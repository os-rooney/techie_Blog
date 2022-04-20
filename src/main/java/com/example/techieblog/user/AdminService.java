package com.example.techieblog.user;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AdminService {

    public static boolean adminCheck(User sessionUser){
        if(sessionUser != null && sessionUser.getRole().equals("admin")) {
            return true;
        }
        return false;
    }

    public static void upgradeUserToAdmin(User sessionUser, UserRepository userRepository, long userId, Model model) {
        User user = userRepository.findUserById(userId);
        user.setRole("admin");
        userRepository.save(user);
        model.addAttribute("registeredUsers", userRepository.findAllByRole("commentOnly"));
    }
}
