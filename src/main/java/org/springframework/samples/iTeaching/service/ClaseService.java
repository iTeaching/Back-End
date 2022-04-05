package org.springframework.samples.iTeaching.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Clase;
import org.springframework.samples.iTeaching.repository.AlumnoRepository;
import org.springframework.samples.iTeaching.repository.ClaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClaseService {

	private ClaseRepository claseRepository;	
	


	@Autowired
	public ClaseService(ClaseRepository claseRepository) {
		this.claseRepository = claseRepository;
	}	

/*	@Transactional(readOnly = true)
	public Alumno findAlumnoById(int id) throws DataAccessException {
		return alumnoRepository.findById(id);
	}*/
		
	@Transactional
	public void saveClase(Clase clase) throws DataAccessException {
		claseRepository.save(clase);		
	}	
	
	@Transactional(readOnly = true)
	public List<Clase> findAlumnoByUsername(String username) throws DataAccessException {
		return claseRepository.findByUsername(username);
	} 
	
	public List<Clase> findAll(){
		return claseRepository.findAll();
	}


}
