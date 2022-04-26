package org.springframework.samples.iTeaching.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Clase;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.model.estadoClase;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ClaseServiceTest {

	@Autowired
	private ClaseService claseService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private ProfesorService profesorService;
	@Autowired
	private AsignaturaService asignaturaService;

	@Test
	public void testFindClaseById() {
		Clase c = this.claseService.findById(1);

		assertEquals(1, c.getProfesor().getId());
		assertEquals(1, c.getAlumno().getId());
	}

	@Test
	public void testInsertAlumno() {
		Clase c = new Clase();
		c.setProfesor(profesorService.findProfesorById(1));
		c.setHoraComienzo("10");
		c.setHoraFin("11");
		c.setEstadoClase(estadoClase.confirmada);
		c.setAsignatura(asignaturaService.findById(1));
		c.setAceptacionAlumno(true);
		c.setAceptacionProfesor(true);
		c.setAlumno(alumnoService.findAlumnoById(1));
		c.setId(3);

		this.claseService.saveClase(c);

		assertEquals(2, claseService.findAll().size());
	}

	@Test
	public void testFindAll() {
		List<Clase> c = this.claseService.findAll();

		assertEquals(1, c.size());
	}

	@Test
	public void testFindByProfesor() {
		List<Clase> c = this.claseService.findByProfesor("gonzalogomez");

		assertEquals(1, c.size());
	}

	@Test
	public void testFindByAlumno() {
		List<Clase> c = this.claseService.findByAlumno("pepeperez");

		assertEquals(1, c.size());
	}

	@Test
	public void testFindByEstado() {
		List<Clase> c = this.claseService.findEstadoClase(estadoClase.solicitada);

		assertEquals(1, c.size());
	}
}
