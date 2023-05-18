package com.example.YOUstart.service;

import com.example.YOUstart.mysql_struct.User;
import com.example.YOUstart.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDB=userRepo.findByUsername(username);
        return userFromDB;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserService userDetailService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    @Transactional
    public void subscribe(User currentUser, User userForSubscribe) {
            userForSubscribe.getSubscribers().add(currentUser);
            currentUser.getSubscriptions().add(userForSubscribe);
            userRepo.save(userForSubscribe);
            userRepo.save(currentUser);
    }

    public void unSubscribe(User currentUser, User userForUnSubscribe) {
        userForUnSubscribe.getSubscribers().remove(currentUser);
        currentUser.getSubscriptions().remove(userForUnSubscribe);
        userRepo.save(userForUnSubscribe);
        userRepo.save(currentUser);
    }
}
