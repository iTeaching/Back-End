package org.springframework.samples.iTeaching.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
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
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatAlumnoProfesorController {
	
	private UserService userService;

    @Autowired
    private AlumnoService alumnoService;
	
    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private AsignaturaService asignaturaService;

    @Autowired
    public ChatAlumnoProfesorController(UserService userService){
        this.userService=userService;
    }
    
    
    @GetMapping(value="/chatProf/{asignaturaId}")
	public String chat(Model model,@PathVariable("asignaturaId") int id){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.findUser(userDetails.getUsername()).get();
        model.addAttribute("user", user);
        Asignatura asignatura = asignaturaService.findById(id);
        Alumno alumno = alumnoService.findAlumnoByUsername(userDetails.getUsername());
        if (alumno==null){
        Profesor profesor =profesorService.findProfesorByUsername(userDetails.getUsername());
            if (asignatura.getProfesor().equals(profesor)){
                model.addAttribute("alumno", profesor.getUser().getUsername());
                return "chat/chatProf";
            }
        }
        else{
            Set<Alumno> alumnos= asignatura.getAlumnos();
            if(alumnos.contains(alumno)){
                model.addAttribute("alumno", alumno.getUser().getUsername());
                return "chat/chatProf";
            }
        }

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
