package org.springframework.samples.iTeaching.web;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.iTeaching.configuration.SecurityConfiguration;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.service.AnuncioService;
import org.springframework.samples.iTeaching.service.AuthoritiesService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(controllers = AnuncioController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class AnuncioControllerTest {

	@Autowired
	private AnuncioController anuncioController;
	
	@MockBean
	private AnuncioService anuncioService;
	@MockBean
	private ProfesorService profesorService;
	@MockBean
	private AuthoritiesService authoritiesServices;
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		Anuncio anuncio = new Anuncio();
		anuncio.setId(3);
		anuncio.setAsignatura("Matematicas");
		anuncio.setDescripcion("Clases de mates");
		anuncio.setPrecio(12.0);
		anuncio.setTitulo("Clases");
		anuncio.setProfesor(profesorService.findProfesorById(1));
		Collection<Anuncio> anuncio1=new ArrayList<Anuncio>();
		anuncio1.add(anuncio);
	}
	
	@WithMockUser(value = "spring")
	@Test
	void findMisAnunciosTest() throws Exception{
		mockMvc.perform(get("ofertas/misOfertas")).andExpect(status().isOk());
	}
	
	@WithMockUser(value = "prof1")
	@Test
	void testProcessCreationFormSucces() throws Exception {
		mockMvc.perform(post("/ofertas/new").with(csrf())
				.param("Asignatura", "Mates")
				.param("Descripcion", "Clases baratas")
				.param("Pecio", "10.0")
				.param("Titulo", "Clases")
				.param("id", "3")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/ofertas/misOfertas"));
	}
}
