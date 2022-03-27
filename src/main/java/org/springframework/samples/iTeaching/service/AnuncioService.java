package org.springframework.samples.iTeaching.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.model.Valoracion;
import org.springframework.samples.iTeaching.repository.AnuncioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnuncioService {

	@Autowired
	AlumnoService alumnoService;
	
	@Autowired
	ValoracionService valoracionService;
  
	private AnuncioRepository anuncioRepository;
	
	@Autowired
	public AnuncioService(AnuncioRepository anuncioRepository) {
		this.anuncioRepository = anuncioRepository;
	}

	@Transactional(readOnly = true)
	public Anuncio findAnuncioById(int anuncioId) throws DataAccessException {
		return anuncioRepository.findById(anuncioId).get();


	}
	
	@Transactional(readOnly = true)
	public Collection<Anuncio> findByUsuario(int usuarioID) throws DataAccessException{
		return anuncioRepository.findByUsuarioId(usuarioID);
	}
	
	@Transactional(readOnly = true)
	public Collection<Anuncio> findAll() throws DataAccessException {
		return (Collection<Anuncio>) anuncioRepository.findAll();
	}
	
	public void saveAnuncio(Anuncio anuncio) throws DataAccessException{
		anuncioRepository.save(anuncio);
	}

	public void delete(Anuncio anuncio) {
		List<Valoracion> valoracion= (List<Valoracion>) valoracionService.findValoracionByAnuncio(anuncio);
		
		for (int i = 0; i<valoracion.size();i++) {
			valoracionService.delete(valoracion.get(i));
		}
		
		anuncioRepository.delete(anuncio);
	}
	
	public Collection<Anuncio> findByAsignatura(String asignatura) throws DataAccessException {
		return anuncioRepository.findByAsignatura(asignatura);
	}

	public List<Anuncio> anunciosAlumno(String username){
		Alumno alumno = alumnoService.findAlumnoByUsername(username);
		List<Anuncio> entrada = (List<Anuncio>) anuncioRepository.findAll();
		return entrada.stream().filter(x->x.getAlumnos().contains(alumno)).collect(Collectors.toList());
	}
	public void aplyAnuncio(Alumno alumno, int id){
		Anuncio anuncioAplied = anuncioRepository.findById(id).get();
		anuncioAplied.getAlumnos().add(alumno);
		saveAnuncio(anuncioAplied);
	}
	public List<Anuncio> appliedAnuncio(Alumno alumno){
		return anuncioRepository.findAll().stream().filter(a->a.getAlumnos().contains(alumno)).collect(Collectors.toList());
		
		
	}

	}

