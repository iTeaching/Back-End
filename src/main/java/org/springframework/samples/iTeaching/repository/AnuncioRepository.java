package org.springframework.samples.iTeaching.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.iTeaching.model.Anuncio;

public interface AnuncioRepository extends CrudRepository<Anuncio, Integer> {

	@Query("SELECT anuncio FROM Anuncio anuncio WHERE anuncio.id =:id")
	public Anuncio findById(@Param("id") int id);
	
	@Query("SELECT anuncio FROM Anuncio anuncio WHERE anuncio.usuario.id =:id")
	public Collection<Anuncio> findByUsuarioId(@Param("id") int id);
	
	List<Anuncio> findAll();

}
