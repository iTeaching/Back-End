package org.springframework.samples.iTeaching.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AnuncioService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class AnuncioController {
	private AnuncioService anuncioService;
	
	@Autowired
	private ProfesorService profesorService;
	@Autowired
	private AlumnoService alumnoService;
	private static final String VIEWS_ANUNCIO_CREATE_FORM = "anuncios/createAnuncioForm";

	@Autowired
	public  AnuncioController(AnuncioService anuncioService, ProfesorService profesorService,AlumnoService alumnoService) {
		this.anuncioService=anuncioService;
		this.profesorService=profesorService;
		this.alumnoService=alumnoService;
	}
	
	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping(value = "/ofertas/new")
	public String initCreationForm(Map<String, Object> model) {
		Anuncio anuncio = new Anuncio();
		model.put("anuncio", anuncio);
		return VIEWS_ANUNCIO_CREATE_FORM;
	}
	
	@PostMapping(value = "/ofertas/new")
	public String processCreationForm(@Valid Anuncio anuncio, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_ANUNCIO_CREATE_FORM;
		}
		else {
			//creating profesor, user and authorities
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String username= clienteDetails.getUsername();
			Profesor usuario = profesorService.findProfesorByUsername(username);
			anuncio.setProfesor(usuario);
			
			this.anuncioService.saveAnuncio(anuncio);
			return "redirect:/ofertas/misOfertas";
		}
		
		}
	
	@GetMapping(value="/ofertas/misOfertas")
	public String findMisAnuncios(Map<String, Object> model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Profesor usuario = profesorService.findProfesorByUsername(username);
		Collection<Anuncio> anuncios= anuncioService.findByUsuario(usuario.getId());
		model.put("anuncios", anuncios);
		return "anuncios/misAnuncios";
	}
	
	@GetMapping(value="/ofertas/find")
	public String findAnuncios(Map<String, Object> model) {
//		List<Anuncio> anuncios = this.anuncioService.findAll();
		model.put("anuncios", new Anuncio());
		return "anuncios/anuncioList";
	}
	
	@GetMapping(value="/ofertas/find/{asignatura}")
	public String findAnunciosByAsignatura(@PathVariable("asignatura") String asignatura, Map<String, Object> model) {
		List<Anuncio> anuncios = (List<Anuncio>) this.anuncioService.findByAsignatura(asignatura);
		model.put("anuncios", anuncios);
		return "anuncios/anuncioList";
	}
	
	@GetMapping(value = "/ofertas/{anuncioId}/edit")
	public String initUpdateAnuncioForm(@PathVariable("anuncioId") int anuncioId, ModelMap model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Profesor usuario = profesorService.findProfesorByUsername(username);
		Anuncio anuncio = this.anuncioService.findAnuncioById(anuncioId);
		if (usuario.equals(anuncio.getProfesor())) {
			anuncio.setId(anuncioId);
			model.put("anuncio",anuncio);
			
			return VIEWS_ANUNCIO_CREATE_FORM;
		}
		else {
			return "welcome";
		}
	}

	@PostMapping(value = "/ofertas/{anuncioId}/edit")
	public String processUpdateAnuncioForm(@Valid Anuncio anuncio, BindingResult result, ModelMap model,
			@PathVariable("anuncioId") int anuncioId) {
		anuncio.setId(anuncioId);
		Collection<Profesor> profesores = profesorService.findProfesores();
		model.put("profesor", profesores);
		if (result.hasErrors()) {
			model.put("anuncio",anuncio);
			return VIEWS_ANUNCIO_CREATE_FORM;
		}
		else {
			anuncio.setId(anuncioId);
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String username= clienteDetails.getUsername();
			Profesor usuario = profesorService.findProfesorByUsername(username);
			anuncio.setProfesor(usuario);
			
			this.anuncioService.saveAnuncio(anuncio);
			return "redirect:/ofertas/misOfertas";
		}
	}
	@GetMapping(value = "/anuncio/{anuncioId}/delete")
	public String deleteAnuncio(@PathVariable("anuncioId") int anuncioId, Model model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Profesor usuario = profesorService.findProfesorByUsername(username);
		Anuncio anuncio = this.anuncioService.findAnuncioById(anuncioId);
		if (usuario.equals(anuncio.getProfesor())) {
		this.anuncioService.delete(anuncio);
		return "redirect:/ofertas/misOfertas";
	}
		else {
			return "welcome";
		}
	}
	
	@GetMapping("/anuncio/{anuncioId}")
	public ModelAndView viewAnuncio(@PathVariable("anuncioId")int anuncioId) {
		ModelAndView mav = new ModelAndView("anuncios/anuncio");
		Anuncio anuncio=this.anuncioService.findAnuncioById(anuncioId);
		mav.addObject("anuncio", anuncio);
		return mav;
	}
	
	@GetMapping("/anuncio/{anuncioId}/apply")
	public String anuncioApply(@PathVariable("anuncioId")int anuncioId) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Alumno alumno = this.alumnoService.findAlumnoByUsername(username);
		this.anuncioService.aplyAnuncio(alumno, anuncioId);
		return "redirect:/logged";
	}

	@GetMapping("/anuncio/anunciosAplicados")
	public String anunciosAplied(Map<String, Object> model){
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Alumno alumno = this.alumnoService.findAlumnoByUsername(username);
		List<Anuncio> lista=this.anuncioService.appliedAnuncio(alumno);
		model.put("anuncios", lista);
		return "anuncios/list";
	}
}