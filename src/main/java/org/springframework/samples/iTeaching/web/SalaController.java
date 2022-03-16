package org.springframework.samples.iTeaching.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Sala;
import org.springframework.samples.iTeaching.service.SalaService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	private static final String VIEWS_SALA_CREATE_FORM = "salas/createSalaForm";

	@GetMapping(value = "/salas/new")
	public String initCreationForm(Map<String, Object> model) {
		Sala sala = new Sala();
	
		model.put("salas", sala);
		
		return VIEWS_SALA_CREATE_FORM;
	}
	
	@PostMapping(value = "/salas/new")
	public String processCreationForm(@Valid Sala sala, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_SALA_CREATE_FORM;
		}
		else {
			//creating profesor, user and authorities

			this.salaService.saveSala(sala);
			return "redirect:/welcome";
		}
		
		}
	
	

	@GetMapping(value = "/ofertas/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("sasla", new Sala());
		return "ofertas/findOfertas";
	}
}
