package org.springframework.samples.iTeaching.web;

import org.springframework.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.service.AnuncioService;
import org.springframework.validation.Errors;

public class AnuncioValidator implements Validator{
    
    private AnuncioService anunSer;
    
    @Autowired
    public AnuncioValidator(AnuncioService aS) {
        this.anunSer = aS;
    }
    
    @Override
    public boolean supports(Class<?> clazz) {
        return Anuncio.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        Anuncio anuncio = (Anuncio) obj;

        
        if (isNumeric(anuncio.getTitulo()) | isDouble(anuncio.getTitulo())) {
            errors.rejectValue("titulo", "El campo titulo no puede ser numérico", "El campo titulo no puede ser numérico");

        }
        if (anuncio.getTitulo().isBlank()) {
            errors.rejectValue("titulo", " No puede dejar el campo vacio", "No puede dejar el campo vacio");

        }
        if (isNumeric(anuncio.getDescripcion()) | isDouble(anuncio.getTitulo())) {
            errors.rejectValue("descripcion", "El campo descripcion no debe ser numérico", "El campo descripcion no debe ser numérico");

        }
        if (anuncio.getDescripcion().isBlank()) {
            errors.rejectValue("descripcion", " No puede dejar el campo vacio", "No puede dejar el campo vacio");

        }
        if (isNumeric(anuncio.getAsignatura()) | isDouble(anuncio.getTitulo())) {
            errors.rejectValue("asignatura", "El campo asignatura  no debe ser numérico", "El campo asignatura  no debe ser numérico");

        }
        if (anuncio.getAsignatura().isBlank()) {
            errors.rejectValue("asignatura", " No puede dejar el campo vacio", "No puede dejar el campo vacio");

        }
        if (anuncio.getPrecio()==null) {
            errors.rejectValue("precio", " No puede dejar el campo vacio", "No puede dejar el campo vacio");
        }
        

    }
    
        
    
    private static boolean isNumeric(String cadena){
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    
    private static boolean isDouble(String cadena){
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe){
            return false;
        }
    }
    

}