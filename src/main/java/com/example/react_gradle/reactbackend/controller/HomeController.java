package com.example.react_gradle.reactbackend.controller;

import com.example.react_gradle.reactbackend.entity.UserTb;
import com.example.react_gradle.reactbackend.repository.UserManageRepo;
import com.example.react_gradle.reactbackend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Controller
@CrossOrigin("*")
public class HomeController {
    @Autowired
    UserManageRepo userManageRepo;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @RequestMapping(value = "/")
    public HashMap<String, String> login(HttpServletRequest req, @RequestHeader("jwt_token") String tokenHeader) throws Exception{

        HashMap<String, String> result = new HashMap<>();
        System.out.println("id : " + req.getParameter("id"));
        System.out.println("pw : " + req.getParameter("pw"));
        System.out.println("token :" + tokenHeader);
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");


        try{
            UserTb user = userManageRepo.userCheckById(id, pw);
            System.out.println(user.getRole());
                String jwt = jwtTokenProvider.createToken(id);
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
    public HashMap<String, String> Page(HttpServletRequest req, @RequestHeader("jwt_token") String tokenHeader) throws Exception{

        HashMap<String, String> result = new HashMap<>();
        System.out.println("token :" + tokenHeader);
//
        boolean test = jwtTokenProvider.validateToken(tokenHeader);
        System.out.println("author : " + test);
        if(tokenHeader.isEmpty()){

            result.put("resultCode", "false");
            return result;
        }

        result.put("resultCode", "true");
        return result;
    }
}
