package org.springframework.samples.iTeaching.repository;

import org.springframework.samples.iTeaching.model.Asignatura;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AsignaturaRepository extends CrudRepository<Asignatura, Integer>{
	
	@Query("SELECT asignatura FROM Asignatura asignatura WHERE asignatura.id =:id")
	public Asignatura findById(@Param("id") int id);
	
	@Query("SELECT asignatura FROM Asignatura asignatura WHERE asignatura.profesor.id =:id")
	public Collection<Asignatura> findByProfesorId(@Param("id") int id);
	
	@Query("SELECT asignatura FROM Asignatura asignatura WHERE asignatura.nombre =:asignatura")
	public Collection<Asignatura> findByNombre(@Param("asignatura") String asignatura);
	
	List<Asignatura> findAll();

}
