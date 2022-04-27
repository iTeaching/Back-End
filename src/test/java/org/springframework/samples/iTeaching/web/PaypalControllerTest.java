package org.springframework.samples.iTeaching.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.samples.iTeaching.model.Orden;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.PaypalService;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;

@WebMvcTest(value = PaypalController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class PaypalControllerTest {
	
	@MockBean
	private PaypalService paypalService;
	@MockBean
    private UserService userService;
	@MockBean
    private AsignaturaService asignaturaService;
	@MockBean
    private AlumnoService alumnoService;
	
	@Autowired
	private MockMvc mockMvc;
	
	public static final String SUCCESS_URL = "/pay/success";
	public static final String CANCEL_URL = "/pay/cancel";
	
	@WithMockUser(value = "spring")
	@Test
	void paymentTest() throws Exception {
		Orden orden=new Orden();
		orden.setCurrency("EUR");
		orden.setIntent("Sale");
		orden.setDescription("decripcon");
		orden.setMethod("PAYPAL");
		orden.setPrice(21.0);
		Payment payment = paypalService.createPayment(orden.getPrice(), orden.getCurrency(), orden.getMethod(),
				orden.getIntent(), orden.getDescription(), "http://localhost:8080/" + CANCEL_URL,
				"http://localhost:8080/" + SUCCESS_URL);
//		Links link=payment.getLinks().get(0);
		mockMvc.perform(post("/pay")
			.with(csrf()))
			.andExpect(status().isOk());

		}
	
	@WithMockUser(value = "spring")
	@Test
	void cancelPayTest() throws Exception {
		mockMvc.perform(get("/pay/cancel"))
			.andExpect(status().isOk())
			.andExpect(view().name("cancel"));
	}
	
	@WithMockUser(value = "spring")
	@Test
	void pagarTestException() throws Exception {
		Orden orden=new Orden();
		orden.setCurrency("EUR");
		orden.setIntent("Sale");
		orden.setDescription("decripcon");
		orden.setMethod("PAYPAL");
		orden.setPrice(21.0);
		paypalService.createPayment(orden.getPrice(), orden.getCurrency(), orden.getMethod(),
				orden.getIntent(), orden.getDescription(), "http://localhost:8080/" + CANCEL_URL,
				"http://localhost:8080/" + SUCCESS_URL);
		mockMvc.perform(get("/pagar"))
			.andExpect(status().isOk())
			.andExpect(view().name("exception"));
	}

}
