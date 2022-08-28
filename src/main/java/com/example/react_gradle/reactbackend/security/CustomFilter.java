package com.example.react_gradle.reactbackend.security;

import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Component
public class CustomFilter implements Filter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String token = req.getHeader("jwt_token");
        String userId = jwtTokenProvider.getUserId(token);
        String user_role = jwtTokenProvider.getUserRole(token);

        System.out.println("Starting a transaction for req : "+ req.getRequestURI());
        System.out.println("jwt token Fiter : " + token);
        System.out.println("userId : " + userId);
        System.out.println("userRole : " + user_role);


        chain.doFilter(request, response);
    }
}
