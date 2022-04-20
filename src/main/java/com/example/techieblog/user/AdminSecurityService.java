package com.example.techieblog.user;

import org.springframework.stereotype.Service;

@Service
public class AdminSecurityService {
    public static boolean adminCheck(User sessionUser){
        if(sessionUser != null && sessionUser.getRole().equals("admin")) {
            return true;
        }
        return false;
    }
}
