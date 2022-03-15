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
	private static final String VIEWS_PROFESOR_CREATE_OR_UPDATE_FORM = "profesores/createOrOwnerProfesorForm";

	@Autowired
	private ProfesorService profesorService;

	
	

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
			
			return "redirect:/profesores/" + profesor.getId();
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
			return "redirect:/profesores/{profesorId}";
		}
	}


	@GetMapping("/profesores/{profesorId}")
	public ModelAndView showProfesor(@PathVariable("profesorId") int profesorId) {
		ModelAndView mav = new ModelAndView("profesores/profesorDetails");
		Profesor profesor=this.profesorService.findProfesorById(profesorId);
		mav.addObject("profesor",profesor);
		return mav;
	}
	
	@PostMapping("/profesores/{profesorId}")
	public String deleteProfesor(@PathVariable("profesorId") int profesorId) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Profesor usuario = profesorService.findProfesorByUsername(username);
		Profesor profesor = profesorService.findProfesorById(profesorId);
		if (usuario.equals(profesor)) {
		this.profesorService.delete(profesor);
		return "welcome";
	}
		else {
			return "welccome";
		}
	}
}
