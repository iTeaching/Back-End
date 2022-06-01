package org.springframework.samples.iTeaching.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.model.Asignatura;
import org.springframework.samples.iTeaching.model.Clase;
import org.springframework.samples.iTeaching.model.Orden;
import org.springframework.samples.iTeaching.model.Profesor;
import org.springframework.samples.iTeaching.model.User;
import org.springframework.samples.iTeaching.model.estadoClase;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.samples.iTeaching.service.AsignaturaService;
import org.springframework.samples.iTeaching.service.ClaseService;
import org.springframework.samples.iTeaching.service.PaypalService;
import org.springframework.samples.iTeaching.service.ProfesorService;
import org.springframework.samples.iTeaching.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.bytebuddy.asm.Advice.Local;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import static java.time.temporal.ChronoUnit.MINUTES;

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

	@Autowired
    ClaseService claseService;

	@Autowired
    ProfesorService profesorService;

	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";


	@PostMapping("/pay")
	public String payment(@ModelAttribute("order") Orden order) {
		try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();	
			String authority= userDetails.getAuthorities().iterator().next().getAuthority();		
			if (authority.equals("profesor")){
				Profesor profesor=this.profesorService.findProfesorByUsername(user.getUsername());
				if(order.getDescription().contains("mensual")){
					profesor.setPremium("mensual");
					profesor.setPago(LocalDate.now());
					profesorService.saveProfesor(profesor);
				}
				else if(order.getDescription().contains("anual")){
					profesor.setPremium("anual");
					profesor.setPago(LocalDate.now());
					profesorService.saveProfesor(profesor);
				}
				else if(order.getDescription().contains("cuatrimestral")){
					profesor.setPremium("cuatrimestral");
					profesor.setPago(LocalDate.now());
					profesorService.saveProfesor(profesor);
				}
			}
			else if(authority.equals("alumno")){
				Alumno alumno=this.alumnoService.findAlumnoByUsername(user.getUsername());
				if(order.getDescription().contains("mensual")){
					alumno.setPremium("mensual");
					alumno.setPago(LocalDate.now());
					alumnoService.saveAlumno(alumno);
				}
				else if(order.getDescription().contains("anual")){
					alumno.setPremium("anual");
					alumno.setPago(LocalDate.now());
					alumnoService.saveAlumno(alumno);
				}
				else if(order.getDescription().contains("cuatrimestral")){
					alumno.setPremium("cuatrimestral");
					alumno.setPago(LocalDate.now());
					alumnoService.saveAlumno(alumno);
				}
			}
			Payment payment = paypalService.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
			order.getIntent(), order.getDescription(), "https://iteaching-a.herokuapp.com/" + CANCEL_URL,  
			"https://iteaching-a.herokuapp.com/" + SUCCESS_URL);
			
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
            List<Clase> asignaturas= this.claseService.findByAlumno(user.getUsername());
			
            Double precio = asignaturas.stream().filter(c->c.getEstadoClase().equals(estadoClase.finalizada)).
			mapToDouble(c->c.getAsignatura().getPrecio() * LocalTime.parse(c.getHoraComienzo()).until(LocalTime.parse(c.getHoraFin()), MINUTES)).sum();
			if(alumno.getPremium()=="mensual"||alumno.getPremium()=="anual"){
				precio = precio;
			}
			else{
			precio = precio *0.07 + precio;
			}
            model.addAttribute("precio", precio);
            Orden order = new Orden();
			order.setDescription("Clases particulares");
            order.setPrice(precio);
			List<String>tipoPago=new ArrayList<>();
			tipoPago.add("paypal");
			tipoPago.add("visa");
			model.addAttribute("tipos",tipoPago);
			model.addAttribute("orden",order);
            return "/pay/pay";
		}

		@GetMapping(value="/suscribirme")
	    public String suscripcion(Model model) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();
            Alumno alumno = this.alumnoService.findAlumnoByUsername(user.getUsername());
			alumno.setPremium("mensual");
			alumno.setPago(LocalDate.now());
            Double precio = 5.00;
            model.addAttribute("precio", precio);
            Orden order = new Orden();
			order.setDescription("Suscripción mensual");
            order.setPrice(precio);
			List<String>tipoPago=new ArrayList<>();
			tipoPago.add("paypal");
			tipoPago.add("visa");
			model.addAttribute("tipos",tipoPago);
			model.addAttribute("orden",order);
            return "/pay/pay";
		}

		@GetMapping(value="/suscribirmeAnual")
	    public String suscripcionAnual(Model model) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();
            Alumno alumno = this.alumnoService.findAlumnoByUsername(user.getUsername());
			alumno.setPremium("anual");
			alumno.setPago(LocalDate.now());
            Double precio = 40.00;
            model.addAttribute("precio", precio);
            Orden order = new Orden();
			order.setDescription("Suscripción anual");
            order.setPrice(precio);
			List<String>tipoPago=new ArrayList<>();
			tipoPago.add("paypal");
			tipoPago.add("visa");
			model.addAttribute("tipos",tipoPago);
			model.addAttribute("orden",order);
            return "/pay/pay";
		}

		@GetMapping(value="/suscribirmeCuatrimestral")
	    public String suscripcionCuatrimestral(Model model) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();
            Alumno alumno = this.alumnoService.findAlumnoByUsername(user.getUsername());
			alumno.setPremium("cuatrimestral");
			alumno.setPago(LocalDate.now());
            Double precio = 15.00;
            model.addAttribute("precio", precio);
            Orden order = new Orden();
			order.setDescription("Suscripción cuatrimestral");
            order.setPrice(precio);
			List<String>tipoPago=new ArrayList<>();
			tipoPago.add("paypal");
			tipoPago.add("visa");
			model.addAttribute("tipos",tipoPago);
			model.addAttribute("orden",order);
            return "/pay/pay";
		}

		@GetMapping(value="/promocionarme")
	    public String suscripcionProfesor(Model model) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();
            Profesor profesor = this.profesorService.findProfesorByUsername(user.getUsername());
			profesor.setPremium("mensual");
            Double precio = 5.00;
            model.addAttribute("precio", precio);
            Orden order = new Orden();
			order.setDescription("Suscripción mensual");
            order.setPrice(precio);
			List<String>tipoPago=new ArrayList<>();
			tipoPago.add("paypal");
			tipoPago.add("visa");
			model.addAttribute("tipos",tipoPago);
			model.addAttribute("orden",order);
            return "/pay/pay";
		}

		@GetMapping(value="/promocionarmeAnual")
	    public String suscripcionProfesorAnual(Model model) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();
            Profesor profesor = this.profesorService.findProfesorByUsername(user.getUsername());
			profesor.setPremium("anual");
            Double precio = 40.00;
            model.addAttribute("precio", precio);
            Orden order = new Orden();
			order.setDescription("Suscripción anual");
            order.setPrice(precio);
			List<String>tipoPago=new ArrayList<>();
			tipoPago.add("paypal");
			tipoPago.add("visa");
			model.addAttribute("tipos",tipoPago);
			model.addAttribute("orden",order);
            return "/pay/pay";
		}

		@GetMapping(value="/promocionarmeCuatrimestral")
	    public String suscripcionProfesorCuatrimestral(Model model) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = this.userService.findUser(userDetails.getUsername()).get();
            Profesor profesor = this.profesorService.findProfesorByUsername(user.getUsername());
			profesor.setPremium("cuatrimestral");
            Double precio = 15.00;
            model.addAttribute("precio", precio);
            Orden order = new Orden();
			order.setDescription("Suscripción cuatrimestral");
            order.setPrice(precio);
			List<String>tipoPago=new ArrayList<>();
			tipoPago.add("paypal");
			tipoPago.add("visa");
			model.addAttribute("tipos",tipoPago);
			model.addAttribute("orden",order);
            return "/pay/pay";
		}
}
