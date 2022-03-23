package org.springframework.samples.iTeaching.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.repository.AnuncioRepository;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

	@Autowired
	AnuncioRepository anuncioRepository;

	@Autowired
	AlumnoService alumnoService;

	public Anuncio findById(int anuncioId) {
		return anuncioRepository.findById(anuncioId);
	}

	public Collection<Anuncio> findByUsuario(int usuarioID) {
		return anuncioRepository.findByUsuarioId(usuarioID);
	}

	public List<Anuncio> findAll() {
		return anuncioRepository.findAll();
	}

	public void saveAnuncio(Anuncio anuncio) {
		anuncioRepository.save(anuncio);
	}

	public void delete(Anuncio anuncio) {
		anuncioRepository.delete(anuncio);
	}

	public Collection<Anuncio> findByAsignatura(String asignatura) {
		return anuncioRepository.findByAsignatura(asignatura);
	}

	public List<Anuncio> anunciosAlumno(String username) {
		Alumno alumno = alumnoService.findAlumnoByUsername(username);
		List<Anuncio> entrada = (List<Anuncio>) anuncioRepository.findAll();
		return entrada.stream().filter(x -> x.getAlumnos().contains(alumno)).collect(Collectors.toList());
	}

	public void aplyAnuncio(Alumno alumno, int id) {
		Anuncio anuncioAplied = anuncioRepository.findById(id);
		anuncioAplied.getAlumnos().add(alumno);
		saveAnuncio(anuncioAplied);
	}

	public List<Anuncio> appliedAnuncio(Alumno alumno) {
		return anuncioRepository.findAll().stream().filter(a -> a.getAlumnos().contains(alumno))
				.collect(Collectors.toList());

	}

}
