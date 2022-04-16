package com.example.techieblog.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentController {

    private CommentRepository commentRepository;

    @Autowired
    public CommentController(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @GetMapping("/article/{messageID}")
    public String article(@PathVariable long messageID, Model model){
        model.addAttribute("comment", new CommentDTO(""));
        return "article";
    }

}
