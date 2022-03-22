package org.springframework.samples.iTeaching.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.service.ProfesorService;
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
					" La contraseña debe contener dos caracteres en minúscula, dos en mayúscula, dos números y dos caracteres especiales",
					"La contraseña debe contener dos caracteres en minúscula, dos en mayúscula, dos números y dos caracteres especiales");
		}

	}

	public static boolean isValid(String password) {
		boolean ev = true;
		// for checking if password length
		// is between 8 and 15
		if (!((password.length() >= 8) && (password.length() <= 15))) {
			ev = false;
			return ev;
		}

		// to check space
		if (password.contains(" ")) {
			ev = false;
			return ev;
		}
		if (ev) {
			int count = 0;

			// check digits from 0 to 9
			for (int i = 0; i <= 9; i++) {

				// to convert int to string
				String str1 = Integer.toString(i);

				if (password.contains(str1)) {
					count++;
				}
			}
			// Must have 2 digits
			if (count < 2) {
				ev = false;
				return ev;
			}
		}
		int special = 0;
		// for special characters

		if (password.contains("@")) {
			special++;
		}
		if (password.contains("#")) {
			special++;
		}
		if (password.contains("!")) {
			special++;
		}
		if (password.contains("~")) {
			special++;
		}
		if (password.contains("$")) {
			special++;
		}
		if (password.contains("%")) {
			special++;
		}
		if (password.contains("^")) {
			special++;
		}
		if (password.contains("&")) {
			special++;
		}
		if (password.contains("*")) {
			special++;
		}
		if (password.contains("(")) {
			special++;
		}
		if (password.contains(")")) {
			special++;
		}
		if (password.contains("-")) {
			special++;
		}
		if (password.contains("/")) {
			special++;
		}
		if (password.contains("+")) {
			special++;
		}
		if (password.contains(":")) {
			special++;
		}
		if (password.contains(".")) {
			special++;
		}
		if (password.contains(",")) {
			special++;
		}
		if (password.contains("<")) {
			special++;
		}
		if (password.contains(">")) {
			special++;
		}
		if (password.contains("?")) {
			special++;
		}
		if (password.contains("|")) {
			special++;
		}
		if (special < 2) {
			ev = false;
			return ev;
		}

		if (ev) {
			int upper = 0;
			int lower = 0;
			for (int i = 0; i < password.length(); i++) {
				if (Character.isUpperCase(password.charAt(i))) {
					upper++;
				} else if (Character.isLowerCase(password.charAt(i))) {
					lower++;
				}

				// if all conditions fails

			}
			if (upper < 2 || lower < 2) {
				ev = false;
				return ev;
			}
		}
		return ev;
	}

}
