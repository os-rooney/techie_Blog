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

import javax.validation.Valid;
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
        // show comments
        model.addAttribute("comments", commentRepository.findAllByMessage_Id(messageId));
        // add new Comments
        model.addAttribute("comment", new CommentDTO(""));
        // show/get Article
        model.addAttribute("message", messageRepository.findMessageById(messageId));
        return "article";
    }

    @PostMapping("/article/{messageId}")
    public String article(@PathVariable long messageId, @ModelAttribute CommentDTO comment, @ModelAttribute("sessionUser") User sessionUser, Model model){
        Message message = messageRepository.findMessageById(messageId);
        Comment newComment = new Comment(sessionUser, message, comment.getCommentText(), Instant.now());
        System.out.println(comment.getCommentText());
        System.out.println(message.getContent());
        commentRepository.save(newComment);
        // show comments
        model.addAttribute("comments", commentRepository.findAllByMessage_Id(message.getId()));
        // add new Comments
        model.addAttribute("comment", new CommentDTO(""));
        // show/get Article
        model.addAttribute("message", messageRepository.findMessageById(message.getId()));
        // send user data
        model.addAttribute("user", sessionUser);
        return "article";
    }




}
