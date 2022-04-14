package com.example.techieblog.profile;

import com.example.techieblog.user.User;
import com.example.techieblog.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

    private UserRepository userRepository;

    @Autowired
    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profile/{username}")
    public String profile(@PathVariable String username, Model model) {
        User user = userRepository.findByUsername(username).orElseThrow();
        model.addAttribute("user", user);
        return "profile";
    }
}