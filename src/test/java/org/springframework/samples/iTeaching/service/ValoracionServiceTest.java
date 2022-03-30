package org.springframework.samples.iTeaching.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.Valoracion;
import org.springframework.stereotype.Service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;

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
	public void findValoracionByAlumnoAsignaturaTest() {
		Alumno alumno = alumnoService.findAlumnoById(1);
		Asignatura asignatura = asignaturaService.findById(1);
		Valoracion valoracion = valoracionService.findValoracionByAlumnoAsignatura(alumno, asignatura).get();
		
		assertThat(valoracion.getComentario().equals("comentario"));
		assertThat(valoracion.getPuntuacion().equals(5.0));
		assertThat(valoracion.getProfesor().getId().equals(1));
	}
	
	@Test
	public void findValoracionByAsignaturaTest() {
		Asignatura asignatura = asignaturaService.findById(1);
		Collection<Valoracion> valoraciones = valoracionService.findValoracionByAsignatura(asignatura);
		
		assertThat(valoraciones.size()==1);
	}
	
	@Test
	public void findAllByProfesorTest() {
		Profesor profesor = profesorService.findProfesorById(1);
		List<Valoracion> valoraciones = valoracionService.findAllByProfesor(profesor);
		
		assertThat(valoraciones.size()==1);
	}

}
