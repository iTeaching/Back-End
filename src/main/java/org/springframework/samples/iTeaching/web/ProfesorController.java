package org.springframework.samples.iTeaching.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfesorController {
	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "profesores/createOrOwnerProfesorForm";

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
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/profesores/new")
	public String processCreationForm(@Valid Profesor profesor, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating profesor, user and authorities
			this.profesorService.saveProfesor(profesor);
			
			return "redirect:/profesores/" + profesor.getId();
		}
	}

}
