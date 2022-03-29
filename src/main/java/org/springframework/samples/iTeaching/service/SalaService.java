package org.springframework.samples.iTeaching.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Sala;
import org.springframework.samples.iTeaching.repository.SalaRepository;
import org.springframework.stereotype.Service;

@Service
public class SalaService {
	
	@Autowired
	SalaRepository salaRepository;
	
	public Sala findById(int salaId){
		return salaRepository.findById(salaId);
	}
	
	public Collection<Sala> findByProfesor(int usuarioID){
		return salaRepository.findByProfesorId(usuarioID);
	}
	public List<Sala> findAll(){
		return salaRepository.findAll();
	}
	
	public void saveSala(Sala sala) {
		salaRepository.save(sala);
	}

}
