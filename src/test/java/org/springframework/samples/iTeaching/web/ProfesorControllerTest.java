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
import org.springframework.samples.iTeaching.model.estadoClase;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.AuthoritiesService;
import org.springframework.samples.iTeaching.service.ClaseService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.samples.iTeaching.service.StorageService;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = ProfesorController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ProfesorControllerTest {

	@MockBean
	private ProfesorService profesorService;
	
	@MockBean
	private StorageService storageService;
	@MockBean
	private AuthoritiesService authService;
	@MockBean
	private UserService userService;

	@MockBean
	private AsignaturaService asignaturaService;

	@MockBean
	private ClaseService claseService;
	@MockBean
	private AlumnoService alumnoService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Profesor profesor = new Profesor();
		User user = new User();
		user.setUsername("profesor4");
		user.setPassword("Profesor1!");
		profesor.setFirstName("Perez");
		profesor.setLastName("Perez");
		profesor.setId(10);
		profesor.setEmail("perez@mail.com");
		profesor.setTelephone("657585793");
		profesor.setDivision(0);
		profesor.setPuntuacion(0.0);
		profesor.setUser(user);
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/profesores/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("profesores/createOrUpdateProfesorForm"))
		.andExpect(model().attributeExists("profesor"));  
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/profesores/new")
			.with(csrf())
			.param("firstName", "Roberto")
			.param("lastName", "Ruiz")
			.param("telephone", "666111333")
			.param("email", "profesormolon1@gmail.com")
			.param("user.username", "profesor1")
			.param("user.password", "Profesor1!"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/login"));

		}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/profesores/new")
			.with(csrf())
			.param("firstName", "")
			.param("lastName", "Viera")
			.param("telephone", "633572849")
			.param("email", "manuvierod@gmail.com")
			.param("division", "0")
			.param("puntuacion", "2.0")
			.param("user.username", "profesor1")
			.param("user.password", "Profesor1!"))
			.andExpect(model().attributeHasErrors("profesor"))
			.andExpect(model().attributeHasFieldErrors("profesor", "firstName"))
			.andExpect(view().name("profesores/createOrUpdateProfesorForm"));

		}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrorsLastName() throws Exception {
		mockMvc.perform(post("/profesores/new")
			.with(csrf())
			.param("firstName", "Jose")
			.param("lastName", "")
			.param("telephone", "633572849")
			.param("email", "manuvierod@gmail.com")
			.param("division", "0")
			.param("puntuacion", "2.0")
			.param("user.username", "profesor1")
			.param("user.password", "Profesor1!"))
			.andExpect(model().attributeHasErrors("profesor"))
			.andExpect(model().attributeHasFieldErrors("profesor", "lastName"))
			.andExpect(view().name("profesores/createOrUpdateProfesorForm"));

		}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrorsEmail() throws Exception {
		mockMvc.perform(post("/profesores/new")
			.with(csrf())
			.param("firstName", "Jose")
			.param("lastName", "Viera")
			.param("telephone", "633572849")
			.param("email", "")
			.param("division", "0")
			.param("puntuacion", "2.0")
			.param("user.username", "profesor1")
			.param("user.password", "Profesor1!"))
			.andExpect(model().attributeHasErrors("profesor"))
			.andExpect(model().attributeHasFieldErrors("profesor", "email"))
			.andExpect(view().name("profesores/createOrUpdateProfesorForm"));

		}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrorsTelephone() throws Exception {
		mockMvc.perform(post("/profesores/new")
			.with(csrf())
			.param("firstName", "Jose")
			.param("lastName", "Viera")
			.param("telephone", "")
			.param("email", "manuvierod@gmail.com")
			.param("division", "0")
			.param("puntuacion", "2.0")
			.param("user.username", "profesor1")
			.param("user.password", "Profesor1!"))
			.andExpect(model().attributeHasErrors("profesor"))
			.andExpect(model().attributeHasFieldErrors("profesor", "telephone"))
			.andExpect(view().name("profesores/createOrUpdateProfesorForm"));

		}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrorsUsername() throws Exception {
		mockMvc.perform(post("/profesores/new")
			.with(csrf())
			.param("firstName", "Jose")
			.param("lastName", "Viera")
			.param("telephone", "633572849")
			.param("email", "manuvierod@gmail.com")
			.param("division", "0")
			.param("puntuacion", "2.0")
			.param("user.username", "")
			.param("user.password", "Profesor1!"))
			.andExpect(model().attributeHasErrors("profesor"))
			.andExpect(model().attributeHasFieldErrors("profesor", "user.username"))
			.andExpect(view().name("profesores/createOrUpdateProfesorForm"));

		}
	
	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrorsPassword() throws Exception {
		mockMvc.perform(post("/profesores/new")
			.with(csrf())
			.param("firstName", "Jose")
			.param("lastName", "Viera")
			.param("telephone", "633572849")
			.param("email", "manuvierod@gmail.com")
			.param("division", "0")
			.param("puntuacion", "2.0")
			.param("user.username", "profesor1")
			.param("user.password", ""))
			.andExpect(model().attributeHasErrors("profesor"))
			.andExpect(model().attributeHasFieldErrors("profesor", "user.password"))
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
			.param("division", "0")
			.param("puntuacion", "2.0")
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
			.param("division", "0")
			.param("puntuacion", "2.0")
			.param("user.username", "profesor1")
			.param("user.password", "Profesor1!"))
		.andExpect(model().attributeHasErrors("profesor"))
		.andExpect(model().attributeHasFieldErrors("profesor", "firstName"))
		.andExpect(view().name("profesores/createOrUpdateProfesorForm"));

		}

	@WithMockUser(value = "spring")
	@Test
	void miPerfilTest() throws Exception {
		mockMvc.perform(get("/profesores/miPerfil")).andExpect(status().isOk())
		.andExpect(view().name("profesores/miPerfil"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testViewChangeAvatar() throws Exception {
		mockMvc.perform(get("/profesor/miPerfil/changeAvatar/{profesorId}",1))
		.andExpect(status().isOk())
		.andExpect(view().name("profesores/changeAvatar"));
	}
	
//	@WithMockUser(value = "spring")
//	@Test
//	void testSaveChangeAvatar() throws Exception {
//		mockMvc.perform(post("/profesor/miPerfil/changeAvatar").with(csrf())
//				.param("avatar", "avatar1650970806192.png"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("redirect:/profesores/miPerfil"));
//	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCrearClaseError() throws Exception {
		mockMvc.perform(get("/profesor/nuevaClase"))
		.andExpect(status().isOk())
		.andExpect(view().name("exception"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCrearClasePostError() throws Exception {
		mockMvc.perform(post("/profesor/nuevaClase").with(csrf())
				.param("estadoclase", "estadoClase.solicitada"))
		.andExpect(status().isOk())
		.andExpect(view().name("exception"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testAceptarClase() throws Exception {
		mockMvc.perform(get("/profesor/aceptar/{claseId}",1))
		.andExpect(status().isOk())
		.andExpect(view().name("profesores/aceptarClase"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testAceptarClasePost() throws Exception {
		mockMvc.perform(post("/profesor/aceptar/{claseId}",1).with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/profesores/miPerfil"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCancelarClase() throws Exception {
		mockMvc.perform(get("/profesor/cancelar/{claseId}",1))
		.andExpect(status().isOk())
		.andExpect(view().name("profesores/cancelarClase"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testCancelarClasePost() throws Exception {
		mockMvc.perform(post("/profesor/cancelar/{claseId}",1).with(csrf()))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/profesores/miPerfil"));
	}

}
