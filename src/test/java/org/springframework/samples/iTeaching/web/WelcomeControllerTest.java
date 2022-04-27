package org.springframework.samples.iTeaching.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.iTeaching.configuration.SecurityConfiguration;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = WelcomeController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class WelcomeControllerTest {

	@MockBean
	private ProfesorService profesorservice;
	@MockBean
	private AlumnoService alumnoservice;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "spring")
	@Test
	void testWelcome() throws Exception {
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("welcome"));
	}
	
	@WithMockUser(roles = "user")
	@Test
	void testLoggedUserError() throws Exception {
		mockMvc.perform(get("/logged"))
		.andExpect(status().isOk())
		.andExpect(view().name("exception"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testTerminosCondiciones() throws Exception {
		mockMvc.perform(get("/TerminosYCondiciones"))
		.andExpect(status().isOk())
		.andExpect(view().name("terminoscondiciones"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testGarantiaPrivacidad() throws Exception {
		mockMvc.perform(get("/GarantiaDePrivacidad"))
		.andExpect(status().isOk())
		.andExpect(view().name("garantiaPrivacidad"));
	}
	
}
