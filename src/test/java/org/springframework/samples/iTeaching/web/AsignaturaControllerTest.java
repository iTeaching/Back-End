package org.springframework.samples.iTeaching.web;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.iTeaching.configuration.SecurityConfiguration;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;

@WebMvcTest(value = AsignaturaController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class AsignaturaControllerTest {
	
	@MockBean
	private AsignaturaService asignaturaService;
	

}
