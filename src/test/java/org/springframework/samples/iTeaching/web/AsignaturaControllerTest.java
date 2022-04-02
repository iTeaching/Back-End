package org.springframework.samples.iTeaching.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.iTeaching.configuration.SecurityConfiguration;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.ProfesorService;
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
	
	@Autowired
	private MockMvc mockMvc;
	
//	@BeforeEach
//	void setup() {
//		Asignatura asignatura=new Asignatura();
//		asignatura.setDescripcion("Clases baratas");
//		asignatura.setId(6);
//		asignatura.setNombre("Fisica2");
//		asignatura.setPrecio(12.5);
//		asignatura.setProfesor(profesorService.findProfesorById(1));
//		asignatura.setTitulo_anuncio("Clases fisica2");
//		asignatura.setUrl("https://acme.whereby.com/0702a4a5-1bd8-49bf-9ede-59b9eca930d6");
//		
//	}
	
	@WithMockUser(value = "spring")
	@Test
	void testInitCrationForm() throws Exception {
		mockMvc.perform(get("/asignaturas/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("asignaturas/createAsignaturaForm"))
		.andExpect(model().attributeExists("asignatura"));
	}
	
	@WithMockUser(value = "prof1")
	@Test
	void testProcessCeationFormSuccess() throws Exception {
		mockMvc.perform(post("/asignaturas/new")
			.with(csrf())
			.param("nombre", "Fisica2")
			.param("titulo_anuncio", "Clases Fisica")
			.param("url", Clases.url())
			.param("descripcion", "Clases baratas de fisica2")
			.param("precio", "12.0"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/asignaturas"));
	}
	
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
		mockMvc.perform(get("/asignaturas/list")).andExpect(status().isOk());
	}
	
	
}
