package com.example.YOUstart.mysql_struct;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "fill text")
    @Length(max = 200,message = "max 200 letters")
    private String text;
    @NotBlank(message = "fill tag")
    @Length(max = 10,message = "max 10 letters")
    private String tag;

    @ManyToMany
    @JoinTable(name="message_likes",
            joinColumns = { @JoinColumn(name="message_id")},
            inverseJoinColumns = { @JoinColumn(name="user_id")})
    private Set<User> likes=new HashSet<>();

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAuthorName() {
        return author!=null ? author.getUsername() : "<none>";
    }

    public Message(String text, String tag, User user) {
        this.text = text;
        this.tag = tag;
        this.author=user;
    }



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
