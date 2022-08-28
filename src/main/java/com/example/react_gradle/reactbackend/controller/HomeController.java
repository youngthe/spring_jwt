package com.example.react_gradle.reactbackend.controller;

import com.example.react_gradle.reactbackend.entity.UserTb;
import com.example.react_gradle.reactbackend.repository.UserManageRepo;
import com.example.react_gradle.reactbackend.security.JwtTokenProvider;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@Controller
@CrossOrigin("*")
public class HomeController {
    @Autowired
    UserManageRepo userManageRepo;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @RequestMapping(value = "/")
    public HashMap<String, String> login(HttpServletRequest req) throws Exception{

        HashMap<String, String> result = new HashMap<>();
        System.out.println("id : " + req.getParameter("id"));
        System.out.println("pw : " + req.getParameter("pw"));
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");


        try{
            UserTb user = userManageRepo.userCheckById(id, pw);
            String jwt = jwtTokenProvider.createToken(user);
            result.put("resultCode", "true");
            result.put("jwt_token", jwt);
            return result;

        }catch (Exception e){
            System.out.println("user is not exist");
            result.put("resultCode", "false");
            return result;
        }

    }

    @RequestMapping(value = "/page")
    public HashMap<String, String> Page(HttpServletRequest req) throws Exception{

        HashMap<String, String> result = new HashMap<>();
        String token = req.getHeader("jwt_token");

        if(token.isEmpty()){
            result.put("resultCode", "false");
            return result;
        }

        result.put("resultCode", "true");
        return result;
    }


}
