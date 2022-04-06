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
package org.springframework.samples.iTeaching.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Clase;
import org.springframework.samples.iTeaching.model.estadoClase;
import org.springframework.samples.iTeaching.repository.ClaseRepository;
/**
 * Spring Data JPA OwnerRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface ClaseRepository extends CrudRepository<Clase, Integer> {


//	@Query("SELECT DISTINCT owner FROM Alumno alumno left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
//	public Collection<Alumno> findByLastName(@Param("lastName") String lastName);



	@Query("SELECT clase FROM Clase clase WHERE clase.id =:id")
	public Clase findById(@Param("id") int id);

	@Query("SELECT clase FROM Clase clase WHERE clase.alumno.user.username =:username")
	public List<Clase> findByUsername(String username);
	
	
	@Query("SELECT clase FROM Clase clase WHERE clase.profesor.user.username =:username")
	public List<Clase> findByProfesor(String username);
	
	@Query("SELECT clase FROM Clase clase WHERE clase.estadoClase =:estadoclase")
	public List<Clase> findByEstadoClase(estadoClase estadoclase);
	
	List<Clase> findAll();

}
