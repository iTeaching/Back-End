package org.springframework.samples.iTeaching.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Clase;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.model.estadoClase;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.ClaseService;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	private ClaseService claseService;

	@Autowired
	public AsignaturaController(AsignaturaService asignaturaService, ProfesorService profesorService,UserService userService, ClaseService claseService) {
		this.asignaturaService=asignaturaService;
		this.profesorService=profesorService;
		this.userService=userService;
		this.claseService=claseService;
	}
	
	@GetMapping(value = "/asignaturas/new")
	public String initCreationForm(Map<String, Object> model) {
		Asignatura a = new Asignatura();
	
		model.put("asignatura", a);
		
		return "asignaturas/createAsignaturaForm";
	}
	
	@PostMapping(value = "/asignaturas/new")
	public String processCreationForm(@Valid Asignatura asignatura, BindingResult result, Model model) throws IOException, InterruptedException {
		if (result.hasErrors()) {
			return "asignaturas/createAsignaturaForm";
		}
		else {
			if(asignatura.getPrecio()<= 0) {
				model.addAttribute("errorMessage", "No se puede introducir valores menores o iguales a 0");
				return "asignaturas/createAsignaturaForm";
			}
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
			List<Asignatura> asignaturas= this.alumnoService.findAlumnoById(usuario.getId()).getAsignaturas();
			asignaturas = asignaturas.stream().filter(a->a.getProfesor().getUser().isEnabled()==true).collect(Collectors.toList());
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
		asignaturas = asignaturas.stream().filter(a->a.getProfesor().getUser().isEnabled()==true).collect(Collectors.toList());
		model.put("asignaturas", asignaturas);
		return "asignaturas/findOfertas";
	}

	@RequestMapping(value="/ofertas/findAsignatura")
	public String findAnunciosByAsignatura(@RequestParam("asignaturaBuscar") String asignatura, Map<String, Object> model) {
		List<Asignatura> asignaturas = (List<Asignatura>) this.asignaturaService.findByNombre(asignatura);
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Alumno alumno = this.alumnoService.findAlumnoByUsername(username);
		List<Asignatura> lista=this.asignaturaService.appliedAnuncio(alumno);
		asignaturas.removeAll(lista);
		asignaturas = asignaturas.stream().filter(a->a.getProfesor().getUser().isEnabled()==true).collect(Collectors.toList());
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

	@GetMapping(value="/asignaturas/{asignaturaId}/nuevaClase")
	public String crearClase(Map<String,Object> model, @PathVariable("asignaturaId") int id) {
		System.out.println("hola");
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username= clienteDetails.getUsername();
		Alumno usuario = alumnoService.findAlumnoByUsername(username);
		model.put("alumno", usuario);
		Clase clase = new Clase();
		model.put("clase", clase);
		List<Profesor> listaProfesores = profesorService.findAll();
		model.put("listaProfesores", listaProfesores);
		List<Asignatura> listaAsignaturas = asignaturaService.findAll();
		model.put("listaAsignaturas", listaAsignaturas);
		
		
		List<Asignatura> conjuntoAsignaturas= usuario.getAsignaturas();
		Set<Profesor> setProfesor= new HashSet<Profesor>();
		for(Asignatura asing: conjuntoAsignaturas) {
			setProfesor.add(asing.getProfesor());
		}
		
		
		Map<Profesor, List<Asignatura>> map = new HashMap<Profesor, List<Asignatura>>();
		
		for(Profesor profes: setProfesor) {
			List<String> listaCosas = alumnoService.findByIdAlumnosProfesores(profes.getId(), usuario.getId());
			int tamanyo = listaCosas.size();
			int i =0;
			while(i<tamanyo) { 
				String elemento[] = listaCosas.get(i).split(",");
				int idAsignatura = Integer.valueOf(elemento[0]);
				int idAlumno = Integer.valueOf(elemento[1]);
				int idProfesor = Integer.valueOf(elemento[2]);
				Profesor profesor = profesorService.findProfesorById(idProfesor);
				Asignatura asignatura = asignaturaService.findById(idAsignatura);
				
				if(map.containsKey(profesor)) {
					map.get(profesor).add(asignatura);
				} else {
					map.put(profesor, new ArrayList<>());
					map.get(profesor).add(asignatura);
					}
				
				i++;
				}
		}
		
		
		model.put("diccionario", map);		
		return "asignaturas/nuevaClase";
	}
	@PostMapping(value="/asignaturas/{asignaturaId}/nuevaClase")
	public String crearClasePost(@Valid Clase clase, BindingResult result, @PathVariable("asignaturaId") int id) {
		if (result.hasErrors()) {
			return "asignaturas/" + id + "/nuevaClase";
		}
		else {
			//creating alumno, user and authorities
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username= clienteDetails.getUsername();
			Alumno usuario = alumnoService.findAlumnoByUsername(username);
			Asignatura asignatura = asignaturaService.findById(id);
			Profesor profesor = asignatura.getProfesor();
			clase.setAceptacionAlumno(true);
			clase.setAceptacionProfesor(false);
			clase.setAsignatura(asignatura);
			clase.setAlumno(usuario);
			clase.getAsignatura().getProfesor();
			clase.setProfesor(profesor);
			clase.setEstadoClase(estadoClase.solicitada);
			this.claseService.saveClase(clase);
;
			return "redirect:/alumnos/miPerfil";
		}
	}
}
