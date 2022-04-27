package org.springframework.samples.iTeaching.service;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AlumnoSeviceTest {
	
	@Autowired
	private AlumnoService alumnoService;
	
	@Test
	public void testFindAlumnoById() {
		Alumno a = this.alumnoService.findAlumnoById(1);
		
		assertEquals("Pepe", a.getFirstName());
		assertEquals("Moya", a.getLastName());
		assertEquals("666111334", a.getTelephone());
	}

	@Test
	public void testFindAlumnoByUsername() {
		Alumno a = this.alumnoService.findAlumnoByUsername("pepeperez");
		
		assertEquals(1, a.getId());
	}

	@Test
	public void testInsertAlumno() {
		User u = new User();
		u.setUsername("pedjimbas");
		u.setPassword("alumno2");
		u.setEnabled(true);
		
		Alumno a = new Alumno();
		a.setFirstName("Pedro");
		a.setLastName("Jimenez");
		a.setEmail("pejim@mail.com");
		a.setTelephone("666777999");
		a.setUser(u);
		a.setId(3);
		
		this.alumnoService.saveAlumno(a);
		
		assertEquals("Pedro", alumnoService.findAlumnoById(3).getFirstName());
	}
}
