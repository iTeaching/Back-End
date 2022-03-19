/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.iTeaching.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.repository.AlumnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class AlumnoService {

	private AlumnoRepository alumnoRepository;	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public AlumnoService(AlumnoRepository alumnoRepository) {
		this.alumnoRepository = alumnoRepository;
	}	

	@Transactional(readOnly = true)
	public Alumno findAlumnoById(int id) throws DataAccessException {
		return alumnoRepository.findById(id);
	}

	@Transactional
	public void saveAlumno(Alumno alumno) throws DataAccessException {
		alumnoRepository.save(alumno);		
		userService.saveUser(alumno.getUser());
		authoritiesService.saveAuthorities(alumno.getUser().getUsername(), "alumno");
	}		
	
	@Transactional(readOnly = true)
	public Alumno findAlumnoByUsername(String username) throws DataAccessException {
		return alumnoRepository.findByUsername(username);
	}

	public void delete(Alumno alumno) {
		this.alumnoRepository.delete(alumno);
	}

}
