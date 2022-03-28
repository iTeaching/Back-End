package org.springframework.samples.iTeaching.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Valoracion;
import org.springframework.samples.iTeaching.repository.ValoracionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ValoracionService {
	
	@Autowired
	ValoracionRepository valoracionRepository;
	
	public void saveValoracion(Valoracion valoracion) throws DataAccessException {
		valoracionRepository.save(valoracion);
	}
	
	@Transactional(readOnly = true)
	public Optional<Valoracion> findValoracionByAlumnoAsignatura(Alumno alumno, Asignatura asignatura) throws DataAccessException {
		return valoracionRepository.findByAlumnoAsignatura(alumno, asignatura);
	}
	
	@Transactional(readOnly = true)
	public Collection<Valoracion> findValoracionByAsignatura(Asignatura asignatura) throws DataAccessException {
		return valoracionRepository.findByAsignatura(asignatura);
	}

	public void delete(Valoracion valoracion) {
		valoracionRepository.delete(valoracion);
	}
}
