package com.example.YOUstart.controllers;

import com.example.YOUstart.mysql_struct.Message;
import com.example.YOUstart.mysql_struct.Role;
import com.example.YOUstart.mysql_struct.User;
import com.example.YOUstart.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegisterUserController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration(Map<String, Object> model) {
        //model.put("name", name);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@RequestParam String username,@RequestParam String password, Map<String, Object> model){
        User userFromDB=userRepo.findByUsername(username);

        if (userFromDB != null){
            model.put("message","user exist");
            return "registration";
        }

        userFromDB=new User();
        userFromDB.setUsername(username);
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        userFromDB.setPassword(passwordEncoder.encode(password));
        userFromDB.setActive(true);
        userFromDB.setRoles(Collections.singleton(Role.USER));

        userRepo.save(userFromDB);
        return "redirect:/login";
    }

}
