package org.springframework.samples.iTeaching.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.iTeaching.model.Sala;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SalaServiceTest {
	
	@Autowired
	private SalaService salaService;
	@Autowired
	private ProfesorService profesorService;

	@Test
	public void testFindById() {
		Sala s = this.salaService.findById(1);
		
		assertThat(s.getNombre().equals("sala1"));
		assertThat(s.getUrl().equals("https://acme.whereby.com/a05d837d-2aaf-47a2-abf2-21dc8121f1c7"));
	}
	
	@Test
	public void testFindByProfesor() {
		Collection<Sala> salas = this.salaService.findByProfesor(1);
		
		assertThat(salas.size() == 2);
	}
	
	@Test
	public void testFindAll() {
		List<Sala> salas = this.salaService.findAll();
		
		assertThat(salas.size() == 3);
	}
	
	@Test
	public void testInsertSala() {
		Sala s = new Sala();
		s.setNombre("sala4");
		s.setProfesor(this.profesorService.findProfesorById(1));
		s.setUrl("https://acme.whereby.com/a05d837d-2aaf-47a2-abf2-33db342321g5");
		
		assertThat(this.salaService.findAll().size() == 4);
	}
}	



