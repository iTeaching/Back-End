package org.springframework.samples.iTeaching.web;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProfesorValidator implements Validator {

private ProfesorService profSer;
private AlumnoService alumnSer;
	
	@Autowired
	public ProfesorValidator(ProfesorService pS, AlumnoService aS) {
		this.profSer = pS;
		this.alumnSer = aS;
	}
	
	public String nombreUsuarioLogeado() {
		Authentication quePasa = SecurityContextHolder.getContext().getAuthentication();
		
		if(quePasa instanceof AnonymousAuthenticationToken) {
			String result = "noLogin";
			return result;
		}else {
			UserDetails alumnoDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String result = alumnoDetails.getUsername();
			return result;
		}	
	}
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Profesor.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		String nombreLogeado = nombreUsuarioLogeado();
		Profesor profesor = (Profesor) obj;

		if (profesor.getFirstName().isBlank()) {
			errors.rejectValue("firstName", " No puede dejar el campo vacio", "No puede dejar el campo vacio");

		}
		if (profesor.getFirstName().length() < 3) {
			errors.rejectValue("firstName", "El nombre debe ser de mayor a 3 caracteres",
					"El nombre debe ser mayor a 3 caracteres");
		}
		if (profesor.getLastName().isBlank()) {
			errors.rejectValue("lastName", " No puede dejar el campo vacio", "No puede dejar el campo vacio");

		}
		if (profesor.getLastName().length() < 3) {
			errors.rejectValue("lastName", "El apellido debe ser mayor a 3 caracteres",
					"El apellido debe ser mayor a 3 caracteres");
		}
		if (profesor.getEmail().isBlank()) {
			errors.rejectValue("email", " No puede dejar el campo vacio", "No puede dejar el campo vacio");

		}
		if(profesor.getTelephone().length()<9) {
			errors.rejectValue("telephone", " El telefono tiene que tener 9 numeros", "El telefono tiene que tener 9 numeros");
		}
		
		if(nombreLogeado=="noLogin") {
			List<Profesor> listaProfesor = profSer.findAll();
			List<Alumno> listaAlumno = alumnSer.findAll();
			for(Profesor profesorIndividual:listaProfesor) {
				if(profesor.getUser().getUsername().equals(profesorIndividual.getUser().getUsername())) {
					errors.rejectValue("user.username", " El nombre de usuario ya existe", "El nombre de usuario ya existe");
				}
			}
			for(Alumno alumnoIndividual : listaAlumno) {
				if(profesor.getUser().getUsername().equals(alumnoIndividual.getUser().getUsername())) {
					errors.rejectValue("user.username", " El nombre de usuario ya existe", "El nombre de usuario ya existe");
				}
			}
		} else {
			UserDetails profesorDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String profesorNombre = profesor.getUser().getUsername();
			String profesorLogeado = profesorDetails.getUsername();
		}
		
		
		
		
		
		
		
		
		if (profesor.getUser().getUsername().isBlank()) {
			errors.rejectValue("user.username", "No puede dejar este campo vacío", "No puede dejar este campo vacío");
		}
		if (profesor.getUser().getUsername().length() < 6) {
			errors.rejectValue("user.username", "El nombre de usuario debe tener al menos 6 caracteres",
					"El nombre de usuario debe tener al menos 6 caracteres");
		}
		if (profesor.getUser().getPassword().isBlank()) {
			errors.rejectValue("user.password", " No puede dejar este campo vacío", "No puede dejar este campo vacío");
		}
		if (!isValid(profesor.getUser().getPassword())) {
			errors.rejectValue("user.password",
					" La contraseña debe contener una minúscula, una mayúscula, un número y un caracter especial como mínimo. También tiene que tener una longitud de 8 a 20 caracteres",
					"La contraseña debe contener una minúscula, una mayúscula, un número y un caracter especial como mínimo. También tiene que tener una longitud de 8 a 20 caracteres");
		}

	}
	
	private static final String pass_pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

	private static final Pattern pattern = Pattern.compile(pass_pattern);

	public static boolean isValid(String password) {
		Matcher matcher = pattern.matcher(password);
        return matcher.matches();
	}

}
