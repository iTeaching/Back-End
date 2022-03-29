package org.springframework.samples.iTeaching.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.StorageService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;



@Controller
public class AlumnoController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "alumnos/createOrUpdateAlumnoForm";
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private AlumnoService alumnoService;

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
			this.alumnoService.saveAlumno(alumno);
			
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
		alumno.setId(alumnoId);
		model.addAttribute(alumno);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/alumnos/{alumnoId}/edit")
	public String processUpdateOwnerForm(@Valid Alumno alumno, BindingResult result,
			@PathVariable("alumnoId") int alumnoId) {
				alumno.setId(alumnoId);
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.alumnoService.saveAlumno(alumno);
			return "redirect:/alumnos/{alumnoId}";
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
			
			model.addAttribute("alumno", usuario);
			return "alumnos/miPerfil";
			
		}catch(Exception e) {	// si no está logueado
			return "redirect:/";
		}
		
	}

	
	@GetMapping(value="/alumnos/miPerfil/changeAvatar/{alumnoId}")
	public String viewChangeAvatar(@PathVariable("alumnoId") int alumnoId, 
			Map<String,Object> model) {
		Alumno alumno = this.alumnoService.findAlumnoById(alumnoId);
		model.put("alumno", alumno);
		return "alumnos/changeAvatar";
	}
	
	
	
	
	@PostMapping(value = "/alumnos/miPerfil/changeAvatar")
	public RedirectView saveChangeAvatar(@RequestParam("avatar") MultipartFile avatar) throws IOException {
		//String fileName = storageService.store(avatar, "profile", http);
		
		
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Alumno alumno = alumnoService.findAlumnoByUsername(userDetails.getUsername());
		
		String fileName = StringUtils.cleanPath(avatar.getOriginalFilename());
        alumno.setAvatar(fileName);
        alumnoService.saveAlumno(alumno);
        String uploadDir = "/resources/images/profile/" + alumno.getId();
        storageService.saveFile(uploadDir, fileName, avatar);
		
		return new  RedirectView("/alumnos/miPerfil", true);
	}
	
	
	
}
