package org.springframework.samples.iTeaching.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Clase;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.model.estadoClase;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.AuthoritiesService;
import org.springframework.samples.iTeaching.service.ClaseService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.samples.iTeaching.service.StorageService;
import org.springframework.samples.iTeaching.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfesorController {
	private static final String VIEWS_PROFESOR_CREATE_OR_UPDATE_FORM = "profesores/createOrUpdateProfesorForm";


	private ProfesorService profesorService;
	private StorageService storageService;
	private AuthoritiesService authService;
	private UserService userService;
	private AsignaturaService asignaturaService;
	private AlumnoService alumnoService;
	private ClaseService claseService;
	@Autowired
	public ProfesorController(StorageService storageService, ProfesorService profesorService, ClaseService claseService, 
				AuthoritiesService authService, UserService userService, AsignaturaService asignaturaService, AlumnoService alumnoService) {
		this.profesorService = profesorService;
		this.authService = authService;
		this.userService = userService;
		this.storageService = storageService;
		this.asignaturaService= asignaturaService;
		this.alumnoService= alumnoService;
		this.claseService= claseService;
	}
	
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
			profesor.setDivision(0);
			profesor.setPuntuacion(0.);
			profesor.getUser().setEnabled(true);
			this.profesorService.saveProfesor(profesor);
			authService.saveAuthorities(profesor.getUser().getUsername(), "profesor");
			
			return "redirect:/login";
		}
	}

	@GetMapping(value = "/profesores/{profesorId}/edit")
	public String initUpdateOwnerForm(@PathVariable("profesorId") int profesorId, Model model) {
		Profesor profesor = profesorService.findProfesorById(profesorId);
		model.addAttribute("profesor", profesor);
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
			profesor.getUser().setEnabled(true);
			this.profesorService.saveProfesor(profesor);
			return "redirect:/profesores/miPerfil";
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
			Profesor usuario = profesorService.findProfesorByUsername(username);
			List<Clase> clase = claseService.findProfesorByUsername(username);	
			
			List<Clase> listaSolicitada = new ArrayList<>();
			List<Clase> listaCancelada = new ArrayList<>();
			List<Clase> listaConfirmada = new ArrayList<>();
			List<Clase> listaFinalizada = new ArrayList<>();

			for(Clase claseIndividual:clase) {
				if(claseIndividual.getEstadoClase().equals(estadoClase.solicitada)) {
					listaSolicitada.add(claseIndividual);
				} else if(claseIndividual.getEstadoClase().equals(estadoClase.confirmada)) {
					listaConfirmada.add(claseIndividual);
				}else if(claseIndividual.getEstadoClase().equals(estadoClase.finalizada)) {
					listaFinalizada.add(claseIndividual);
				} else {
					listaCancelada.add(claseIndividual);
				}
			}

			model.addAttribute("listaSolicitada", listaSolicitada);
			model.addAttribute("listaCancelada", listaCancelada);
			model.addAttribute("listaConfirmada", listaConfirmada);
			model.addAttribute("listaFinalizada", listaFinalizada);
			model.addAttribute("profesor", usuario);
			return "profesores/miPerfil";
		}catch(Exception e) {	// si no está logueado
			return "redirect:/";
		}
		
	}
	
	@GetMapping(value="/profesor/miPerfil/changeAvatar/{profesorId}")
	public String viewChangeAvatar(@PathVariable("profesorId") int profesorId, 
			Map<String,Object> model) {
		Profesor profesor = this.profesorService.findProfesorById(profesorId);
		model.put("profesor", profesor);
		return "profesores/changeAvatar";
	}


	@PostMapping(value = "profesor/miPerfil/changeAvatar")
	public String saveChangeAvatar(@RequestParam("avatar") MultipartFile avatar, HttpSession http) {
		String fileName = storageService.store(avatar, "profile", http);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Profesor profesor = profesorService.findProfesorByUsername(userDetails.getUsername());
		profesor.setAvatar(fileName);
		profesorService.saveProfesor(profesor);
		return "redirect:/profesores/miPerfil";
	}
	
	
	@GetMapping(value="profesor/nuevaClase")
	public String crearClase(Map<String,Object> model) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username= clienteDetails.getUsername();
		Profesor usuario = profesorService.findProfesorByUsername(username);
		model.put("profesor", usuario);
		Clase clase = new Clase();
		model.put("clase", clase);
		List<Alumno> listaAlumnos = alumnoService.findAll();
		model.put("listaAlumnos", listaAlumnos);
		List<Asignatura> listaAsignaturas = asignaturaService.findAll();
		model.put("listaAsignaturas", listaAsignaturas);
		
		
		//Lista de alumnos asignados a ti como profesor
		//recorre la lista y hace lo siquiente iterando
		
		
		
		Set<Asignatura> conjuntoAsignaturas= usuario.getAsignaturas();
		Set<Alumno> setAlumnos= new HashSet<Alumno>();
		for(Asignatura asing: conjuntoAsignaturas) {
			setAlumnos.addAll(asing.getAlumnos());
		}
		
		
		Map<Alumno, List<Asignatura>> map = new HashMap<Alumno, List<Asignatura>>();
		
		for(Alumno niños: setAlumnos) {
			
			List<String> listaCosas = profesorService.findByIdAlumnosProfesores(usuario.getId(), niños.getId());
			int tamanyo = listaCosas.size();
			int i =0;
			
			while(i<tamanyo) { 
				String elemento[] = listaCosas.get(i).split(",");
				int idAsignatura = Integer.valueOf(elemento[0]);
				int idAlumno = Integer.valueOf(elemento[1]);
				int idProfesor = Integer.valueOf(elemento[2]);
				Alumno alumno = alumnoService.findAlumnoById(idAlumno);
				
				Asignatura asignatura = asignaturaService.findById(idAsignatura);
				
				if(map.containsKey(alumno)) {
					map.get(alumno).add(asignatura);
				} else {
					map.put(alumno, new ArrayList<>());
					map.get(alumno).add(asignatura);
					}
				
				i++;
				}
		}
		
			
		
		model.put("diccionario", map);
		
		return "profesores/nuevaClase";
	}
	
	@PostMapping(value="profesor/nuevaClase")
	public String crearClasePost(@Valid Clase clase, BindingResult result) {
		if (result.hasErrors()) {
			return "profesor/nuevaClase";
		}
		else {
			//creating alumno, user and authorities
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username= clienteDetails.getUsername();
			Profesor usuario = profesorService.findProfesorByUsername(username);
			Asignatura asignatura = asignaturaService.findById(clase.getAsignatura().getId());
			clase.setAceptacionAlumno(false);
			clase.setAceptacionProfesor(true);
			clase.setAsignatura(asignatura);
			
			
			clase.setAlumno(clase.getAlumno());
			clase.getAsignatura().getProfesor();
			
			clase.setProfesor(usuario);
			clase.setEstadoClase(estadoClase.solicitada);
			this.claseService.saveClase(clase);

			return "redirect:/profesores/miPerfil";
		}
	}
	
	
	@GetMapping(value="/profesor/aceptar/{claseId}")
	public String aceptarClase(@PathVariable("claseId") int claseId, 
			Map<String,Object> model) {
		Clase clase = claseService.findById(claseId);
		model.put("clase", clase);
		return "profesores/aceptarClase";
	}

	@PostMapping(value="/profesor/aceptar/{claseId}")
	public String aceptarClasePost(@Valid Clase clase,@PathVariable("claseId") int claseId, 
			Map<String,Object> model, BindingResult result) {
		if (result.hasErrors()) {
			return "profesores/{profesorId}/nuevaClase";
		}
		else {
			//creating prof, user and authorities
			clase.setId(claseId);
			clase.setEstadoClase(estadoClase.confirmada);
			this.claseService.saveClase(clase);
			return "redirect:/profesores/miPerfil";
		}
	}
	
	@GetMapping(value="/profesor/cancelar/{claseId}")
	public String cancelarClase(@PathVariable("claseId") int claseId, 
			Map<String,Object> model) {
		Clase clase = claseService.findById(claseId);
		model.put("clase", clase);
		return "profesores/cancelarClase";
	}

	@PostMapping(value="/profesor/cancelar/{claseId}")
	public String cancelarClasePost(@Valid Clase clase,@PathVariable("claseId") int claseId, 
			Map<String,Object> model, BindingResult result) {
		if (result.hasErrors()) {
			return "profesores/cancelarClase";
		}
		else {
			//creating alumno, user and authorities
			clase.setId(claseId);
			clase.setEstadoClase(estadoClase.cancelada);
			this.claseService.saveClase(clase);
			return "redirect:/profesores/miPerfil";
	

	
	
	
		}

	
	

	
	
	
	}
}


	
	
	
	
	
	
	
	

