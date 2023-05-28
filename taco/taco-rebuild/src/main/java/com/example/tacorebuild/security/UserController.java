package com.example.tacorebuild.security;

import com.example.tacorebuild.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

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
        userFromDB.setRoles(Collections.singleton(Role.USER));

        userRepo.save(userFromDB);
        return "redirect:/login";	 }

}
