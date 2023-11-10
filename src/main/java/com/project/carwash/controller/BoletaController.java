package com.project.carwash.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.carwash.services.BoletaServices;
import com.project.carwash.services.SedeServices;

@Controller
@RequestMapping("/boleta")
public class BoletaController{
	@RequestMapping("/form")
	public String index (Model model) { //model interface{
		return "boleta";
	}
	
	@Autowired
	private SedeServices sedeServices;
		
	@Autowired
	private BoletaServices boletaServices;
	
	@GetMapping("/boletasXRangoFechas")
	public String buscarBoletasXRangoFechas(Model model) {
		model.addAttribute("sedes", sedeServices.listarSede());
		return "boletaXrango";
	}
	
	@GetMapping("/boletasXRangoFechass")
	public String buscarBoletasXRangoFechass(
			@RequestParam("daterange") String fechas,
			@RequestParam("tipo") int codSede,
			Model model
			) 
	{
		LocalDate inicio = LocalDate.parse(fechas.substring(0, 10));
		LocalDate fin = LocalDate.parse(fechas.substring(13));
		model.addAttribute("sedes", sedeServices.listarSede());
		model.addAttribute("boletas", boletaServices.buscarBoletasXRangoDeFecha(inicio, fin, codSede));
		
		return "boletaXrango";
	}
}
