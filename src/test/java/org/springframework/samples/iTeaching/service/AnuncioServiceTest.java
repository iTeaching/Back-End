package org.springframework.samples.iTeaching.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AnuncioServiceTest {
	
	@Autowired
	private AnuncioService anuncioService;
	@Autowired
	private ProfesorService profesorService;
	
	@Test
	public void testFindById() {
		Anuncio a = this.anuncioService.findById(1);
		
		assertThat(a.getProfesor().getId().equals(1));
		assertThat(a.getTitulo().equals("Clases de lengua"));
		assertThat(a.getAsignatura().equals("Lengua"));
	}
	
	@Test
	public void testFindAll() {
		List<Anuncio> anuncios = this.anuncioService.findAll();
		
		assertThat(anuncios.size()==2);
	}
	
	@Test
	public void testInsertAnuncio() {
		Anuncio a = new Anuncio();
		Profesor p = this.profesorService.findProfesorById(1); 
		
		a.setAsignatura("Lengua");
		a.setDescripcion("Clases de Lengua baratas");
		a.setTitulo("Clases de Lengua para 1 ESO");
		a.setPrecio(6.00);
		a.setProfesor(p);
		a.setVersion(1);
		
		this.anuncioService.saveAnuncio(a);
	}
	
	
	
}












