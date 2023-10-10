package com.project.carwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.carwash.entity.Servicio;
import com.project.carwash.services.ServicioServices;

@Controller
@RequestMapping("/servicio")
public class ServicioController {
	@Autowired
	private ServicioServices ser;

	
	@RequestMapping("/lista")
	public String listar(Model model) {
		model.addAttribute("servicio", ser.listarTodo());
		return "Servicio";
	}
	@RequestMapping("/grabar")
	public String grabar(
			@RequestParam("codigo") Integer cod,
			@RequestParam("nombre") String nom,
			@RequestParam("precio") double pre,
			RedirectAttributes redirect) {
		try {
			Servicio servicio = new Servicio();
			servicio.setNombre(nom);
			servicio.setPrecio(pre);
			
			if(cod==0) {
				ser.Registrar(servicio);
				redirect.addFlashAttribute("MENSAJE", "Servicio registrado");
			}else {
				servicio.setCodigo(cod);
				ser.Actualizar(servicio);
				redirect.addFlashAttribute("MENSAJE", "Servicio actualizado");

			}
		}catch (Exception e) {
			e.getMessage();
		}	
		return "redirect:/servicio/lista";
	}	
	@RequestMapping("/buscar")
	@ResponseBody
	public Servicio Buscar(@RequestParam("codigo") Integer cod) {
		return ser.Buscar(cod);
	}
	
}
