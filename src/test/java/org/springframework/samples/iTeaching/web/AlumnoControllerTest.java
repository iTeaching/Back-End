package org.springframework.samples.iTeaching.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.*;
import org.springframework.samples.iTeaching.configuration.SecurityConfiguration;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Authorities;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AuthoritiesService;
import org.springframework.samples.iTeaching.service.StorageService;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(controllers = AlumnoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class AlumnoControllerTest {
	
	@Autowired
	private AlumnoController alumnoController;
	
	@MockBean
	private AlumnoService alumnoService;
	@MockBean
	private StorageService storageService;
	@MockBean
	private AuthoritiesService authService;
	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() {
		Alumno alumno = new Alumno();
		User user = new User();
		user.setUsername("alumnoTest");
		Authorities authorities = new Authorities();
		authorities.setAuthority("alumno");
		authorities.setUser(user);
		Set<Authorities> aut = new HashSet<Authorities>();
		aut.add(authorities);
		user.setAuthorities(aut);
		user.setEnabled(true);
		user.setPassword("Pa$$w0rd1");
		alumno.setUser(user);
		alumno.setEmail("test@gmail.com");
		alumno.setFirstName("Test");
		alumno.setLastName("Prueba");
		alumno.setId(24);
		alumno.setTelephone("612345678");
		
	}
	@WithMockUser(value = "spring")
	@Test
	void initCreationFormTest() throws Exception {
		mockMvc.perform(get("/alumnos/new")).andExpect(status().isOk())
		.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void processCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/alumnos/new").with(csrf())
				.param("firstName", "test")
				.param("lastName", "prueba")
				.param("telephone", "612345678")
				.param("email", "test1@gmail.com")
				.param("user.username", "alumnoTest1")
				.param("user.password", "Pa$$w0rd1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/login"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void processCreationFormFailFirstName() throws Exception {
		mockMvc.perform(post("/alumnos/new").with(csrf())
				.param("firstName", "")
				.param("lastName", "prueba")
				.param("telephone", "612345678123457897654578")
				.param("email", "test@gmail.com")
				.param("user.username", "alumnoTest")
				.param("user.password", "Pa$$w0rd1"))
				.andExpect(model().attributeHasErrors("alumno"))
				.andExpect(model().attributeHasFieldErrors("alumno","firstName"))
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void processCreationFormFailLastName() throws Exception {
		mockMvc.perform(post("/alumnos/new").with(csrf())
				.param("firstName", "Test")
				.param("lastName", "")
				.param("telephone", "612345678123457897654578")
				.param("email", "test@gmail.com")
				.param("user.username", "alumnoTest")
				.param("user.password", "Pa$$w0rd1"))
				.andExpect(model().attributeHasErrors("alumno"))
				.andExpect(model().attributeHasFieldErrors("alumno","lastName"))
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	

	
	@WithMockUser(value = "spring")
	@Test
	void processCreationFormFailEmail() throws Exception {
		mockMvc.perform(post("/alumnos/new").with(csrf())
				.param("firstName", "test")
				.param("lastName", "prueba")
				.param("telephone", "612345678")
				.param("email", "")
				.param("user.username", "alumnoTest")
				.param("user.password", "Pa$$w0rd1"))
				.andExpect(model().attributeHasErrors("alumno"))
				.andExpect(model().attributeHasFieldErrors("alumno","email"))
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void processCreationFormFailPassword() throws Exception {
		mockMvc.perform(post("/alumnos/new").with(csrf())
				.param("firstName", "test")
				.param("lastName", "prueba")
				.param("telephone", "612345678")
				.param("email", "test@gmailcom")
				.param("user.username", "alumnoTest")
				.param("user.password", "contraseñamala"))
				.andExpect(model().attributeHasErrors("alumno"))
				.andExpect(model().attributeHasFieldErrors("alumno","user.password"))
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void initFindFormTest() throws Exception {
		mockMvc.perform(get("/alumnos/find")).andExpect(status().isOk())
				.andExpect(view().name("alumnos/findOwners"));
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void initUpdateOwnerForm() throws Exception {

		mockMvc.perform(get("/alumnos/{alumnoId}/edit",14)).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void processUpdateOwnerFormSuccess() throws Exception {
		mockMvc.perform(post("/alumnos/24/edit").with(csrf())
				.param("firstName", "Test")
				.param("lastName", "Prueba")
				.param("telephone", "612345678")
				.param("email", "test@gmail.com")
				.param("user.username", "alumnoTest")
				.param("user.password", "nuevaPa$$w0rd1"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/alumnos/miPerfil"));
	}
	

	
	@WithMockUser(value = "alumnoTest")
	@Test
	void processUpdateOwnerFormFailEmail() throws Exception {
		mockMvc.perform(post("/alumnos/24/edit").with(csrf())
				.param("firstName", "Test")
				.param("lastName", "Prueba")
				.param("telephone", "612345678")
				.param("email", "")
				.param("user.username", "alumnoTest")
				.param("user.password", "nuevaPa$$w0rd1"))
				.andExpect(model().attributeHasErrors("alumno"))
				.andExpect(model().attributeHasFieldErrors("alumno","email"))
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void processUpdateOwnerFormFailPassword() throws Exception {
		mockMvc.perform(post("/alumnos/24/edit").with(csrf())
				.param("firstName", "Test")
				.param("lastName", "Prueba")
				.param("telephone", "612345678")
				.param("email", "test@gmail.com")
				.param("user.username", "alumnoTest")
				.param("user.password", "contraseñamala"))
				.andExpect(model().attributeHasErrors("alumno"))
				.andExpect(model().attributeHasFieldErrors("alumno","user.password"))
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void processUpdateOwnerFormFailFirstName() throws Exception {
		mockMvc.perform(post("/alumnos/24/edit").with(csrf())
				.param("firstName", "")
				.param("lastName", "Prueba")
				.param("telephone", "612345678")
				.param("email", "test@gmail.com")
				.param("user.username", "alumnoTest")
				.param("user.password", "contraseñamala"))
				.andExpect(model().attributeHasErrors("alumno"))
				.andExpect(model().attributeHasFieldErrors("alumno","firstName"))
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void processUpdateOwnerFormFailLastName() throws Exception {
		mockMvc.perform(post("/alumnos/24/edit").with(csrf())
				.param("firstName", "Test")
				.param("lastName", "")
				.param("telephone", "612345678")
				.param("email", "test@gmail.com")
				.param("user.username", "alumnoTest")
				.param("user.password", "contraseñamala"))
				.andExpect(model().attributeHasErrors("alumno"))
				.andExpect(model().attributeHasFieldErrors("alumno","lastName"))
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void miPerfilSuccess() throws Exception {
		mockMvc.perform(get("/alumnos/miPerfil")).andExpect(status().isOk())
				.andExpect(view().name("alumnos/miPerfil"));
	}
	
}
