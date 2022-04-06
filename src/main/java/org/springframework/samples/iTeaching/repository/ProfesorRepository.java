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

	
	@Query(value="SELECT SALA_ID, ALUMNO_ID, PROFESOR FROM ASIGNATURA_ALUMNO INNER JOIN ASIGNATURA WHERE ASIGNATURA.ID = ASIGNATURA_ALUMNO.SALA_ID AND PROFESOR=?1 AND ASIGNATURA_ALUMNO.ALUMNO_ID =?2", nativeQuery = true)
	public List<String> findByIdAlumnosProfesores(int id, int id2);


}
