package com.project.carwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.carwash.entity.Cliente;
import com.project.carwash.entity.Vehiculo;
import com.project.carwash.services.ClienteServices;
import com.project.carwash.services.VehiculoServices;

@Controller
@RequestMapping("/vehiculo")
public class VehiculoController {
	@Autowired
	private VehiculoServices vehiculoService;
	
	@Autowired
	private ClienteServices clienteService;
		
	@GetMapping("/lista")
	public String index(Model model) {
		model.addAttribute("vehiculos", vehiculoService.findAll());
		model.addAttribute("clientes", clienteService.listarTodos());
		return "vehiculo";
	}
	
	@PostMapping("/grabar")
	public String grabar(
		@RequestParam("codigo") Integer codigo,
		@RequestParam("placa") String placa,
		@RequestParam("marca") String marca,
		@RequestParam("modelo") String modelo,
		@RequestParam("color") String color,
		@RequestParam("cliente") int codigoCliente,
		RedirectAttributes redirect
	) 
	{
		try {
			Cliente cliente = new Cliente(codigoCliente);
			Vehiculo vehiculo = new Vehiculo(codigo, placa, marca, modelo, color, cliente);
			String complemento;
			
			if (codigo == 0) {
				vehiculoService.insert(vehiculo);
				complemento = "Registrado";
			} else {
				vehiculoService.update(vehiculo);
				complemento = "Actualizado";
			}
			redirect.addFlashAttribute("MENSAJE", complemento);
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return "redirect:/vehiculo/lista";
	}
	
	@GetMapping("/buscar")
	@ResponseBody
	public Vehiculo buscar(@RequestParam("codigo") Integer codigo) {
		return vehiculoService.findById(codigo);
	}
}

