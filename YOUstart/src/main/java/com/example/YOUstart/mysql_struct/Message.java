package com.example.YOUstart.mysql_struct;

import jakarta.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public String getAuthorName() {
        return author!=null ? author.getUsername() : "<none>";
    }

    public Message(String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.author=user;
    }

    private String tag;

    public Message() {}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
