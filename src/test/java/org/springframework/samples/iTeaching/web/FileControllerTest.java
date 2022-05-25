package org.springframework.samples.iTeaching.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.samples.iTeaching.configuration.SecurityConfiguration;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.FileiTeachingService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest(value = FileController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class FileControllerTest {

	@MockBean 
	private FileiTeachingService fileiTeachingService;
	
	@MockBean
	private AsignaturaService asignaturaService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@WithMockUser(value="spring")
	@Test
	void getTest() throws Exception {
		mockMvc.perform(get("/asignatura/{asignaturaId}/files",1))
		.andExpect(status().isOk())
		.andExpect(view().name("files/fileList"));
	}
	
	@WithMockUser(value="spring")
	@Test
	void getFileHasErrors() throws Exception {
		mockMvc.perform(get("/files/noexisteestearchivo.pdf"))
		.andExpect(status().is4xxClientError());
	}
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Test
    public void testUploadMultipleFiles() throws Exception {

        MockMultipartFile firstFile = new MockMultipartFile("images", "image1", "image/png", "png".getBytes());
        MockMultipartFile secondFile = new MockMultipartFile("data", "other-file-name.data", "text/plain", "some other type".getBytes());
        MockMultipartFile jsonFile = new MockMultipartFile("json", "", "application/json", "{\"json\": \"someValue\"}".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc.perform(MockMvcRequestBuilders.multipart("/asignatura/{asignaturaId}/files",1)
                        .file(firstFile)
                        .file(secondFile)
                        .file(jsonFile)
                        .param("some-random", "4"))
                    .andExpect(status().is(200));
    }
	
}