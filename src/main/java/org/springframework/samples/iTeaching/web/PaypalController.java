package org.springframework.samples.iTeaching.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Orden;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.PaypalService;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
@Controller
public class PaypalController {

	@Autowired
	PaypalService paypalService;

	@Autowired
    UserService userService;

	@Autowired
    AsignaturaService asignaturaService;

	
	@Autowired
    AlumnoService alumnoService;

	public static final String SUCCESS_URL = "/pay/success";
	public static final String CANCEL_URL = "/pay/cancel";


	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") Orden order) {
		try {
			Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(), "https://iteaching-production-sprint3.herokuapp.com/" + CANCEL_URL,
					"https://iteaching-production-sprint3.herokuapp.com/" + SUCCESS_URL);
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
	    public String pagar(Model model) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();
            Alumno alumno = this.alumnoService.findAlumnoByUsername(user.getUsername());
            List<Asignatura> asignaturas= this.asignaturaService.appliedAnuncio(alumno);
            Double precio = asignaturas.stream().mapToDouble(a->a.getPrecio()).sum();
            model.addAttribute("precio", precio);
            Orden order = new Orden();
            order.setPrice(precio);
			List<String>tipoPago=new ArrayList<>();
			tipoPago.add("paypal");
			tipoPago.add("visa");
			model.addAttribute("tipos",tipoPago);
			model.addAttribute("orden",order);
            return "/pay/pay";
		}
}
