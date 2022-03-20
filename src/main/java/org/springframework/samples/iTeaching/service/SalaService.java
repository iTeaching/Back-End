package org.springframework.samples.iTeaching.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Sala;
import org.springframework.samples.iTeaching.repository.SalaRepository;
import org.springframework.stereotype.Service;

@Service
public class SalaService {
	
	@Autowired
	SalaRepository salaRepository;
	@Autowired
	AlumnoService alumnoService;
	public Sala findById(int salaId){
		return salaRepository.findById(salaId);
	}
	
	public Collection<Sala> findByProfesor(int usuarioID){
		return salaRepository.findByProfesorId(usuarioID);
	}
	public List<Sala> findAll(){
		return salaRepository.findAll();
	}
	
	public void saveSala(Sala sala) {
		salaRepository.save(sala);
	}
	public List<Sala> salasAlumno(String username){
		Alumno alumno = alumnoService.findAlumnoByUsername(username);
		List<Sala> entrada = salaRepository.findAll();
		return entrada.stream().filter(x->x.getAlumnos().contains(alumno)).collect(Collectors.toList());
	}
}
