package org.springframework.samples.iTeaching.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Anuncio;
import org.springframework.samples.iTeaching.model.Orden;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AnuncioService;
import org.springframework.samples.iTeaching.service.PaypalService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
@Controller
public class PaypalController {

	@Autowired
	PaypalService paypalService;
	
	@Autowired
	AlumnoService alumnoService;
	
	@Autowired
	AnuncioService anuncioService;
	
	public static final String SUCCESS_URL = "/pay/success";
	public static final String CANCEL_URL = "/pay/cancel";



	@PostMapping("/pagar")
	public String payment(@ModelAttribute("order") Orden order) {
		System.out.println(order.toString());
		try {
			Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(), "http://localhost:8080" + CANCEL_URL,
					"http://localhost:8080" + SUCCESS_URL);
			for(Links link:payment.getLinks()) {
				if(link.getRel().equals("approval_url")) {
					return "redirect:"+link.getHref();
				}
			}
			
		} catch (PayPalRESTException e) {
		
			e.printStackTrace();
		}
		return "redirect:/";
	}
	
	 @GetMapping(value = CANCEL_URL)
	    public String cancelPay() {
	        return "cancel";
	    }

	    @GetMapping(value = SUCCESS_URL)
	    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
	        try {
	            Payment payment = paypalService.executePayment(paymentId, payerId);
	            System.out.println(payment.toJSON());
	            if (payment.getState().equals("approved")) {
	                return "success";
	            }
	        } catch (PayPalRESTException e) {
	         System.out.println(e.getMessage());
	        }
	        return "redirect:/";
	    }
	    
	    @GetMapping(value="/pagar")
	    public String pagar(@ModelAttribute("order") Orden order,Map<String, Object> model) {
	    	model.put("order",order);
	    	return "/pay/pay";
	    	
	    }
	    @GetMapping(value="/pagarMisClases")
	    public String pagarMisClases(@ModelAttribute("order") Orden order,Map<String, Object> model) {
	    	UserDetails clienteDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();
			String username= clienteDetails.getUsername();
			List<Anuncio> anunciosAplicados=anuncioService.anunciosAlumno(username);
			Float f = (float) anunciosAplicados.stream().map(x->x.getPrecio()).mapToDouble(Double::doubleValue).sum();
			model.put("total",f);
			model.put("anuncio", anunciosAplicados);
			order.setPrice(f);
	    	model.put("order",order);
	    	return "/pay/pay";
	    	
	    }

}
