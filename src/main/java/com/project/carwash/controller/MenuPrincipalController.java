package com.project.carwash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuPrincipalController {
	@RequestMapping("/index")
	public String menu() {
		return "index";
	}
}
