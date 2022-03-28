package org.springframework.samples.iTeaching.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.repository.AlumnoRepository;
/**
 * Spring Data JPA OwnerRepository interface
 *
 * @author Michael Isvy
 * @since 15.1.2013
 */
public interface AlumnoRepository extends CrudRepository<Alumno, Integer> {


//	@Query("SELECT DISTINCT owner FROM Alumno alumno left join fetch owner.pets WHERE owner.lastName LIKE :lastName%")
//	public Collection<Alumno> findByLastName(@Param("lastName") String lastName);



	@Query("SELECT alumno FROM Alumno alumno WHERE alumno.id =:id")
	public Alumno findById(@Param("id") int id);

	@Query("SELECT alumno FROM Alumno alumno WHERE alumno.user.username =:username")
	Alumno findByUsername(String username);

}
