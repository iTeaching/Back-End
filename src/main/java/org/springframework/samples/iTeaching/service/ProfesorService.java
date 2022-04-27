package org.springframework.samples.iTeaching.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.repository.ProfesorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ProfesorService {

	private ProfesorRepository profesorRepository;	
	
	@Autowired
	private UserService userService;

	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public ProfesorService(ProfesorRepository profesorRepository) {
		this.profesorRepository = profesorRepository;
	}
	
	public List<Profesor> findAll(){
		return profesorRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Profesor findProfesorById(int id) throws DataAccessException {
		return profesorRepository.findById(id).get();
	}

	@Transactional
	public void saveProfesor(Profesor profesor) throws DataAccessException {
		profesor.getUser().setPassword((profesor.getUser().getPassword()));
		profesorRepository.save(profesor);		
	}		
	
	@Transactional(readOnly = true)
	public Profesor findProfesorByUsername(String username) throws DataAccessException {
		return profesorRepository.findByUsername(username);
	}

	public void deleteProfesor(Profesor profesor) {
		this.profesorRepository.delete(profesor);
	}

	public List<String> findByIdAlumnosProfesores(int idProfesores, int idAlumnos){
		return profesorRepository.findByIdAlumnosProfesores(idProfesores, idAlumnos);	
	}
	
	
}
