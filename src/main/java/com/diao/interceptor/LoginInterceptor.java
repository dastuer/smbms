package com.diao.interceptor;

import com.diao.pojo.User;
import com.diao.service.UserService;
import com.diao.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("userInfo")!=null){
            return true;
        }
        else if (CookieUtil.readCookie(request,response,userService)){
            return true;
        }
        else {
            request.getRequestDispatcher("/error/loginError").
                    forward(request,response);
        }
        return false;
    }
}
