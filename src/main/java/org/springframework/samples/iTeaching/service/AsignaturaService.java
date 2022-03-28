package org.springframework.samples.iTeaching.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.repository.AsignaturaRepository;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaService {
	
	@Autowired
	AsignaturaRepository asignaturaRepository;
	
	public Asignatura findById(int asignaturaId){
		return asignaturaRepository.findById(asignaturaId);
	}
	
	public Collection<Asignatura> findByProfesor(int usuarioID){
		return asignaturaRepository.findByProfesorId(usuarioID);
	}
	
	public List<Asignatura> findAll(){
		return asignaturaRepository.findAll();
	}
	
	public Collection<Asignatura> findByNombre(String asignatura){
		return asignaturaRepository.findByNombre(asignatura);
	}
	
	public List<Asignatura> appliedAnuncio(Alumno alumno){
		return asignaturaRepository.findAll().stream().filter(a->a.getAlumnos().contains(alumno)).collect(Collectors.toList());
	}
	
	public void saveAsignatura(Asignatura asignatura) {
		asignaturaRepository.save(asignatura);
	}

}
