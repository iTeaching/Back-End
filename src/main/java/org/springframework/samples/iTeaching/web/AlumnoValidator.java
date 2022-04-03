package org.springframework.samples.iTeaching.web;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AlumnoValidator implements Validator {



	private AlumnoService aluSer;
	
	@Autowired
	public AlumnoValidator(AlumnoService aS) {
		this.aluSer = aS;
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
		return Alumno.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		String nombreLogeado = nombreUsuarioLogeado();
		Alumno alumno = (Alumno) obj;

		if (alumno.getFirstName().isBlank()) {
			errors.rejectValue("firstName", " No puede dejar el campo vacio", "No puede dejar el campo vacio");

		}
		if (alumno.getFirstName().length() < 3) {
			errors.rejectValue("firstName", "El nombre debe ser de mayor a 3 caracteres",
					"El nombre debe ser mayor a 3 caracteres");
		}
		if (alumno.getLastName().isBlank()) {
			errors.rejectValue("lastName", " No puede dejar el campo vacio", "No puede dejar el campo vacio");

		}
		if (alumno.getLastName().length() < 3) {
			errors.rejectValue("lastName", "El apellido debe ser mayor a 3 caracteres",
					"El apellido debe ser mayor a 3 caracteres");
		}
		if (alumno.getEmail().isBlank()) {
			errors.rejectValue("email", " No puede dejar el campo vacio", "No puede dejar el campo vacio");

		}
		if(alumno.getTelephone().length()<9) {
			errors.rejectValue("telephone", " El telefono tiene que tener 9 numeros", "El telefono tiene que tener 9 numeros");
		}
		
		if(nombreLogeado=="noLogin") {
			List<Alumno> listaAlumnos = aluSer.findAll();
			for(Alumno alumnoIndividual:listaAlumnos) {
				if(alumno.getUser().getUsername().equals(alumnoIndividual.getUser().getUsername())) {
					errors.rejectValue("user.username", " El nombre de usuario ya existe", "El nombre de usuario ya existe");
				}
			}
		} else {
			UserDetails alumnoDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String alumnoNombre = alumno.getUser().getUsername();
			String alumnoLogeado = alumnoDetails.getUsername();
			if(!alumnoDetails.getUsername().equals(alumno.getUser().getUsername())) {
				errors.rejectValue("user.username", " No es posible cambiar el nombre de usuario. Introduzca el mismo nombre de usuario.", "No es posible cambiar el nombre de usuario. Introduzca el mismo nombre de usuario.");
			}
		}

		if (alumno.getUser().getUsername()!=null&&alumno.getUser().getUsername().isBlank()) {
			errors.rejectValue("user.username", "No puede dejar este campo vacío", "No puede dejar este campo vacío");
		}
		if (alumno.getUser().getUsername()!=null&&alumno.getUser().getUsername().length() < 6) {
			errors.rejectValue("user.username", "El nombre de usuario debe tener al menos 6 caracteres",
					"El nombre de usuario debe tener al menos 6 caracteres");
		}
		if (alumno.getUser().getPassword().isBlank()) {
			errors.rejectValue("user.password", " No puede dejar este campo vacío", "No puede dejar este campo vacío");
		}
		if (!isValid(alumno.getUser().getPassword())) {
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
