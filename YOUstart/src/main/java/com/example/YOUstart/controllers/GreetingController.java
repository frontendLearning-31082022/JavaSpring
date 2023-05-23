package com.example.YOUstart.controllers;

import com.example.YOUstart.mysql_struct.Message;
import com.example.YOUstart.mysql_struct.User;
import com.example.YOUstart.mysql_struct.dto.MessageDTO;
import com.example.YOUstart.repos.MessageRepo;
import com.example.YOUstart.service.MessagesService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Controller
public class GreetingController {
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private MessagesService messagesService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "main";
    }

    @GetMapping("/messages")
    public String main( Map<String,Object>model,
                       @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable
    ,@AuthenticationPrincipal User curUser){
//        Page<Message> messages=messageRepo.findAll(pageable);
        Page<MessageDTO> messages=messagesService.findAll(pageable,curUser);


        model.put("idCurUser",curUser.getId());
        model.put("messages",messages);
        model.put("url","/messages");
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
    public String filter(@RequestParam String filter,Map<String, Object> model,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @AuthenticationPrincipal User currentUser
    ){
        Page<MessageDTO> filterList=messageRepo.findByTag(filter,currentUser, pageable);

        if (filterList.getSize()==0)filterList= (Page<MessageDTO>) messageRepo.findAll(pageable,currentUser);

        model.put("messages",filterList);
        model.put("some","");

        return "main";
    }


    @GetMapping("/user-messages/messages/{userReq}")
    public String userMessages(@AuthenticationPrincipal User currentUser,
                               @PathVariable User userReq,
                               @RequestParam(required = false) Message message,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
                               HttpServletRequest request,
                               Model model){
        Page<MessageDTO> messByUser=messageRepo.findByUser(pageable,userReq,currentUser);

        model.addAttribute("messages",messByUser);
        model.addAttribute("message",message);
        model.addAttribute("idCurUser",currentUser.getId());
        model.addAttribute("url",request.getRequestURL().toString() );

        model.addAttribute("userChannel",userReq);
        model.addAttribute("isSubscriber",userReq.getSubscribers().contains(currentUser));
        model.addAttribute("subscriptionsCount",userReq.getSubscriptions().size());
        model.addAttribute("subscribersCount",userReq.getSubscribers().size());

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

    @GetMapping("/messages/{message}/like")
    public String makeLike(@AuthenticationPrincipal User curUser,
            @PathVariable Message message,
            RedirectAttributes redirectAttributes,
            @RequestHeader(required = false) String referer){

        Set<User> likes=message.getLikes();
        if(likes.contains(curUser)){likes.remove(curUser);
        }else{likes.add(curUser);};

        UriComponents uriComponents= UriComponentsBuilder.fromHttpUrl(referer).build();

        uriComponents.getQueryParams().entrySet().forEach(x->
                redirectAttributes.addAttribute(x.getKey(),x.getValue()));
        return "redirect:"+uriComponents.getPath();}
}