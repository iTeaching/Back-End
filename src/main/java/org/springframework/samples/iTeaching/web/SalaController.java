package org.springframework.samples.iTeaching.web;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.Sala;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.samples.iTeaching.service.SalaService;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SalaController {
	
	@Autowired
	private SalaService salaService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProfesorService profesorService;
	private static final String VIEWS_SALA_CREATE_FORM = "salas/createSalaForm";

	@Autowired
	public SalaController(SalaService salaService, ProfesorService profesorService,UserService userService) {
		this.salaService=salaService;
		this.profesorService=profesorService;
		this.userService=userService;
	}
	
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
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();
            Profesor profesor = this.profesorService.findProfesorByUsername(user.getUsername());
            sala.setProfesor(profesor);
			this.salaService.saveSala(sala);
			return "redirect:/welcome";
		}
		
		}
	
	
	@GetMapping(value = "/alumnos/{alumnoId}/salas")
	public String viewSalas(@PathVariable("alumnoId") int id, Map<String, Object> model) {
		List<Sala> salas = this.salaService.findAll();
		Alumno alumno = this.alumnoService.findAlumnoById(id);
		salas.stream().filter(s -> s.getAlumnos().contains(alumno)).collect(Collectors.toList());
		model.put("salas", salas);
		return "salas/list";
	}
	
	@GetMapping (value ="/salas")
	public String mySalas(Map<String, Object> model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Profesor usuario = profesorService.findProfesorByUsername(username);
		List<Sala> salas= (List<Sala>) this.salaService.findByUsuario(usuario.getId());
		model.put("salas",salas);
		for (int i=0;i<salas.size();i++) {
			List<Alumno> alumnos = salas.get(i).getAlumnos();
			model.put("alumnos",alumnos);
		}
		return "salas/list";
	}
}