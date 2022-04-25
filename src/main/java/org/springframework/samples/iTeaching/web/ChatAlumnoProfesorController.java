package org.springframework.samples.iTeaching.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Authorities;
import org.springframework.samples.iTeaching.model.ChatMessage;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatAlumnoProfesorController {
	
	private UserService userService;
	private AlumnoService alumnoService;
	private ProfesorService profesorService;
	private AsignaturaService asignaturaService;
	
    @Autowired
    public ChatAlumnoProfesorController(UserService userService, AlumnoService alumnoService, ProfesorService profesorService, AsignaturaService asignaturaService){
        this.userService=userService;
        this.alumnoService=alumnoService;
        this.profesorService=profesorService;
        this.asignaturaService=asignaturaService;
    }
    
    @ModelAttribute("asignatura")
    public Asignatura findAsignatura(@PathVariable("asignaturaId") int asignaturaId) {
    	return this.asignaturaService.findById(asignaturaId);
    }
    
    @GetMapping(value="asignatura/{asignaturaId}/chat")
	public String chat(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.findUser(userDetails.getUsername()).get();
        model.addAttribute("user", user);
        Alumno alumno = this.alumnoService.findAlumnoByUsername(user.getUsername());
        if(alumno!=null) {
        	model.addAttribute("alumno", alumno );
        } else {
        	Profesor profesor = this.profesorService.findProfesorByUsername(user.getUsername());
        	model.addAttribute("profesor", profesor);
        }
		return "chatProf/chat";
	}
    
    @MessageMapping("/asignatura/{asignaturaId}/chat.register")
    @SendTo("/topic/{asignaturaId}")
    public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
    
    @MessageMapping("/asignatura/{asignaturaId}/chat.send")
    @SendTo("/topic/{asignaturaId}")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }

}
