package org.springframework.samples.iTeaching.repository;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
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

}