package org.springframework.samples.iTeaching.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.iTeaching.model.Profesor;


public interface ProfesorRepository extends CrudRepository<Profesor, Integer>{	
	@Query("SELECT p FROM Profesor p WHERE p.user.username = :username")
	public Profesor findByUsername(@Param("username") String username);
	
	List<Profesor> findAll();

}
