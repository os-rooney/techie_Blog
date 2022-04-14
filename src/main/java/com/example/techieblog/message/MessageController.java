package com.example.techieblog.message;

import com.example.techieblog.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.time.Instant;

@Controller
public class MessageController {

    private MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping("/message")
    public String message(Model model) {
        model.addAttribute("message", new MessageDTO("", "", ""));
        return "message";
    }

    @PostMapping("/message")
    public String message(@Valid @ModelAttribute("message") MessageDTO messageDTO, BindingResult bindingResult, @ModelAttribute("sessionUser") User sessionUser) {
        if (bindingResult.hasErrors()) {
            return "message";
        }

        Message message = new Message(sessionUser, messageDTO.getTitle(), messageDTO.getDescription(), messageDTO.getContent(), Instant.now());
        messageRepository.save(message);

        return "redirect:/";
    }

    @PostMapping("/messageDelete")
    public String delete(@RequestParam long messageId, @ModelAttribute("sessionUser") User sessionUser) {
        Message message = messageRepository.findById(messageId).orElseThrow();
        if (message.getUser() != sessionUser) {
            throw new IllegalArgumentException("nein!");
        }

        messageRepository.delete(message);

        return "redirect:" + UriComponentsBuilder.fromPath("/profile").pathSegment(sessionUser.getUsername()).toUriString();
    }

}
