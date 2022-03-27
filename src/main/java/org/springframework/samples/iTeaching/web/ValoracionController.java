package org.springframework.samples.iTeaching.web;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.Sala;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.model.Valoracion;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AnuncioService;
import org.springframework.samples.iTeaching.service.SalaService;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.samples.iTeaching.service.ValoracionService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ValoracionController {

	@Autowired
	private ValoracionService valoracionService;
	
	@Autowired
	private UserService userService;
	@Autowired
	private AnuncioService anuncioService;
	
	@Autowired
	private AlumnoService alumnoService;
	
	private static final String VIEWS_VALORACION_CREATE_FORM = "valoraciones/createValoracionForm";
	
	@GetMapping(value = "/anuncio/{anuncioId}/valoraciones/new")
	public String initCreationForm(Map<String, Object> model,@PathVariable("anuncioId") int anuncioId) {
		Valoracion valoracion = new Valoracion();
		Anuncio anuncio= this.anuncioService.findAnuncioById(anuncioId);
		valoracion.setAnuncio(anuncio);
		model.put("valoracion", valoracion); 
		
		return VIEWS_VALORACION_CREATE_FORM;
	}
	
	@PostMapping(value = "/anuncio/{anuncioId}/valoraciones/new")
	public String processCreationForm(@Valid Valoracion valoracion,@PathVariable("anuncioId") int anuncioId, BindingResult result) throws IOException, InterruptedException {
		if (result.hasErrors()) {
			return VIEWS_VALORACION_CREATE_FORM;
		}
		else {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();
            Alumno alumno = this.alumnoService.findAlumnoByUsername(user.getUsername());
            Anuncio anuncio= this.anuncioService.findAnuncioById(anuncioId);
            Profesor profesor=anuncio.getProfesor();
            valoracion.setAnuncio(anuncio);
            valoracion.setAlumno(alumno);
            valoracion.setProfesor(profesor);
            valoracionService.saveValoracion(valoracion);
			return "redirect:/ofertas/find";
		}
		
		}
}
