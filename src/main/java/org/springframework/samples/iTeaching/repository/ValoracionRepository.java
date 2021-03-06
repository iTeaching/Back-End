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

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.Valoracion;
import org.springframework.samples.iTeaching.repository.ValoracionRepository;
/**
 * Spring Data JPA OwnerRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface ValoracionRepository extends CrudRepository<Valoracion, Integer> {
	
	@Query("SELECT valoracion FROM Valoracion valoracion WHERE valoracion.alumno =:alumno AND valoracion.asignatura =:asignatura")
	public Optional<Valoracion> findByAlumnoAsignatura(@Param("alumno") Alumno alumno, 
			@Param("asignatura") Asignatura asignatura);
	
	@Query("SELECT valoracion FROM Valoracion valoracion WHERE valoracion.asignatura =:asignatura")
	public Collection<Valoracion> findByAsignatura(@Param("asignatura") Asignatura asignatura);
	
	@Query("SELECT valoracion FROM Valoracion valoracion WHERE valoracion.profesor=:profesor")
	public List<Valoracion> findAllByProfesor(@Param("profesor") Profesor profesor);

}
