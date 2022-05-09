package org.springframework.samples.iTeaching.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.iTeaching.model.Clase;
import org.springframework.samples.iTeaching.model.estadoClase;
import org.springframework.samples.iTeaching.repository.ClaseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClaseService {

	private ClaseRepository claseRepository;

	@Autowired
	public ClaseService(ClaseRepository claseRepository) {
		this.claseRepository = claseRepository;
	}

	@Transactional(readOnly = true)
	public Clase findById(int id) throws DataAccessException {
		return claseRepository.findById(id);
	}

	@Transactional
	public void saveClase(Clase clase) throws DataAccessException {
		claseRepository.save(clase);
	}

	@Transactional(readOnly = true)
	public List<Clase> findByAlumno(String username) throws DataAccessException {
		return claseRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public List<Clase> findByProfesor(String username) throws DataAccessException {
		return claseRepository.findByProfesor(username);
	}

	@Transactional(readOnly = true)
	public List<Clase> findEstadoClase(estadoClase estadoClase) throws DataAccessException {
		return claseRepository.findByEstadoClase(estadoClase);
	}

	public List<Clase> findAll() {
		return claseRepository.findAll();
	}

}
