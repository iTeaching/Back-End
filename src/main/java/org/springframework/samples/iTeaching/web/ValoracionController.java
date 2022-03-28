package org.springframework.samples.iTeaching.web;

import java.io.IOException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.model.Valoracion;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AsignaturaService;
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
	private AsignaturaService asignaturaService;
	
	@Autowired
	private AlumnoService alumnoService;
	
	private static final String VIEWS_VALORACION_CREATE_FORM = "valoraciones/createValoracionForm";
	
	@GetMapping(value = "/asignatura/{asignaturaId}/valoraciones/new")
	public String initCreationForm(Map<String, Object> model,@PathVariable("asignaturaId") int asignaturaId) {
		Valoracion valoracion = new Valoracion();
		Asignatura asignatura= this.asignaturaService.findById(asignaturaId);
		valoracion.setAsignatura(asignatura);
		model.put("valoracion", valoracion); 
		
		return VIEWS_VALORACION_CREATE_FORM;
	}
	
	@PostMapping(value = "/asignatura/{asignaturaId}/valoraciones/new")
	public String processCreationForm(@Valid Valoracion valoracion,@PathVariable("asignaturaId") int asignaturaId, BindingResult result) throws IOException, InterruptedException {
		if (result.hasErrors()) {
			return VIEWS_VALORACION_CREATE_FORM;
		}
		else {
			UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();
            Alumno alumno = this.alumnoService.findAlumnoByUsername(user.getUsername());
            Asignatura asignatura= this.asignaturaService.findById(asignaturaId);
            Profesor profesor=asignatura.getProfesor();
            if(this.valoracionService.findValoracionByAlumnoAsignatura(alumno, asignatura).isPresent()){
            	Valoracion valoracionPrevia= this.valoracionService.
            			findValoracionByAlumnoAsignatura(alumno, asignatura).get();
            	valoracionService.delete(valoracionPrevia);
            			profesor.setPuntuacion(profesor.getPuntuacion()-valoracionPrevia.getPuntuacion());
            			profesor.setDivision(profesor.getDivision()-1);
            }
            valoracion.setAsignatura(asignatura);
            valoracion.setAlumno(alumno);
            valoracion.setProfesor(profesor);
            profesor.setPuntuacion(valoracion.getPuntuacion()+profesor.getPuntuacion());
            profesor.setDivision(profesor.getDivision()+1);
            valoracionService.saveValoracion(valoracion);
			return "redirect:/ofertas/find";
		}
		
		}
}
