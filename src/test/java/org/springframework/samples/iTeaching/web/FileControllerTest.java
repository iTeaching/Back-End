package org.springframework.samples.iTeaching.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

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
		.andExpect(status().isOk());
	}
	
	@WithMockUser(value="spring")
	@Test
	void getFileHasErrors() throws Exception {
		mockMvc.perform(get("/files/noexisteestearchivo.pdf"))
		.andExpect(status().is4xxClientError());
	}
	
	//@WithMockUser(value="spring")
	//@Test
	//void uploadFilesTest() throws Exception{
	//	MultipartFile test = new MockMultipartFile("img1.jpg", new FileInputStream(new File("..\\Back-End\\src\\main\\resources\\static\\resources\\images\\img1.jpg")));
		//List<MultipartFile> list = new ArrayList<MultipartFile>();
		//list.add(test);
		//mockMvc.perform(post("/asignatura/{asignaturaId}/files",1).with(csrf())
		//		.requestAttr("files", list))
			//	.andExpect(status().is2xxSuccessful());

	//}
	
	//@WithMockUser(value="spring")
	//@Test
	//void downloadFileTest() throws Exception {
	//	mockMvc.perform(get("/downloadFile/{fileId}",1))
		//.andExpect(status().isOk());
	//}
	
}
