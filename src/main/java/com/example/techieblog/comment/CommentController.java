package com.example.techieblog.comment;

import com.example.techieblog.message.Message;
import com.example.techieblog.message.MessageRepository;
import com.example.techieblog.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

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
    // Send Article
    @PostMapping("/article/{messageId}")
    public String article(@PathVariable long messageId, @ModelAttribute CommentDTO comment, @ModelAttribute("sessionUser") User sessionUser, Model model){
        Message message = messageRepository.findMessageById(messageId);
        Comment newComment = new Comment(sessionUser, message, comment.getCommentText(), Instant.now());
        commentRepository.save(newComment);
        // show comments
        model.addAttribute("comments", commentRepository.findAllByMessage_Id(message.getId()));
        // show/get Article
        model.addAttribute("message", messageRepository.findMessageById(message.getId()));
        // send user data
        model.addAttribute("user", sessionUser);
        // add new Comments
        model.addAttribute("comment", new CommentDTO(""));
        return "article";
    }

    // delete comment
    @PostMapping("/deleteComment")
    public String delete(@RequestParam long commentId, @RequestParam long messageId ,Model model){
        Comment deleteComment = commentRepository.findCommentById(commentId);
        commentRepository.delete(deleteComment);

        return "redirect:/article/" + messageId;
    }
}
