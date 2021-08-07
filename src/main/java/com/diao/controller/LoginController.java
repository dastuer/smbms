package com.diao.controller;

import com.alibaba.fastjson.JSON;
import com.diao.pojo.User;
import com.diao.service.UserService;
import com.diao.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class LoginController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/goLogin")
    public String goLogin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(HttpSession session, HttpServletResponse resp,
                        String username, String pwd, Model model,String rem){
        User user = userService.getUserById(username);
//        System.out.println(rem);
        if (user!=null&&pwd.equals(user.getPwd())){
            if ("on".equals(rem)){
                CookieUtil.saveCookie(user,resp);

            }
            session.setAttribute("userInfo",user);
            return "redirect:/book/allBooks";
        }
        if (user==null){
            model.addAttribute("info","none");
        }
        else if (!pwd.equals(user.getPwd())){
            model.addAttribute("info","wrong");
        }
        return "/login";
    }
    @RequestMapping("/getState")
    @ResponseBody
    public String state(String username, String pwd){
//        System.out.println(username+pwd);
        HashMap<String, String> map = new HashMap<>();
        String msg="";
        if (username!=null){
            User user = userService.getUserById(username);
            System.out.println(user);
            if(user!=null){
                msg="ok";
                map.put("uif","ok");
                if (pwd!=null){
                    if (user.getPwd().equals(pwd)){
                        msg="ok";
                    }
                    else {
                        msg="wrong";
                    }
                    map.put("pif",msg);
                }
            }
            else {
                msg = "none";
                map.put("uif",msg);
            }
        }
//        System.out.println(JSON.toJSONString(map));
//        System.out.println("test");
        return JSON.toJSONString(map);

    }
    @RequestMapping("/error/loginError")
    public String loginError(){
        return "error/loginError";
    }
    @RequestMapping("/error/authorityError")
    public String authorityError(){
        return "error/authorityError";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session,HttpServletResponse resp){
        CookieUtil.clearCookie(resp);
        session.removeAttribute("userInfo");
        return "redirect:/goLogin";
    }
}
