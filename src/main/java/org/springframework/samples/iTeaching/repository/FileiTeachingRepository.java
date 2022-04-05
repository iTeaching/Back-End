package org.springframework.samples.iTeaching.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.FileiTeaching;

public interface FileiTeachingRepository extends JpaRepository<FileiTeaching,Integer>{

	@Query("SELECT file FROM FileiTeaching file WHERE file.asignatura =:asignatura")
	public Collection<FileiTeaching> findByAsignatura(@Param("asignatura") Asignatura asignatura);
}
