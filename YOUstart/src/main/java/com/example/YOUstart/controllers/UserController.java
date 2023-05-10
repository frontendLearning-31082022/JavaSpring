package com.example.YOUstart.controllers;

import com.example.YOUstart.repos.UserRepo;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users",userRepo.findAll());
        return "userList";
    }
}
