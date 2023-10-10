package com.project.carwash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boleta")
public class BoletaController{
	@RequestMapping("/form")
	public String index (Model model) { //model interface{
		return "boletaXrango";
	}
}
