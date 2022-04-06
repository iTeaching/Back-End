package org.springframework.samples.iTeaching.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.iTeaching.configuration.SecurityConfiguration;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.Valoracion;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(value = ValoracionController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class ValoracionControllerTest {
	
	@MockBean
	private ValoracionController valoracionController;
	
	@MockBean
	private ProfesorService profesorService;
	@MockBean
	private AlumnoService alumnoService;
	@MockBean
	private AsignaturaService asignaturaService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() {
		
		Valoracion valoracion = new Valoracion();
		
		Profesor profesor = profesorService.findProfesorById(1);
		Alumno alumno = alumnoService.findAlumnoById(1);
		Asignatura asignatura = asignaturaService.findById(1);
		
		valoracion.setAlumno(alumno);
		valoracion.setAsignatura(asignatura);
		valoracion.setComentario("prueba");
		valoracion.setId(24);
		valoracion.setProfesor(profesor);
		valoracion.setPuntuacion(3.0);
		
	}
	
	@WithMockUser(value="alumno1")
	@Test
	void initCreationFormTest() throws Exception {
		mockMvc.perform(get("/asignatura/1/valoraciones/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("asignatura/1/valoraciones/new"));
	}
	
	// @WithMockUser(value="alumno1")
	// @Test
	// void processCreationFormSuccess() throws Exception {
	// 	mockMvc.perform(post("/asignatura/1/valoraciones/new")
	// 	.with(csrf())
	// 	.param("puntuacion", "4.0")
	// 	.param("comentario", "test"))
	// 	.andExpect(status().is3xxRedirection())
	// 	.andExpect(view().name("redirect:/ofertas/find"));
	// }
	
	// @WithMockUser(value="alumno1")
	// @Test
	// void processCreationFormHasErrors() throws Exception {
	// 	mockMvc.perform(post("/asignatura/1/valoraciones/new")
	// 			.with(csrf())
	// 			.param("puntuacion", "4.0")
	// 			.param("comentario", ""))
	// 			.andExpect(model().attributeHasErrors("valoracion"))
	// 			.andExpect(model().attributeHasFieldErrors("valoraciones", "puntuacion"))
	// 			.andExpect(view().name("valoraciones/createValoracionForm"));
	// }
	
	@WithMockUser(value="alumno1")
	@Test
	void ListValoracionTest() throws Exception {
		mockMvc.perform(get("/asignatura/1/valoraciones/profesor/1"))
		.andExpect(status().isOk())
		.andExpect(view().name("asignatura/1/valoraciones/profesor/1"));
	}

}
