package org.springframework.samples.iTeaching.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WelcomeController {
	
	@Autowired
	private ProfesorService profesorservice;
	@Autowired
	private AlumnoService alumnoservice;

	@GetMapping({ "/", "/welcome" })
	public String welcome(Map<String, Object> model) {

		return "welcome";
	}

	@GetMapping("/logged")
	public String loggedUser(Model model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username= clienteDetails.getUsername();
		String authority= clienteDetails.getAuthorities().iterator().next().getAuthority();
		if(authority.equals("profesor")) {
		Profesor profesor=this.profesorservice.findProfesorByUsername(username);
		model.addAttribute(profesor);}
		else{
		Alumno alumno=this.alumnoservice.findAlumnoByUsername(username);
		model.addAttribute(alumno);
		}

		return "logged";
	}
	
	@GetMapping({ "/TerminosYCondiciones" })
	public String terminosCondiciones(Map<String, Object> model) {

		return "terminoscondiciones";
	}
}
