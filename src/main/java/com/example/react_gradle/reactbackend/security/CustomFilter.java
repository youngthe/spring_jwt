package com.example.react_gradle.reactbackend.security;

import com.example.react_gradle.reactbackend.entity.UserTb;
import com.example.react_gradle.reactbackend.repository.UserManageRepo;
import io.jsonwebtoken.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class CustomFilter implements Filter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserManageRepo userManageRepo;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("\nFilter start\n");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        System.out.println(req.getRequestURI());
        String token = req.getHeader("jwt_token");

            try{
                String userId = jwtTokenProvider.getUserId(token);
                String user_role = jwtTokenProvider.getUserRole(token);
                System.out.println("userId : " + userId);
                System.out.println("userRole : " + user_role);
                System.out.println("jwt token Fiter : " + token);

                if(user_role == "admin"){

                }
            }catch (Exception e) {
                System.out.println("jwt is null");
                res.setHeader("jwt_token", "");
            }




        chain.doFilter(request, response);
    }
}
