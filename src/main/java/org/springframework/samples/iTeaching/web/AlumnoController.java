/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.iTeaching.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.iTeaching.model.Alumno;
import org.springframework.samples.iTeaching.service.AlumnoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class AlumnoController {

	private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "alumnos/createOrOwnerAlumnoForm";

	@Autowired
	private AlumnoService alumnoService;

	
	

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/alumnos/new")
	public String initCreationForm(Map<String, Object> model) {
		Alumno alumno = new Alumno();
		model.put("alumno", alumno);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/alumnos/new")
	public String processCreationForm(@Valid Alumno alumno, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			//creating alumno, user and authorities
			this.alumnoService.saveAlumno(alumno);
			
			return "redirect:/alumnos/" + alumno.getId();
		}
	}

	@GetMapping(value = "/alumnos/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("alumno", new Alumno());
		return "alumnos/findOwners";
	}

//	@GetMapping(value = "/alumnos")
//	public String processFindForm(Alumno alumno, BindingResult result, Map<String, Object> model) {
//
//		// allow parameterless GET request for /alumnos to return all records
//		if (alumno.getLastName() == null) {
//			alumno.setLastName(""); // empty string signifies broadest possible search
//		}
//
//		// find alumnos by last name
//		Collection<Alumno> results = this.alumnoService.findOwnerByLastName(alumno.getLastName());
//		if (results.isEmpty()) {
//			// no alumnos found
//			result.rejectValue("lastName", "notFound", "not found");
//			return "alumnos/findOwners";
//		}
//		else if (results.size() == 1) {
//			// 1 alumno found
//			alumno = results.iterator().next();
//			return "redirect:/alumnos/" + alumno.getId();
//		}
//		else {
//			// multiple alumnos found
//			model.put("selections", results);
//			return "alumnos/alumnosList";
//		}
//	}

	@GetMapping(value = "/alumnos/{alumnoId}/edit")
	public String initUpdateOwnerForm(@PathVariable("alumnoId") int alumnoId, Model model) {
		Alumno alumno = this.alumnoService.findAlumnoById(alumnoId);
		model.addAttribute(alumno);
		return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/alumnos/{alumnoId}/edit")
	public String processUpdateOwnerForm(@Valid Alumno alumno, BindingResult result,
			@PathVariable("alumnoId") int alumnoId) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
		}
		else {
			alumno.setId(alumnoId);
			this.alumnoService.saveAlumno(alumno);
			return "redirect:/alumnos/{alumnoId}";
		}
	}

	/**
	 * Custom handler for displaying an alumno.
	 * @param alumnoId the ID of the alumno to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping("/alumnos/{alumnoId}")
	public ModelAndView showOwner(@PathVariable("alumnoId") int alumnoId) {
		ModelAndView mav = new ModelAndView("alumnos/alumnoDetails");
		Alumno alumno=this.alumnoService.findAlumnoById(alumnoId);
		mav.addObject("alumno",alumno);
		return mav;
	}

}
