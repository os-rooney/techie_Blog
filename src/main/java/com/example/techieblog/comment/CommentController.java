package com.example.techieblog.comment;

import com.example.techieblog.message.Message;
import com.example.techieblog.message.MessageRepository;
import com.example.techieblog.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.Instant;

@Controller
public class CommentController {

    private CommentRepository commentRepository;
    private MessageRepository messageRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, MessageRepository messageRepository){
        this.commentRepository = commentRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping("/article/{messageId}")
    public String article(@PathVariable long messageId, Model model){
        model.addAttribute("comment", new CommentDTO(""));
        model.addAttribute("message", messageRepository.findMessageById(messageId));
        return "article";
    }

    @PostMapping("/article/{messageId}")
    public String commentSave(@PathVariable long messageId, @ModelAttribute CommentDTO comment, Model model, @ModelAttribute("sessionUser") User sessionUser){
        Message message = messageRepository.findById(messageId).orElseThrow();
        Comment newComment = new Comment(sessionUser, message, comment.getCommentText(), Instant.now());
        commentRepository.save(newComment);
        model.addAttribute("message", message);
        return "article";
    }

}
