package com.example.techieblog.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CommentController {

    @GetMapping("/article/{messageID}")
    public String article(@PathVariable long messageID, Model model){
        return "article";
    }

}
