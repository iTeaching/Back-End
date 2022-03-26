package org.springframework.samples.iTeaching.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Valoracion;
import org.springframework.samples.iTeaching.repository.ValoracionRepository;
import org.springframework.stereotype.Service;

@Service
public class ValoracionService {
	
	@Autowired
	ValoracionRepository valoracionRepository;
	

	
	public void saveValoracion(Valoracion valoracion) {
		valoracionRepository.save(valoracion);
	}

}
