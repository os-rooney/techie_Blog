package com.example.techieblog.session;

import com.example.techieblog.user.User;
import com.example.techieblog.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.Optional;

@Controller
public class SessionController {

    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new LoginDTO("", ""));
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login") LoginDTO login, BindingResult bindingResult, HttpServletResponse response) {
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());

        if (optionalUser.isPresent()) {
            Session session = new Session(optionalUser.get(), Instant.now().plusSeconds(7*24*60*60));
            sessionRepository.save(session);

            Cookie cookie = new Cookie("sessionId", session.getId());
            response.addCookie(cookie);

            // Login erfolgreich
            return "redirect:/";
        }

        bindingResult.addError(new FieldError("login", "password", "Login fehlgeschlagen."));

        return "login";
    }

    @PostMapping("/logout")
    public String logout(@CookieValue(value = "sessionId", defaultValue = "") String sessionId, HttpServletResponse response) {
        Optional<Session> optionalSession = sessionRepository.findByIdAndExpiresAtAfter(sessionId, Instant.now());
        optionalSession.ifPresent(session -> sessionRepository.delete(session));

        Cookie cookie = new Cookie("sessionId", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }
}
