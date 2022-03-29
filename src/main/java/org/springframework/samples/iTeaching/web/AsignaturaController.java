package org.springframework.samples.iTeaching.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.Sala;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AsignaturaController {
	
	@Autowired
	private AsignaturaService asignaturaService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProfesorService profesorService;

	@Autowired
	public AsignaturaController(AsignaturaService asignaturaService, ProfesorService profesorService,UserService userService) {
		this.asignaturaService=asignaturaService;
		this.profesorService=profesorService;
		this.userService=userService;
	}
	
	@GetMapping(value = "/asignaturas/new")
	public String initCreationForm(Map<String, Object> model) {
		Asignatura a = new Asignatura();
	
		model.put("asignatura", a);
		
		return "asignaturas/createAsignaturaForm";
	}
	
	@PostMapping(value = "/asignaturas/new")
	public String processCreationForm(@Valid Asignatura asignatura, BindingResult result) throws IOException, InterruptedException {
		if (result.hasErrors()) {
			return "asignaturas/createAsignaturaForm";
		}
		else {
			//creating profesor, user and authorities
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();
            Profesor profesor = this.profesorService.findProfesorByUsername(user.getUsername());
            asignatura.setProfesor(profesor);
            asignatura.setUrl(Clases.url());
			this.asignaturaService.saveAsignatura(asignatura);
			return "redirect:/asignaturas";
		}
		
		}
	
	
//	@GetMapping(value = "/alumnos/{alumnoId}/salas")
//	public String viewSalas(@PathVariable("alumnoId") int id, Map<String, Object> model) {
//		List<Asignatura> asignaturas = this.asignaturaService.findAll();
//		Alumno alumno = this.alumnoService.findAlumnoById(id);
//		asignaturas.stream().filter(s -> s.getAlumnos().contains(alumno)).collect(Collectors.toList());
//		model.put("asignaturas", asignaturas);
//		return "salas/list";
//	}
	
	@GetMapping (value ="/asignaturas")
	public String mySalas(Map<String, Object> model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		
		try {
			Profesor usuario = profesorService.findProfesorByUsername(username);
			List<Asignatura> asignaturas= (List<Asignatura>) this.asignaturaService.findByProfesor(usuario.getId());
			model.put("asignaturas",asignaturas);
			return "asignaturas/list";
		} catch(Exception e) {
			
		}
		
		try {
			Alumno usuario = alumnoService.findAlumnoByUsername(username);
			Set<Sala> asignaturas= this.alumnoService.findAlumnoById(usuario.getId()).getSalas().stream().collect(Collectors.toSet());
			model.put("asignaturas",asignaturas);
			return "asignaturas/list";
		} catch(Exception e) {
			
		}
		
		return "redirect:/";
		
	}
	
	@GetMapping(value = "/asignaturas/{asignaturaId}")
	public String getSala(@PathVariable("asignaturaId") int id, Map<String, Object> model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
				
		Asignatura a = this.asignaturaService.findById(id);
		model.put("asignatura", a);
		
		Profesor profConPermiso = a.getProfesor();
		Set<Alumno> alumsConPermiso = a.getAlumnos();
		
		try {
			Profesor usuario = profesorService.findProfesorByUsername(username);
			if(usuario.equals(profConPermiso)) {
				return "asignaturas/view";
			}
		} catch(Exception e) {
			
		}
		
		try {
			Alumno usuario = alumnoService.findAlumnoByUsername(username);
			if(alumsConPermiso.contains(usuario)) {
				return "asignaturas/view";
			}
		} catch(Exception e) {
			
		}
		
		return "redirect:/";
	}
	
	
	@GetMapping(value="/ofertas/find")
	public String findAnuncios(Map<String, Object> model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Alumno alumno = this.alumnoService.findAlumnoByUsername(username);
		List<Asignatura> lista=this.asignaturaService.appliedAnuncio(alumno);
		List<Asignatura> asignaturas = (List<Asignatura>) this.asignaturaService.findAll();
		asignaturas.removeAll(lista);

		model.put("asignaturas", asignaturas);
		return "asignaturas/findOfertas";
	}
	
	@GetMapping(value="/ofertas/find/{asignatura}")
	public String findAnunciosByAsignatura(@PathVariable("asignatura") String asignatura, Map<String, Object> model) {
		List<Asignatura> asignaturas = (List<Asignatura>) this.asignaturaService.findByNombre(asignatura);
		model.put("asignaturas", asignaturas);
		return "asignaturas/findOfertas";
	}
	
	@GetMapping(value = "/asignaturas/{asignaturaId}/apply")
	public String suscribirAsignatura(@PathVariable("asignaturaId") int id, Map<String, Object> model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
				
		Asignatura a = this.asignaturaService.findById(id);
//		model.put("asignatura", a);
		
//		Profesor profConPermiso = a.getProfesor();
		Set<Alumno> alumsConPermiso = a.getAlumnos();
		
//		try {
//			Profesor usuario = profesorService.findProfesorByUsername(username);
//			if(usuario.equals(profConPermiso)) {
//				return "redirect:/";
//			}
//		} catch(Exception e) {
//			
//		}
		
		try {
			Alumno usuario = alumnoService.findAlumnoByUsername(username);
			if(!alumsConPermiso.contains(usuario)) {
				alumsConPermiso.add(usuario);
				a.setAlumnos(alumsConPermiso);
				this.asignaturaService.saveAsignatura(a);
				return "redirect:/asignaturas";
			}
		} catch(Exception e) {
			
		}
		
		return "redirect:/";
	}
	
}