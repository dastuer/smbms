package com.diao.utils;


import java.io.IOException;

import java.io.PrintWriter;

import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;

import javax.servlet.ServletException;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;

import com.diao.pojo.User;

import com.diao.service.UserService;
import com.mchange.util.Base64Encoder;
import com.mysql.cj.util.Base64Decoder;
import org.junit.Test;
import org.springframework.util.Base64Utils;


public class CookieUtil {
    //保存cookie时的cookieName
    private final static String cookieDomainName ="smbms";
    //加密cookie时的网站自定码
    private final static String webKey = "123456";
    //设置cookie有效期是两个星期，根据需要自定义
    private final static long cookieMaxAge = 60 * 60 * 24 * 7 * 2;
    //保存Cookie到客户端-------------------------------------------------------------------------
    //在CheckLogonServlet.java中被调用
    //传递进来的user对象中封装了在登陆时填写的用户名与密码
    public static void saveCookie(User user, HttpServletResponse response) {
        //cookie的有效期
        long validTime = System.currentTimeMillis() + (cookieMaxAge * 5000);
        //MD5加密用户详细信息
        String cookieValueWithMd5 =getMD5(user.getUsername() + ":" + user.getPwd()
                + ":" + validTime + ":" + webKey);
        //将要被保存的完整的Cookie值
        String cookieValue = user.getUsername() + ":" + validTime + ":" + cookieValueWithMd5;
        //再一次对Cookie的值进行BASE64编码
        String cookieValueBase64 = new String(Base64Utils.encode(cookieValue.getBytes()));
        //开始保存Cookie
        Cookie cookie = new Cookie(cookieDomainName, cookieValueBase64);
        //存两年(这个值应该大于或等于validTime)
        cookie.setMaxAge(60 * 60 * 24 * 365 * 2);
        //cookie有效路径是网站根目录
        cookie.setPath("/");
        //向客户端写入
        response.addCookie(cookie);
    }
    //读取Cookie,自动完成登陆操作----------------------------------------------------------------
    //在Filter程序中调用该方法,见AutoLogonFilter.java
    public static Boolean readCookie(HttpServletRequest request, HttpServletResponse response, UserService service) throws IOException {
    //根据cookieName取cookieValue
        Cookie[] cookies = request.getCookies();
        String cookieValue = null;
        if (cookies==null) {
            return false;
        }
        for (Cookie cookie : cookies)
            if (cookieDomainName.equals(cookie.getName())) {
                cookieValue = cookie.getValue();
                break;
            }
        //如果cookieValue为空,返回
        if(cookieValue==null){
            return false;
        }
        //如果cookieValue不为空,才执行下面的代码
        //先得到的CookieValue进行Base64解码
        String cookieValueAfterDecode = new String (Base64Utils.decode(cookieValue.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        //对解码后的值进行分拆,得到一个数组,如果数组长度不为3,就是非法登陆
        String[] cookieValues = cookieValueAfterDecode.split(":");
        if(cookieValues.length!=3){
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("你正在用非正常方式进入本站...");
        out.close();
        return false; }
        //判断是否在有效期内,过期就删除Cookie
        long validTimeInCookie = Long.parseLong(cookieValues[1]);
        if(validTimeInCookie < System.currentTimeMillis()){
            //删除Cookie
            clearCookie(response);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("你的Cookie已经失效,请重新登陆");
            out.close();
            return false;
        }
        //取出cookie中的用户名,并到数据库中检查这个用户名,
        String username = cookieValues[0];
        //根据用户名到数据库中检查用户是否存在
        User user = service.getUserById(username);
        //如果user返回不为空,就取出密码,使用用户名+密码+有效时间+ webSiteKey进行MD5加密
        if(user!=null){
            String md5ValueInCookie = cookieValues[2];
            String md5ValueFromUser =getMD5(user.getUsername() + ":" + user.getPwd()
                    + ":" + validTimeInCookie + ":" + webKey);
            //将结果与Cookie中的MD5码相比较,如果相同,写入Session,自动登陆成功,并继续用户请求
            if(md5ValueFromUser.equals(md5ValueInCookie)){
                HttpSession session = request.getSession(true);
                session.setAttribute("userInfo", user);
                return true;
            }
        }else{
        //返回为空执行
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("cookie验证错误！");
            out.close();
            return false;
        }
        return false;
    }

    //用户注销时,清除Cookie,在需要时可随时调用-----------------------------------------------------
    public static void clearCookie( HttpServletResponse response){
        Cookie cookie = new Cookie(cookieDomainName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
    //获取Cookie组合字符串的MD5码的字符串----------------------------------------------------------------
    public static String getMD5(String value) {
        String result = null;
        try{
            byte[] valueByte = value.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(valueByte);
            result = toHex(md.digest());
        } catch (NoSuchAlgorithmException e2){
            e2.printStackTrace();
        }
        return result;
    }
    //将传递进来的字节数组转换成十六进制的字符串形式并返回
    private static String toHex(byte[] buffer){
        StringBuilder sb = new StringBuilder(buffer.length * 2);
        for (byte b : buffer) {
            sb.append(Character.forDigit((b & 0xf0) >> 4, 16));
            sb.append(Character.forDigit(b & 0x0f, 16));
        }
        return sb.toString();
    }
}
