package org.springframework.samples.iTeaching.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.iTeaching.model.Valoracion;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ValoracionServiceTest {

	@Autowired
	private ValoracionService valoracionService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private AsignaturaService asignaturaService;
	@Autowired
	private ProfesorService profesorService;

	@Test
	public void testFindValoracionByAlumnoAsignatura() {
		Optional<Valoracion> v = this.valoracionService.findValoracionByAlumnoAsignatura(
				this.alumnoService.findAlumnoById(1), this.asignaturaService.findById(1));

		assertEquals(1, v.get().getId());
	}

	@Test
	public void testFindValoracionByAsignatura() {
		Collection<Valoracion> vals = this.valoracionService
				.findValoracionByAsignatura(this.asignaturaService.findById(1));

		assertEquals(1, vals.size());
	}

	@Test
	public void testFindValoracionByProfesor() {
		Collection<Valoracion> vals = this.valoracionService
				.findAllByProfesor(this.profesorService.findProfesorById(1));

		assertEquals(1, vals.size());
	}

	@Test
	public void testSaveValoracion() {
		Valoracion v = new Valoracion();
		v.setAlumno(this.alumnoService.findAlumnoById(2));
		v.setAsignatura(this.asignaturaService.findById(1));
		v.setComentario("Buena");
		v.setProfesor(this.profesorService.findProfesorById(1));
		v.setPuntuacion(4.00);

		this.valoracionService.saveValoracion(v);

		assertEquals(2, this.valoracionService.findAllByProfesor(this.profesorService.findProfesorById(1)).size());
	}

	@Test
	public void testDeleteValoracion() {
		this.valoracionService.delete(this.valoracionService.findValoracionByAlumnoAsignatura(
				this.alumnoService.findAlumnoById(1), this.asignaturaService.findById(1)).get());

		assertEquals(0, this.valoracionService.findAllByProfesor(this.profesorService.findProfesorById(1)).size());
	}
}
