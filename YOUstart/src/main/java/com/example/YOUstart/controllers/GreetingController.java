package com.example.YOUstart.controllers;

import com.example.YOUstart.mysql_struct.Message;
import com.example.YOUstart.mysql_struct.User;
import com.example.YOUstart.repos.MessageRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "main";
    }

    @GetMapping("/messages")
    public String main(@AuthenticationPrincipal User user,Map<String,Object>model){
        Iterable<Message> messages=messageRepo.findAll();

        model.put("idCurUser",user.getId());
        model.put("messages",messages);
        model.put("some","test");
        return "messages";}

    @PostMapping(value = "/messages", params = "addSubmit")
    public String addMessages(@AuthenticationPrincipal User user,   @RequestParam("file")MultipartFile file,
                              @Valid Message message   ,
                              BindingResult bindingResult, Model model, HttpServletRequest request) throws IOException {
        message.setAuthor(user);
          if(bindingResult.hasErrors()){
            Map<String, String> errorsMaps = ControllersUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMaps);
            model.addAttribute("message",message);

        }else {
        if (file!=null && !file.isEmpty()){
            File uploadPathFile=new File(uploadPath);
            if (!uploadPathFile.exists())uploadPathFile.mkdirs();

            String genName= UUID.randomUUID().toString()+"."+file.getOriginalFilename();
            file.transferTo(new File(uploadPathFile.getAbsolutePath()+"\\"+ genName)  );
            message.setFilename(genName);
        }
        messageRepo.save(message);
        model.addAttribute("message",null);
        }

        Iterable<Message> messages=messageRepo.findAll();
        model.addAttribute("messages",messages);


        return "messages";
    }



    @PostMapping("/messages/filter")
    public String filter(@RequestParam String filter,Map<String, Object> model){
        List<Message> filterList=messageRepo.findByTag(filter);
        if (filterList.size()==0)filterList= (List<Message>) messageRepo.findAll();

        model.put("messages",filterList);
        model.put("some","");

        return "main";
    }


    @GetMapping("/user-messages/messages/{userReq}")
    public String userMessages(@AuthenticationPrincipal User currentUser,
                               @PathVariable User userReq,
                               @RequestParam(required = false) Message message,
                               Model model){
        Set<Message> messByUser=userReq.getMessages();
        model.addAttribute("messages",messByUser);
        model.addAttribute("message",message);

        model.addAttribute("isCurrentUser",currentUser.equals(userReq));

        return "userMessages";
    }

    @GetMapping(value = "/user-messages/messages/{userReq}/editMessage{message}")
    public String editMessage(@AuthenticationPrincipal User currentUser,
                               @PathVariable User userReq,
                               @PathVariable Message message,
                               Model model){
        if (!currentUser.equals(message.getAuthor())){
            model.addAttribute("errorMessage","Вы не являетесь владельцом сообщения");
            return "error";
        }


        model.addAttribute("message",message);
        return "editMessage";
    }
    @PostMapping(value = "/user-messages/messages/{userReq}/editMessage{message}")
    public String editMessageSave(@AuthenticationPrincipal User currentUser,
                              @PathVariable User userReq,
                                  Message message,
                              Model model){

        if (!currentUser.equals(message.getAuthor())){
            model.addAttribute("errorMessage","Вы не являетесь владельцом сообщения");
            return "error";
        }

        messageRepo.save(message);
        model.addAttribute("message",message);
        model.addAttribute("result_saving","saved success");
        return "editMessage";
    }



}