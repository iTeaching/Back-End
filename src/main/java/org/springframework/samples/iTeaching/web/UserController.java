package org.springframework.samples.iTeaching.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

	private static final String VIEWS_OWNER_CREATE_FORM = "users/createOwnerForm";

	private final AlumnoService alumnoService;

	@Autowired
	public UserController(AlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}


	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping("/login")
	public String login(Model model,String error,String logout){
		if(error!=null){
			model.addAttribute("error", "Tu usuario y contraseña es incorrecta");
		}
		if(logout!=null){
			model.addAttribute("message", "Has entrado correctamente");
		}else {
			SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
		}
		return "login";
		
	}
}
