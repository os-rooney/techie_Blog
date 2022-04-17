package com.example.techieblog.user;
import com.example.techieblog.comment.Comment;
import com.example.techieblog.message.Message;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String password;
    private String role;
    @OneToMany(mappedBy = "user")
    private List<Message> messages;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    //Standardkonstruktor
    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "commentOnly";
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}