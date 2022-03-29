package org.springframework.samples.iTeaching.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfesorController {
	private static final String VIEWS_PROFESOR_CREATE_OR_UPDATE_FORM = "profesores/createOrUpdateProfesorForm";

	@Autowired
	private ProfesorService profesorService;

	
	@InitBinder("profesor")
	public void initVehiculoBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new ProfesorValidator(profesorService));
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/profesores/new")
	public String initCreationForm(Map<String, Object> model) {
		Profesor profesor = new Profesor();
		model.put("profesor", profesor);
		return VIEWS_PROFESOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/profesores/new")
	public String processCreationForm(@Valid Profesor profesor, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_PROFESOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating profesor, user and authorities
			this.profesorService.saveProfesor(profesor);
			
			return "redirect:/login";
		}
	}

	@GetMapping(value = "/profesores/{profesorId}/edit")
	public String initUpdateOwnerForm(@PathVariable("profesorId") int profesorId, Model model) {
		Profesor profesor = this.profesorService.findProfesorById(profesorId);
		model.addAttribute(profesor);
		return VIEWS_PROFESOR_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/profesores/{profesorId}/edit")
	public String processUpdateOwnerForm(@Valid Profesor profesor, BindingResult result,
			@PathVariable("profesorId") int profesorId) {
		if (result.hasErrors()) {
			return VIEWS_PROFESOR_CREATE_OR_UPDATE_FORM;
		}
		else {
			profesor.setId(profesorId);
			this.profesorService.saveProfesor(profesor);
//			System.out.println(profesorId);
			return "redirect:/profesores/{profesorId}/perfil";
		}
	}


	@GetMapping("/profesores/{profesorId}")
	public ModelAndView showProfesor(@PathVariable("profesorId") int profesorId) {
		ModelAndView mav = new ModelAndView("profesores/profesorDetails");
		Profesor profesor=this.profesorService.findProfesorById(profesorId);
		mav.addObject("profesor",profesor);
		return mav;
	}
	
	
	
	@GetMapping(value = "/profesores/miPerfil")
	public String miPerfil(Model model) {		
		
		try {	// si está logueado
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username= clienteDetails.getUsername();
			System.out.println(username);
			Profesor usuario = profesorService.findProfesorByUsername(username);
			model.addAttribute("profesor", usuario);
			return "profesores/miPerfil";
		}catch(Exception e) {	// si no está logueado
			return "redirect:/";
		}
		
	}
	
}
