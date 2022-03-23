package org.springframework.samples.iTeaching.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class AlumnoValidator implements Validator {

	private AlumnoService aluSer;
	
	@Autowired
	public AlumnoValidator(AlumnoService aS) {
		this.aluSer = aS;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Alumno.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Alumno alumno = (Alumno) obj;
		Alumno alumnoRegistered = this.aluSer.findAlumnoByUsername(alumno.getUser().getUsername());
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
		if (alumnoRegistered!=null) {
			errors.rejectValue("user.username", "Este username ya esta en uso", "Este username ya esta en uso");

		}
		if (alumno.getUser().getUsername().isBlank()) {
			errors.rejectValue("user.username", "No puede dejar este campo vacío", "No puede dejar este campo vacío");
		}
		if (alumno.getUser().getUsername().length() < 6) {
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
