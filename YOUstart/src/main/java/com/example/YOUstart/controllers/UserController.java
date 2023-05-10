package com.example.YOUstart.controllers;

import com.example.YOUstart.mysql_struct.Role;
import com.example.YOUstart.mysql_struct.User;
import com.example.YOUstart.repos.UserRepo;
import jakarta.persistence.ManyToOne;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    @GetMapping("/{user}")
    public String editUser(@PathVariable User user, Model model){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "editUser";
    }

    @PostMapping("/{user}")
    public String editUserSave(@RequestParam String username,
                               @RequestParam Map<String,String> form,
                               @RequestParam("user_id") User user){
        user.setUsername(username);

        Set<String> roles= Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();
        form.keySet().forEach(x->{
            if (roles.contains(x))user.getRoles().add(Role.valueOf(x));
        });

        userRepo.save(user);
        return "redirect:/users";
    }


}
