package org.springframework.samples.iTeaching.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Clase;
import org.springframework.samples.iTeaching.service.ClaseService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class TutoriaValidator implements Validator {

    private ClaseService claseService;
    @Autowired
	public TutoriaValidator(ClaseService cs) {
		this.claseService =cs;
	}

    @Override
    public boolean supports(Class<?> clazz) {
		return Clase.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    Clase clase= (Clase) target; 
    
    if(LocalDateTime.parse(clase.getHoraComienzo()).isAfter(LocalDateTime.parse(clase.getHoraFin()))){
        errors.rejectValue("horaFin","La hora de fin debe ser posterior a la hora de inicio","La hora de fin debe ser posterior a la hora de inicio");
    }
    }
}

