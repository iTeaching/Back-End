package org.springframework.samples.iTeaching.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.iTeaching.model.Profesor;


public interface ProfesorRepository extends CrudRepository<Profesor, Integer>{

	@Query("SELECT profesor FROM Profesor profesor WHERE profesor.id =:id")
	public Profesor findById(@Param("id") int id);

	@Query("SELECT profesor FROM Profesor profesor WHERE profesor.user.username =:username")
	public Profesor findByUsername(String username);

}
