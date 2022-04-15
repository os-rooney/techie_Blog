package com.example.techieblog.profile;

import antlr.debug.MessageAdapter;
import com.example.techieblog.message.Message;
import com.example.techieblog.message.MessageRepository;
import com.example.techieblog.user.User;
import com.example.techieblog.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    private UserRepository userRepository;
    private MessageRepository messageRepository;

    @Autowired
    public ProfileController(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username, Model model) {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<Message> messages = messageRepository.findMessagesByUserOrderByPostedAtDesc(user);
        model.addAttribute("user", user);
        model.addAttribute("messages", messages);
        return "profile";
    }
}