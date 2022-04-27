package org.springframework.samples.iTeaching.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AsignaturaServiceTest {

	@Autowired
	private AsignaturaService asignaturaService;
	@Autowired
	private ProfesorService proferoserService;
	@Autowired
	private AlumnoService alumnoService;
	
	@Test
	public void testFindAsignaturaById() {
		Asignatura a = this.asignaturaService.findById(1);
		
		assertThat(a.getTitulo_anuncio().equals(""));
	}
	
	@Test
	public void testFindAsignaturaByProfesor() {
		Collection<Asignatura> as = this.asignaturaService.findByProfesor(1);
		
		assertThat(as.size() == 2);
	}
	
	@Test
	public void testFindAsignaturas() {
		List<Asignatura> as = this.asignaturaService.findAll();
		
		assertThat(as.size() == 4);
	}
	
	@Test
	public void testFindAsignaturaByNombre() {
		Collection<Asignatura> as = this.asignaturaService.findByNombre("");
		
		assertThat(as.size() == 2);
	}
	
	@Test
	public void testAppliedAnuncio() {
		List<Asignatura> as = this.asignaturaService.appliedAnuncio(this.alumnoService.findAlumnoById(1));
		
		assertThat(as.size() == 1);
	}
	
	@Test
	public void testInsertAsignatura() {
		Asignatura a = new Asignatura();
		a.setDescripcion("Clase de lengua castellana");
		a.setNombre("Lengua");
		a.setId(12);
		a.setPrecio(10.00);
		a.setProfesor(this.proferoserService.findProfesorById(1));
		a.setTitulo_anuncio("Clases de lengua");
		a.setUrl("https://acme.whereby.com/008509eb-3d6a-43ce-bd86-2712cc02c40b");
		
		this.asignaturaService.saveAsignatura(a);
	}
	
	@Test
	public void testDelete() {
		this.asignaturaService.delete(this.asignaturaService.findById(1));
		
	}
}
