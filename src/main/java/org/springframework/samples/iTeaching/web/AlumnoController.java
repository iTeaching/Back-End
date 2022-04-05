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
public class AlumnoController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "alumnos/createOrUpdateAlumnoForm";
	
	private StorageService storageService;	
	private AlumnoService alumnoService;
	private AuthoritiesService authService;
	private UserService userService;
	private ClaseService claseService;
	private ProfesorService profesorService;
	private AsignaturaService asignaturaService;
	@Autowired
	public AlumnoController(StorageService storageService, AlumnoService alumnoService, AuthoritiesService authService, UserService userService, ClaseService claseService, ProfesorService profesorService, AsignaturaService asignaturaService) {
		this.alumnoService = alumnoService;
		this.authService = authService;
		this.userService = userService;
		this.storageService = storageService;
		this.claseService = claseService;
		this.profesorService = profesorService;
		this.asignaturaService = asignaturaService;
	}

	@InitBinder("alumno")
	public void initVehiculoBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new AlumnoValidator(alumnoService));
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/alumnos/new")
	public String initCreationForm(Map<String, Object> model) {
		Alumno alumno = new Alumno();
		model.put("alumno", alumno);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/alumnos/new")
	public String processCreationForm(@Valid Alumno alumno, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating alumno, user and authorities
			alumno.getUser().setEnabled(true);
			this.alumnoService.saveAlumno(alumno);
			authService.saveAuthorities(alumno.getUser().getUsername(), "alumno");
			
			return "redirect:/login";
		}
	}

	@GetMapping(value = "/alumnos/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("alumno", new Alumno());
		return "alumnos/findOwners";
	}



	@GetMapping(value = "/alumnos/{alumnoId}/edit")
	public String initUpdateOwnerForm(@PathVariable("alumnoId") int alumnoId, Model model) {
		Alumno alumno = this.alumnoService.findAlumnoById(alumnoId);
		model.addAttribute("alumno", alumno);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/alumnos/{alumnoId}/edit")
	public String processUpdateOwnerForm(@Valid Alumno alumno, BindingResult result,
			@PathVariable("alumnoId") int alumnoId) {

		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		} else {
				alumno.setId(alumnoId);
				alumno.getUser().setEnabled(true);
				this.alumnoService.saveAlumno(alumno);
				return "redirect:/alumnos/miPerfil";
		}
	}

	@GetMapping("/alumnos/{alumnoId}")
	public ModelAndView showOwner(@PathVariable("alumnoId") int alumnoId) {
		ModelAndView mav = new ModelAndView("alumnos/alumnoDetails");
		Alumno alumno=this.alumnoService.findAlumnoById(alumnoId);
		mav.addObject("alumno",alumno);
		return mav;
	}
	@PostMapping("/alumnos/{alumnoId}")
	public String deleteAlumno(@PathVariable("alumnoId") int alumnoId) {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Alumno usuario = alumnoService.findAlumnoByUsername(username);
		Alumno alumno = alumnoService.findAlumnoById(alumnoId);
		if (usuario.equals(alumno)) {
		this.alumnoService.delete(alumno);
		return "welcome";
	}
		else {
			return "welcome";
		}
	}
	
	
	@GetMapping(value = "/alumnos/miPerfil")
	public String miPerfil(Model model) {		
		
		try {	// si está logueado
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username= clienteDetails.getUsername();
			System.out.println(username);
			Alumno usuario = alumnoService.findAlumnoByUsername(username);
			List<Clase> listaClase = claseService.findAll();
			
			List<Clase> clase = claseService.findAlumnoByUsername(username);
			//model.addAttribute("listaClase", listaClase);
			model.addAttribute("listaClase", clase);
			model.addAttribute("alumno", usuario);
			return "alumnos/miPerfil";
			
		}catch(Exception e) {	// si no está logueado
			return "redirect:/";
		}
		
	}
	
	@GetMapping(value="alumnos/{alumnoId}/nuevaClase")
	public String crearClase(Map<String,Object> model, @PathVariable("alumnoId") int alumnoIdPagina) {
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
		
		
		
		
		Alumno alumnoActual= alumnoService.findAlumnoById(alumnoIdPagina);
		List<Asignatura> conjuntoAsignaturas= alumnoActual.getAsignaturas();
		Set<Profesor> setProfesor= new HashSet<Profesor>();
		for(Asignatura asing: conjuntoAsignaturas) {
			setProfesor.add(asing.getProfesor());
		}
		
		
		Map<Profesor, List<Asignatura>> map = new HashMap<Profesor, List<Asignatura>>();
		
		for(Profesor profes: setProfesor) {
			List<String> listaCosas = alumnoService.findByIdAlumnosProfesores(profes.getId(), alumnoIdPagina);
			System.out.println(listaCosas.size());
			int tamanyo = listaCosas.size();
			int i =0;
			while(i<tamanyo) { 
				System.out.println(listaCosas.get(i));
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
		System.out.println(map);
		
		return "alumnos/nuevaClase";
	}
	
	@PostMapping(value="alumnos/{alumnoId}/nuevaClase")
	public String crearClasePost(@Valid Clase clase, BindingResult result, @PathVariable("alumnoId") int alumnoIdPagina) {
		if (result.hasErrors()) {
			return "alumnos/{alumnoId}/nuevaClase";
		}
		else {
			//creating alumno, user and authorities
			UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username= clienteDetails.getUsername();
			Alumno usuario = alumnoService.findAlumnoByUsername(username);
			Asignatura asignatura = asignaturaService.findById(clase.getAsignatura().getId());
			clase.setAceptacionAlumno(true);
			clase.setAceptacionProfesor(false);
			clase.setAsignatura(asignatura);
			clase.setAlumno(usuario);
			clase.getAsignatura().getProfesor();
			clase.setProfesor(clase.getAsignatura().getProfesor());
			this.claseService.saveClase(clase);

			return "redirect:/alumnos/miPerfil";
		}
	}

	
	@GetMapping(value="/alumnos/miPerfil/changeAvatar/{alumnoId}")
	public String viewChangeAvatar(@PathVariable("alumnoId") int alumnoId, 
			Map<String,Object> model) {
		Alumno alumno = this.alumnoService.findAlumnoById(alumnoId);
		model.put("alumno", alumno);
		return "alumnos/changeAvatar";
	}
//	
//	
//	
//	
//	@PostMapping(value = "/alumnos/miPerfil/changeAvatar")
//	public RedirectView saveChangeAvatar(@RequestParam("avatar") MultipartFile avatar) throws IOException {
//		//String fileName = storageService.store(avatar, "profile", http);
//		
//		
//		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		Alumno alumno = alumnoService.findAlumnoByUsername(userDetails.getUsername());
//		
//		String fileName = StringUtils.cleanPath(avatar.getOriginalFilename());
//        alumno.setAvatar(fileName);
//        alumnoService.saveAlumno(alumno);
//        String uploadDir = "/resources/images/profile/" + alumno.getId();
//        storageService.saveFile(uploadDir, fileName, avatar);
//		
//		return new  RedirectView("/alumnos/miPerfil", true);
//	}
	
//	@GetMapping(value = "alumnos/miPerfil/changeAvatar/{usernameProfile}")
//	public String viewChangeAvatar(@PathVariable("usernameProfile") String usernameProfile, Map<String, Object> model) {
//		Alumno userOptional = alumnoService.findAlumnoByUsername(usernameProfile);
//		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (!userOptional.getUser().equals(userDetails.getUsername())) {
//			return "exception";
//		} else {
//			model.put("user", userOptional);
//			return "alumnos/changeAvatar";
//		}
//	}

	@PostMapping(value = "alumnos/miPerfil/changeAvatar")
	public String saveChangeAvatar(@RequestParam("avatar") MultipartFile avatar, HttpSession http) {
		String fileName = storageService.store(avatar, "profile", http);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Alumno alumno = alumnoService.findAlumnoByUsername(userDetails.getUsername());
		alumno.setAvatar(fileName);
		alumnoService.saveAlumno(alumno);
		return "redirect:/alumnos/miPerfil";
	}
	
	
	
}
