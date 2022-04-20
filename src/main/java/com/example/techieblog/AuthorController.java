package com.example.techieblog;

import com.example.techieblog.user.User;
import com.example.techieblog.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/changeToAdmin/{userId}")
    public String authorList(Model model, @PathVariable long userId, @ModelAttribute("sessionUser") User sessionUser){
        if(sessionUser.getRole().equals("admin")) {
            User changeRole = userRepository.findUserById(userId);
            changeRole.setRole("admin");
            userRepository.save(changeRole);
        }else{
            return "redirect:/";
        }
        model.addAttribute("registeredUsers", userRepository.findAllByRole("commentOnly"));
        return "redirect:/author";
    }
}
