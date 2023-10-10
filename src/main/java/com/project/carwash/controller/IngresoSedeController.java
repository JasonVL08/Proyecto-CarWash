package com.project.carwash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ingresoSede")
public class IngresoSedeController{
	@RequestMapping("/form")
	public String index (Model model) { //model interface{
		return "ingresoXsede";
	}
}
