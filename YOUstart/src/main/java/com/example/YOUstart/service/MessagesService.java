package com.example.YOUstart.service;

import com.example.YOUstart.mysql_struct.Message;
import com.example.YOUstart.mysql_struct.User;
import com.example.YOUstart.mysql_struct.dto.MessageDTO;
import com.example.YOUstart.repos.MessageRepo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessagesService {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private EntityManager entityManager;



    public Page<MessageDTO> findAll(Pageable pageable, User curUser) {
        Page<MessageDTO> res=  messageRepo.findAll(pageable,curUser);
        return res;
    }
}
