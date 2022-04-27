package org.springframework.samples.iTeaching.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.samples.iTeaching.model.ChatMessage;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatAlumnoProfesorController {
	
	private UserService userService;

	
    @Autowired
    public ChatAlumnoProfesorController(UserService userService){
        this.userService=userService;
    }
    
    
    @GetMapping(value="/chatProf/{asignaturaId}")
	public String chat(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.findUser(userDetails.getUsername()).get();
        model.addAttribute("user", user);
		return "chat/chatProf";
	}
    
    @MessageMapping("/chatProf.register/{asignaturaId}")
    @SendTo("/topic/asignatura/{asignaturaId}")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    
    @MessageMapping("/chatProf.send/{asignaturaId}")
    @SendTo("/topic/asignatura/{asignaturaId}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }

}
