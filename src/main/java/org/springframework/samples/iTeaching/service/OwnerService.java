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
import org.springframework.samples.iTeaching.model.Usuario;
import org.springframework.samples.iTeaching.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class OwnerService {

	private UsuarioRepository usuarioRepository;	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	public OwnerService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}	

	@Transactional(readOnly = true)
	public Usuario findUsuarioById(int id) throws DataAccessException {
		return usuarioRepository.findById(id);
	}

//	@Transactional(readOnly = true)
//	public Collection<Usuario> findOwnerByLastName(String lastName) throws DataAccessException {
//		return ownerRepository.findByLastName(lastName);
//	}

	@Transactional
	public void saveUsuario(Usuario usuario) throws DataAccessException {
		//creating owner
		usuarioRepository.save(usuario);		
		//creating user
		userService.saveUser(usuario.getUser());
		//creating authorities
		authoritiesService.saveAuthorities(usuario.getUser().getUsername(), "test");
	}		
	
	@Transactional(readOnly = true)
	public Usuario findUsuarioByUsername(String username) throws DataAccessException {
		return usuarioRepository.findByUsername(username);
	}

}
