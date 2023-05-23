package com.example.YOUstart.mysql_struct.dto;

import com.example.YOUstart.mysql_struct.Message;
import com.example.YOUstart.mysql_struct.User;

public class MessageDTO {

    private Long likes;
    private Boolean meLiked;
    Message message;

    public MessageDTO(Message message, Long likes, Boolean meLiked) {
        this.message=message;

        this.likes = likes;
        this.meLiked = meLiked;
    }

    public Integer getId() {
        return message.getId();
    }

    public String getText() {
        return message.getText();
    }

    public String getTag() {
        return message.getTag();
    }

    public User getAuthor() {
        return message.getAuthor();
    }

    public String getFilename() {
        return message.getFilename();
    }

    public Long getLikes() {
        return likes;
    }

    public Boolean getMeLiked() {
        return meLiked;
    }

    public String getAuthorName() {
        return message.getAuthorName();
    }
}
