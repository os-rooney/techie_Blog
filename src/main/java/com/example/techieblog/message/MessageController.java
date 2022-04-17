package com.example.techieblog.message;

import com.example.techieblog.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/message/{id}")
    public String message(@Valid @ModelAttribute("message") MessageDTO messageDTO, @PathVariable long id, BindingResult bindingResult, @ModelAttribute("sessionUser") User sessionUser) {
        if (bindingResult.hasErrors()) {
            return "message";
        }
        if (!messageRepository.existsById(id)) {
            Message message = new Message(sessionUser, messageDTO.getTitle(), messageDTO.getDescription(), messageDTO.getContent(), Instant.now());
            messageRepository.save(message);
        }else{
            Message message = messageRepository.findMessageById(id);
            message.setTitle(messageDTO.getTitle());
            message.setDescription(messageDTO.getDescription());
            message.setContent(messageDTO.getContent());
            messageRepository.save(message);
        }
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

    @GetMapping("/messageUpdate/{messageId}")
    public String getMessageDataToUpdate(@PathVariable long messageId, @ModelAttribute("sessionUser") User sessionUser, Model model) {
        Message message = messageRepository.findMessageById(messageId);

        model.addAttribute("message", message);
        return "message";
    }


}
