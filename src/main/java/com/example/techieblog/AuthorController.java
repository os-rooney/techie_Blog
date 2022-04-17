package com.example.techieblog;

import com.example.techieblog.user.User;
import com.example.techieblog.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AuthorController {
    private UserRepository userRepository;

    AuthorController( UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/author")
    public String authorList(Model model){
        model.addAttribute("registeredUsers", userRepository.findAllByRole("commentOnly"));
        return "author-list";
    }

    @GetMapping("/changeToAdmin/{userId}")
    public String authorList(Model model, @PathVariable long userId){
        User changeRole = userRepository.findUserById(userId);
        changeRole.setRole("admin");
        userRepository.save(changeRole);
        model.addAttribute("registeredUsers", userRepository.findAllByRole("commentOnly"));
        return "author-list";
    }
}