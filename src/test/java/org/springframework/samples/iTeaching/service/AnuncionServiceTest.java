package org.springframework.samples.iTeaching.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AnuncionServiceTest {

	@Autowired
	private AnuncioService anuncioService;
	
	@Test
	public void testFindAnuncioById() {
		Anuncio a = this.anuncioService.findAnuncioById(1);
	
		assertThat(a.getTitulo().equals("Clases de lengua"));
		assertThat(a.getAsignatura().equals("Lengua"));
		assertThat(a.getDescripcion().equals("Clases muy baratas"));
		assertThat(a.getPrecio().equals(15.));
	}



	@Test
	public void testInsertAnuncio() {
		

		Profesor p = new Profesor();
		p.setFirstName("Juan Manuel");
		p.setLastName("Gump Romero");
		p.setTelephone("612623789");
		p.setEmail("juanmagump@mail.com");
		
		
		
		Anuncio a = new Anuncio();
		a.setTitulo("Clases de mates");
		a.setAsignatura("Mates");
		a.setDescripcion("Clases muy baratas");
		a.setPrecio(20.);
		a.setId(3);
		a.setProfesor(p);
	
		
		assertThat(anuncioService.findAnuncioById(3).getProfesor().equals(p));
	}
}
