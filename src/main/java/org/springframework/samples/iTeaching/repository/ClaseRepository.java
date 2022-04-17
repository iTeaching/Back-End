package org.springframework.samples.iTeaching.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.iTeaching.model.Clase;
import org.springframework.samples.iTeaching.model.estadoClase;
import org.springframework.samples.iTeaching.repository.ClaseRepository;

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
