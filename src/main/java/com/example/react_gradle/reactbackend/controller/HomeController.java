package com.example.react_gradle.reactbackend.controller;

import com.example.react_gradle.reactbackend.entity.UserTb;
import com.example.react_gradle.reactbackend.repository.UserManageRepo;
import com.example.react_gradle.reactbackend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public HashMap<String, String> login(HttpServletRequest req) throws Exception{

        HashMap<String, String> result = new HashMap<>();
        System.out.println("id : " + req.getParameter("id"));
        System.out.println("pw : " + req.getParameter("pw"));
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");

        try{
            if(userManageRepo.userCheckById(id, pw)){
                List<String> role = new ArrayList<>();
                role.add("ADMIN");
                String jwt = jwtTokenProvider.createToken(id, role);
                System.out.println(jwt);
                String getPk = jwtTokenProvider.getUserPk(jwt);
                System.out.println("test : "+ getPk);
            }

        }catch (Exception e){
            System.out.println("user is not exist");
            result.put("resultCode", "false");
            return result;
        }

        result.put("resultCode","true");
        return result;
    }

}
