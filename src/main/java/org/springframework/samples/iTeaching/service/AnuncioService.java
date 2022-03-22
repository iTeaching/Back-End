package org.springframework.samples.iTeaching.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.repository.AnuncioRepository;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

	@Autowired
	AnuncioRepository anuncioRepository;
	
	public Anuncio findById(int anuncioId){
		return anuncioRepository.findById(anuncioId);
	}
	
	public Collection<Anuncio> findByUsuario(int usuarioID){
		return anuncioRepository.findByUsuarioId(usuarioID);
	}
	public List<Anuncio> findAll(){
		return anuncioRepository.findAll();
	}
	
	public void saveAnuncio(Anuncio anuncio) {
		anuncioRepository.save(anuncio);
	}
	}
