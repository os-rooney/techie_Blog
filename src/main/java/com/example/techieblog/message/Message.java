package com.example.techieblog.message;

import com.example.techieblog.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private User user;

    private String title;

    private String description;

    private String content;

    private Instant postedAt;

    public Message() {}

    public Message(User user, String title, String description, String content, Instant postedAt) {
        this.user = user;
        this.title = title;
        this.description = description;
        this.content = content;
        this.postedAt = postedAt;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getPostedAt() {
        return postedAt;
    }

    public void setPostedAt(Instant postedAt) {
        this.postedAt = postedAt;
    }
}
