package org.springframework.samples.iTeaching.service;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.repository.AnuncioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnuncioService {

	
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
		// TODO Auto-generated method stub
		anuncioRepository.delete(anuncio);
	}
	
	public Collection<Anuncio> findByAsignatura(String asignatura) throws DataAccessException {
		return anuncioRepository.findByAsignatura(asignatura);
	}
	}
