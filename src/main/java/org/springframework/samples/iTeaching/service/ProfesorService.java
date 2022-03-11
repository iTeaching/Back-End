package org.springframework.samples.iTeaching.service;

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

	@Transactional(readOnly = true)
	public Profesor findProfesorById(int id) throws DataAccessException {
		return profesorRepository.findById(id);
	}

	@Transactional
	public void saveProfesor(Profesor profesor) throws DataAccessException {
		profesorRepository.save(profesor);		
		userService.saveUser(profesor.getUser());
		authoritiesService.saveAuthorities(profesor.getUser().getUsername(), "profesor");
	}		
	
	@Transactional(readOnly = true)
	public Profesor findProfesorByUsername(String username) throws DataAccessException {
		return profesorRepository.findByUsername(username);
	}

}
