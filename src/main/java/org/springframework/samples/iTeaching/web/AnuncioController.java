package org.springframework.samples.iTeaching.web;

import java.util.Collection;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.service.AnuncioService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class AnuncioController {
	private AnuncioService anuncioService;
	
	@Autowired
	private ProfesorService profesorService;
	private static final String VIEWS_ANUNCIO_CREATE_FORM = "anuncios/createAnuncioForm";

	@Autowired
	public  AnuncioController(AnuncioService anuncioService, ProfesorService profesorService) {
		this.anuncioService=anuncioService;
		this.profesorService=profesorService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/oferta/new")
	public String initCreationForm(Map<String, Object> model) {
		Anuncio anuncio = new Anuncio();
		model.put("anuncio", anuncio);
		return VIEWS_ANUNCIO_CREATE_FORM;
	}
	
	@PostMapping(value = "/oferta/new")
	public String processCreationForm(@Valid Anuncio anuncio, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_ANUNCIO_CREATE_FORM;
		}
		else {
			//creating profesor, user and authorities
			this.anuncioService.saveAnuncio(anuncio);
			
			return "redirect:/usuarios/" + anuncio.getId();
		}
		
		}
	
	

	@GetMapping(value = "/ofertas/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("anuncio", new Anuncio());
		return "ofertas/findOfertas";
	}
	
	@GetMapping(value="/misAnuncios")
	public String findMisAnuncios(Map<String, Object> model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Profesor usuario = profesorService.findProfesorByUsername(username);
		Collection<Anuncio> anuncios= anuncioService.findByUsuario(usuario.getId());
		model.put("anuncios", anuncios);
		return "anuncios/misAnuncios";
	}
	
	@GetMapping(value = "/anuncio/{anuncioId}/edit")
	public String initUpdateOwnerForm(@PathVariable("anuncioId") int anuncioId, Model model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Profesor usuario = profesorService.findProfesorByUsername(username);
		Anuncio anuncio = this.anuncioService.findById(anuncioId);
		if (usuario.equals(anuncio.getProfesor())) {
		model.addAttribute(anuncio);
		return VIEWS_ANUNCIO_CREATE_FORM;
	}
		else {
			return "welccome";
		}
	}

	@PostMapping(value = "/anuncio/{anuncioId}/edit")
	public String processUpdateOwnerForm(@Valid Anuncio anuncio, BindingResult result,
			@PathVariable("anuncioId") int anuncioId) {
		if (result.hasErrors()) {
			return VIEWS_ANUNCIO_CREATE_FORM;
		}
		else {
			anuncio.setId(anuncioId);
			this.anuncioService.saveAnuncio(anuncio);
			return "redirect:/anuncio/{anuncioId}";
		}
	}


}
