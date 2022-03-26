package org.springframework.samples.iTeaching.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.*;
import org.springframework.samples.iTeaching.configuration.SecurityConfiguration;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Authorities;
import org.springframework.samples.iTeaching.model.Sala;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.SalaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(controllers = AlumnoController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class AlumnoControllerTest {
	
	@Autowired
	private AlumnoController alumnoController;
	
	@MockBean
	private AlumnoService alumnoService;
	@MockBean
	private SalaService salaService;
	
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
		user.setPassword("password");
		alumno.setUser(user);
		alumno.setEmail("test@gmail.com");
		alumno.setFirstName("Test");
		alumno.setLastName("Prueba");
		alumno.setId(24);
		alumno.setTelephone("612345678");
		List<Sala> salas = salaService.findAll();
		alumno.setSalas(salas);
		
	}
	@WithMockUser(value = "spring")
	@Test
	void initCreationFormTest() throws Exception {
		mockMvc.perform(get("/alumnos/new")).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "spring")
	@Test
	void processCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/alumnos/new").with(csrf())
				.param("First Name", "test")
				.param("Last Name", "prueba")
				.param("Telephone", "612345678")
				.param("Email", "test@gmail.com")
				.param("Username", "alumnoTest")
				.param("Password", "password"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/login"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void processCreationFormFailTelephone() throws Exception {
		mockMvc.perform(post("/alumnos/new").with(csrf())
				.param("First Name", "test")
				.param("Last Name", "prueba")
				.param("Telephone", "612345678123457897654578")
				.param("Email", "test@gmail.com")
				.param("Username", "alumnoTest")
				.param("Password", "password"))
				.andExpect(status().is4xxClientError())
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void processCreationFormFailEmail() throws Exception {
		mockMvc.perform(post("/alumnos/new").with(csrf())
				.param("First Name", "test")
				.param("Last Name", "prueba")
				.param("Telephone", "612345678")
				.param("Email", "testatgmail.com")
				.param("Username", "alumnoTest")
				.param("Password", "password"))
				.andExpect(status().is4xxClientError())
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void initFindFormTest() throws Exception {
		mockMvc.perform(get("/alumnos/find")).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void initUpdateOwnerForm() throws Exception {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Alumno alumno = alumnoService.findAlumnoByUsername(username);
		mockMvc.perform(get("/alumnos/{alumnoId}/edit").queryParam("alumnoId", alumno.getId().toString())).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void processUpdateOwnerFormSuccess() throws Exception {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Alumno alumno = alumnoService.findAlumnoByUsername(username);
		mockMvc.perform(post("/alumnos/{alumnoId}/edit").queryParam("alumnoId", alumno.getId().toString()).with(csrf())
				.param("First Name", alumno.getFirstName())
				.param("Last Name", alumno.getLastName())
				.param("Telephone", alumno.getTelephone())
				.param("Email", alumno.getEmail())
				.param("Username", username)
				.param("Password", "nuevaPassword"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/alumnos/{alumnoId}"));
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void processUpdateOwnerFormFailTelephone() throws Exception {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Alumno alumno = alumnoService.findAlumnoByUsername(username);
		mockMvc.perform(post("/alumnos/{alumnoId}/edit").queryParam("alumnoId", alumno.getId().toString()).with(csrf())
				.param("First Name", alumno.getFirstName())
				.param("Last Name", alumno.getLastName())
				.param("Telephone", "612345678123457897654578")
				.param("Email", alumno.getEmail())
				.param("Username", username)
				.param("Password", "nuevaPassword"))
				.andExpect(status().is4xxClientError())
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void processUpdateOwnerFormFailEmail() throws Exception {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Alumno alumno = alumnoService.findAlumnoByUsername(username);
		mockMvc.perform(post("/alumnos/{alumnoId}/edit").queryParam("alumnoId", alumno.getId().toString()).with(csrf())
				.param("First Name", alumno.getFirstName())
				.param("Last Name", alumno.getLastName())
				.param("Telephone", alumno.getTelephone())
				.param("Email", "testatgmail.com")
				.param("Username", username)
				.param("Password", "nuevaPassword"))
				.andExpect(status().is4xxClientError())
				.andExpect(view().name("alumnos/createOrUpdateAlumnoForm"));
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void deleteAlumnoSuccess() throws Exception {
		UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		String username= clienteDetails.getUsername();
		Alumno alumno = alumnoService.findAlumnoByUsername(username);
		mockMvc.perform(post("/alumnos/{alumnoId}/delete").queryParam("alumnoId", alumno.getId().toString()).with(csrf()))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("welcome"));
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void deleteAlumnoFail() throws Exception {
		mockMvc.perform(post("/alumnos/1/delete").with(csrf()))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("welcome"));
	}
	
	@WithMockUser(value = "alumnoTest")
	@Test
	void miPerfilSuccess() throws Exception {
		mockMvc.perform(get("/alumnos/miPerfil")).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "prof1")
	@Test
	void miPerfilFail() throws Exception {
		mockMvc.perform(get("/alumnos/miPerfil")).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/"));
	}


}
