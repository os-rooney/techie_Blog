package com.example.techieblog.comment;

import com.example.techieblog.message.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {

    private CommentRepository commentRepository;
    private MessageRepository messageRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository, MessageRepository messageRepository){
        this.commentRepository = commentRepository;
        this.messageRepository = messageRepository;
    }



    @GetMapping("/article/{messageID}")
    public String article(@PathVariable long messageID, Model model){
        model.addAttribute("comment", new CommentDTO(""));
        model.addAttribute("message", messageRepository.findMessageById(messageID) );
        return "article";
    }

    @PostMapping("/article/{messageID}")
    public String commentSave(@ModelAttribute Comment comment){
        Comment newComment = new Comment(comment.getComment());

    }

}
