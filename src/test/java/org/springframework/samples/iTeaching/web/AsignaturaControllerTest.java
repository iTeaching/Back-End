package org.springframework.samples.iTeaching.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.iTeaching.configuration.SecurityConfiguration;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.User;
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

@WebMvcTest(value = AsignaturaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class AsignaturaControllerTest {
	
	@MockBean
	private AsignaturaService asignaturaService;
	@MockBean
	private AlumnoService alumnoService;
	@MockBean
	private UserService userService;
	@MockBean
	private ProfesorService profesorService;

	@MockBean
	private StorageService storageService;

	@MockBean
	private AuthoritiesService authService;
	
	@MockBean
	private ClaseService claseService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCrationForm() throws Exception {
		mockMvc.perform(get("/asignaturas/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("asignaturas/createAsignaturaForm"))
		.andExpect(model().attributeExists("asignatura"));
	}
	
//	 @WithMockUser(value = "prof1")
//	 @Test
//	 void testProcessCeationFormSuccess() throws Exception {
//	 	mockMvc.perform(post("/asignaturas/new")
//	 		.with(csrf())
//	 		.param("profesor", "1")
//	 		.param("nombre", "Fisica2")
//	 		.param("titulo_anuncio", "Clases Fisica")
//	 		.param("url", "https://ispp1.whereby.com/4f32158f-ca85-4a72-9700-7c334b080f56")
//	 		.param("descripcion", "Clases baratas de fisica2")
//	 		.param("precio", "12"))
//	 		.andExpect(status().is3xxRedirection())
//	 		.andExpect(view().name("redirect:/asignaturas"));
//	 }
	
	@WithMockUser(value = "prof1")
	@Test
	void testProcessCeationFormHasErrors() throws Exception {
		mockMvc.perform(post("/asignaturas/new")
			.with(csrf())
			.param("nombre", "")
			.param("titulo_anuncio", "Clases Fisica")
			.param("url", Clases.url())
			.param("descripcion", "Clases baratas de fisica2")
			.param("precio", "12.0"))
			.andExpect(model().attributeHasErrors("asignatura"))
			.andExpect(model().attributeHasFieldErrors("asignatura", "nombre"))
			.andExpect(view().name("asignaturas/createAsignaturaForm"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void testMySalas() throws Exception {
		mockMvc.perform(get("/asignaturas"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/"));
	}
	
//	@WithMockUser(value = "alumno1")
//	@Test
//	void testGetSalas() throws Exception {
//		mockMvc.perform(get("/asignaturas/{asignaturaId}",1))
//		.andExpect(status().isOk())
//		.andExpect(view().name("asignaturas/view"));
//	}
	
	@WithMockUser(value="alumno1")
	@Test
	void testFindAnuncios() throws Exception{
		mockMvc.perform(get("/ofertas/find"))
		.andExpect(status().isOk())
		.andExpect(model().attributeExists("asignaturas"))
		.andExpect(view().name("asignaturas/findOfertas"));
	}
	
//	 @WithMockUser(value = "alumno1")
//	 @Test
//	 void testFindAnunciosByAsignatura() throws Exception{
//		 mockMvc.perform(post("/ofertas/findAsignatura").with(csrf()))
//		 .andExpect(status().isOk())
//		 .andExpect(view().name("asignaturas/findOfertas"));
//	}
	
//	 @WithMockUser(value = "alumno1")
//	 @Test
//	 void testSuscribirAsignatura() throws Exception {
//		 mockMvc.perform(get("/asignaturas/{asignaturaId}/apply",10))
//		 .andExpect(status().isOk())
//		 .andExpect(view().name("redirect:/asignaturas"));
//		}
	 
//	@WithMockUser(value="pepeperez")
//	@Test
//	void testCrearClase() throws Exception{
//		mockMvc.perform(get("/asignaturas/{asignaturaId}/nuevaClase",1))
//		.andExpect(status().isOk())
//		.andExpect(model().attributeExists("diccionario"))
//		.andExpect(view().name("asignaturas/1/nuevaClase"));
//	}
	
//	@WithMockUser(value="pepeperez")
//	@Test
//	void testCrearClasePost() throws Exception{
//		mockMvc.perform(post("/asignaturas/{asignaturaId}/nuevaClase",1))
//		.andExpect(status().isOk())
//		.andExpect(view().name("redirect:/alumnos/miPerfil"));
//	}
}
