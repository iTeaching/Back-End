package org.springframework.samples.iTeaching.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.iTeaching.configuration.SecurityConfiguration;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = ProfesorController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ProfesorControllerTest {

	@MockBean
	private ProfesorService profesorService;
	@MockBean
	private AsignaturaService asignaturaService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Profesor profesor = new Profesor();
		User user = new User();
		user.setUsername("profesor1");
		user.setPassword("Profesor1!");
		profesor.setFirstName("Perez");
		profesor.setLastName("Perez");
		profesor.setId(10);
		profesor.setEmail("perez@mail.com");
		profesor.setTelephone("657585793");
		profesor.setAsignaturas(new HashSet<>());
		profesor.setUser(user);
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCrationForm() throws Exception {
		mockMvc.perform(get("/profesores/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("profesores/createOrUpdateProfesorForm"))
		.andExpect(model().attributeExists("profesor"));  
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCeationFormSuccess() throws Exception {
		mockMvc.perform(post("/profesores/new")
			.with(csrf())
			.param("firstName", "Mario")
			.param("lastName", "Viera")
			.param("telephone", "633572849")
			.param("email", "manuvierod@gmail.com")
			.param("user.username", "profesor1")
			.param("user.password", "Profesor1!"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/login"));

		}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCeationFormHasErrors() throws Exception {
		mockMvc.perform(post("/profesores/new")
			.with(csrf())
			.param("firstName", "")
			.param("lastName", "Viera")
			.param("telephone", "633572849")
			.param("email", "manuvierod@gmail.com")
			.param("user.username", "profesor1")
			.param("user.password", "Profesor1!"))
			.andExpect(model().attributeHasErrors("profesor"))
			.andExpect(model().attributeHasFieldErrors("profesor", "firstName"))
			.andExpect(view().name("profesores/createOrUpdateProfesorForm"));

		}

	@WithMockUser(value = "profesor1")
	@Test
	void testProcessInitUpdateForm() throws Exception {
		mockMvc.perform(get("/profesores/{profesorId}/edit",10))
			.andExpect(status().isOk())
			.andExpect(view().name("profesores/createOrUpdateProfesorForm"));
	}

	@WithMockUser(value = "profesor1")
	@Test
	void testEditProfesorSuccess() throws Exception {
		mockMvc.perform(post("/profesores/{profesorId}/edit", 10)
			.with(csrf())
			.param("firstName", "Mario")
			.param("lastName", "Viera")
			.param("telephone", "633572849")
			.param("email", "manuvierod@gmail.com")
			.param("user.username", "profesor1")
			.param("user.password", "Profesor1!"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/profesores/miPerfil"));

		}

	@WithMockUser(value = "profesor1")
	@Test
	void testEditProfesorHasErrors() throws Exception {
		mockMvc.perform(post("/profesores/{profesorId}/edit", 10)
			.with(csrf())
			.param("firstName", "")
			.param("lastName", "Viera")
			.param("telephone", "666123123")
			.param("email", "manuvierod@gmail.com")
			.param("user.username", "profesor1")
			.param("user.password", "Profesor1!"))
		.andExpect(model().attributeHasErrors("profesor"))
		.andExpect(model().attributeHasFieldErrors("profesor", "firstName"))
		.andExpect(view().name("profesores/createOrUpdateProfesorForm"));

		}

	@WithMockUser(value = "spring")
	@Test
	void miPerfilTest() throws Exception {
		mockMvc.perform(get("/profesores/miPerfil")).andExpect(status().isOk());
	}

}
