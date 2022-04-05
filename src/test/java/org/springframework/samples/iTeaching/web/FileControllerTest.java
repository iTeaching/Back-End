package org.springframework.samples.iTeaching.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.samples.iTeaching.configuration.SecurityConfiguration;
import org.springframework.samples.iTeaching.service.FileServiceImpl;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

@WebMvcTest(value = FileController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class FileControllerTest {

	@MockBean
	private FileServiceImpl fileService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		MultipartFile test = new MockMultipartFile("test.xlsx", new FileInputStream(new File("..\\Back-End\\src\\main\\resources\\test.xlsx")));
		fileService.save(test);
		fileService.load(test.getName());
		List<MultipartFile> list = new ArrayList<MultipartFile>();
		list.add(test);
		
		
	}
	
	@WithMockUser(value="spring")
	@Test
	void getFileHasErrors() throws Exception {
		mockMvc.perform(get("/files/noexisteestearchivo.pdf"))
		.andExpect(status().is4xxClientError());
	}
	
	@WithMockUser(value="spring")
	@Test
	void getAllFilesTest() throws Exception{
		mockMvc.perform(get("/files"))
		.andExpect(status().is2xxSuccessful());
	}
	
	@WithMockUser(value="spring")
	@Test
	void uploadFilesTest() throws Exception{
		MultipartFile test = new MockMultipartFile("test.xlsx", new FileInputStream(new File("..\\Back-End\\src\\main\\resources\\test.xlsx")));
		List<MultipartFile> list = new ArrayList<MultipartFile>();
		list.add(test);
		mockMvc.perform(post("/upload").with(csrf())
				.requestAttr("files", list))
		.andExpect(status().is2xxSuccessful());

	}
	
}
