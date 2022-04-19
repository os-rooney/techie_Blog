package com.example.techieblog;

import com.example.techieblog.message.MessageRepository;
import com.example.techieblog.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class ArchiveController {

    private MessageRepository messageRepository;

    @Autowired
    public ArchiveController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/archive")
    public String archive(@ModelAttribute("sessionUser") User sessionUser, Model model) {
        model.addAttribute("messages", messageRepository.findAllByOrderByPostedAtDesc());
        return "archive";
    }
}
