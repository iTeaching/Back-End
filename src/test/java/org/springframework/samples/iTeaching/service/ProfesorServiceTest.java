package org.springframework.samples.iTeaching.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class ProfesorServiceTest {

	@Autowired
	private ProfesorService profesorService;

	@Test
	public void testFindProfesores() {
		Collection<Profesor> p = this.profesorService.findProfesores();

		assertThat(p.size() == 2);
	}

	@Test
	public void testFindProfesorById() {
		Profesor p = this.profesorService.findProfesorById(1);

		assertThat(p.getUser().getUsername().equals("prof1"));
	}

	@Test
	public void testFindProfesorByUsername() {
		Profesor p = this.profesorService.findProfesorByUsername("prof1");

		assertThat(p.getId() == 1);
	}

	@Test
	public void testInsertProfesor() {
		User u = new User();
		u.setUsername("prof3");
		u.setPassword("prof3");
		u.setEnabled(true);

		Profesor p = new Profesor();
		p.setFirstName("Juan Manuel");
		p.setLastName("Gump Romero");
		p.setTelephone("612623789");
		p.setEmail("juanmagump@mail.com");
		p.setUser(u);

		this.profesorService.saveProfesor(p);
		assertThat(this.profesorService.findProfesores().size() == 3);
	}

	@Test
	public void testDeleteProfesor() {
		Profesor p = this.profesorService.findProfesorById(2);
		
		this.profesorService.deleteProfesor(p);
		assertNull(this.profesorService.findProfesorById(2));
	}
}
