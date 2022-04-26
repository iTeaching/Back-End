package org.springframework.samples.iTeaching.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.ChatMessage;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    private UserService userService;
    private AlumnoService alumnoService;
	

    @Autowired
    public ChatController(UserService userService, AlumnoService alumnoService){
        this.userService=userService;
        this.alumnoService=alumnoService;
    }

    @GetMapping(value="/chat")
	public String chat(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.findUser(userDetails.getUsername()).get();
        Alumno alumno = this.alumnoService.findAlumnoByUsername(user.getUsername());
        model.addAttribute("alumno", alumno );
		return "chat/chat";
	}
    
    @MessageMapping("/chat.register")
    @SendTo("/topic/public")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }
    
}
