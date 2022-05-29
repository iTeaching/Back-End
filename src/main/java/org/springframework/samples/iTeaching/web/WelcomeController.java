package org.springframework.samples.iTeaching.web;

import java.time.LocalDate;
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
		model.addAttribute(profesor);
		if(profesor.getPremium()=="mensual"&&profesor.getPago().isAfter(LocalDate.now().minusMonths(1))){
			profesor.setPremium(null);
			profesor.setPago(null);
		}
		else if(profesor.getPremium()=="anual"&&profesor.getPago().isAfter(LocalDate.now().minusYears(1))){
			profesor.setPremium(null);
			profesor.setPago(null);
		}
	}
		else{
		Alumno alumno=this.alumnoservice.findAlumnoByUsername(username);
		model.addAttribute(alumno);
		if(alumno.getPremium()=="mensual"&&alumno.getPago().isAfter(LocalDate.now().minusMonths(1))){
			alumno.setPremium(null);
			alumno.setPago(null);
		}
		else if(alumno.getPremium()=="mensual"&&alumno.getPago().isAfter(LocalDate.now().minusYears(1))){
			alumno.setPremium(null);
			alumno.setPago(null);
		}
		}

		return "logged";
	}
	
	@GetMapping({ "/TerminosYCondiciones" })
	public String terminosCondiciones(Map<String, Object> model) {

		return "terminoscondiciones";
	}
	
	@GetMapping({ "/GarantiaDePrivacidad" })
	public String garantiaPrivacidad(Map<String, Object> model) {

		return "garantiaPrivacidad";
	}
	
	
}
