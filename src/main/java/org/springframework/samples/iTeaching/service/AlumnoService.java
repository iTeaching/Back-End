package org.springframework.samples.iTeaching.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.repository.AlumnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class AlumnoService {

	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public AlumnoService(AlumnoRepository alumnoRepository) {
		this.alumnoRepository = alumnoRepository;
	}	

	@Transactional(readOnly = true)
	public Alumno findAlumnoById(int id) throws DataAccessException {
		return alumnoRepository.findById(id);
	}
		
	@Transactional
	public void saveAlumno(Alumno alumno,boolean valor) throws DataAccessException {
		if(valor==true) {
		alumno.getUser().setPassword(encoder.encode(alumno.getUser().getPassword()));
		}
		alumnoRepository.save(alumno);		
	}		
	
	@Transactional(readOnly = true)
	public Alumno findAlumnoByUsername(String username) throws DataAccessException {
		return alumnoRepository.findByUsername(username);
	}
	
	public List<Alumno> findAll(){
		return alumnoRepository.findAll();
	}

	public void delete(Alumno alumno) {
		this.alumnoRepository.delete(alumno);
	}

	public List<String> findByIdAlumnosProfesores(int idProfesores, int idAlumnos){
		return alumnoRepository.findByIdAlumnosProfesores(idProfesores, idAlumnos);	
	}
	
}
