package org.springframework.samples.iTeaching.web;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProfesorValidator implements Validator {

private ProfesorService profSer;
	
	@Autowired
	public ProfesorValidator(ProfesorService pS) {
		this.profSer = pS;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Profesor.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {

		Profesor profesor = (Profesor) obj;
		Profesor profesorRegistered = this.profSer.findProfesorByUsername(profesor.getUser().getUsername());
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
		if (profesorRegistered != null) {
			errors.rejectValue("user.username", "Este username ya esta en uso", "Este username ya esta en uso");

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
