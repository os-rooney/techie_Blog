package com.example.techieblog;

import com.example.techieblog.user.AdminService;
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
    public String authorList(Model model, @ModelAttribute("sessionUser") User sessionUser ){
        if(!AdminService.adminCheck(sessionUser)){
            return "redirect:/";
        }
        model.addAttribute("registeredUsers", userRepository.findAllByRole("commentOnly"));
        return "author-list";
    }

    @PostMapping("/changeToAdmin/{userId}")
    public String authorList(Model model, @PathVariable long userId, @ModelAttribute("sessionUser") User sessionUser){
        if(!AdminService.adminCheck(sessionUser)){
            return "redirect:/";
        }
        AdminService.upgradeUserToAdmin(sessionUser, userRepository, userId, model);
        return "redirect:/author";
    }
}
