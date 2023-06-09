package com.example.YOUstart.controllers;

import com.example.YOUstart.mysql_struct.Message;
import com.example.YOUstart.mysql_struct.Role;
import com.example.YOUstart.mysql_struct.User;
import com.example.YOUstart.repos.UserRepo;
import com.example.YOUstart.service.UserService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.ManyToOne;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

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


    @GetMapping(value = "/subscribe/{userReq}")
    public String subscribeToUser(@AuthenticationPrincipal User currentUser,
                                  @PathVariable User userReq, Principal user){
        userService.subscribe( userRepo.findById(currentUser.getId()).get(),userReq);
        return "redirect:/user-messages/messages/"+userReq.getId();
    }
    @GetMapping(value = "/unsubscribe/{userReq}")
    public String unSubscribeToUser(@AuthenticationPrincipal User currentUser,
                                   @PathVariable User userReq,Principal user){
        userService.unSubscribe(userRepo.findById(currentUser.getId()).get(),userReq);
        return "redirect:/user-messages/messages/"+userReq.getId();
    }

    @GetMapping(value = "/{type}/{userReq}/list")
    public String userList(
                                    @PathVariable User userReq, @PathVariable String type,Model model){
        model.addAttribute("userChannel",userReq);
        model.addAttribute("type",type);

        if (type.equals("subscriptions"))model.addAttribute("users",userReq.getSubscriptions());
        if (type.equals("subscribers"))model.addAttribute("users",userReq.getSubscribers());

        return "subscriptions";
    }

}
