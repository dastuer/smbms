package com.diao.interceptor;

import com.diao.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("userInfo");
        if ("admin".equals(user.getUsername())){
            return true;
        }
        else {
            request.getRequestDispatcher("/error/authorityError").forward(request,response);
        }
        return false;
    }
}
