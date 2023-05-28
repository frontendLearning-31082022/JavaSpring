package com.example.tacorebuild.security;

import com.example.tacorebuild.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoderBCrypt;

    @GetMapping("/registration")
    public String registration() {
        return "login/registration";}


    @PostMapping("/registration")
    public String addUser(@RequestParam String username,
                          @RequestParam String password, Model model){
        User userFromDB=userRepo.findByUsername(username);
        if (userFromDB != null){  model.addAttribute("message","user exist");
            return "registration";    }

        userFromDB=new User();
        userFromDB.setUsername(username);

        userFromDB.setPassword(passwordEncoderBCrypt.encode(password));
        userFromDB.setActive(true);
        userFromDB.setRoles(Collections.singleton(Role.ROLE_USER));

        if (userRepo.findAll().size()==0)userFromDB.setRoles(new HashSet<>(Arrays.asList(Role.ROLE_USER,Role.ROLE_ADMIN)));

        userRepo.save(userFromDB);
        return "redirect:/login";	 }

}
