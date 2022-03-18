package org.springframework.samples.iTeaching.repository;

import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.model.Sala;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SalaRepository extends CrudRepository<Sala, Integer>{
	
	@Query("SELECT sala FROM Sala sala WHERE sala.id =:id")
	public Sala findById(@Param("id") int id);
	
	@Query("SELECT sala FROM Sala sala WHERE sala.profesor.id =:id")
	public Collection<Sala> findByUsuarioId(@Param("id") int id);
	
	List<Sala> findAll();

}
