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
import com.project.carwash.services.ClienteServices;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteServices clienteService;
	
	@GetMapping("/lista")
	public String listado(Model model) {
		model.addAttribute("clientes", clienteService.listarTodos());
		return "cliente";
	}
	
	@PostMapping("/grabar")
	public String grabar
	(
			@RequestParam("codigo") int codigo,
			@RequestParam("nombre") String nombre,
			@RequestParam("apellidos") String apellidos,
			@RequestParam("telefono") String telefono,
			@RequestParam("correo") String correo,
			@RequestParam("direccion") String direccion,
			RedirectAttributes redirect
	)
	{
		try {
			Cliente cliente = new Cliente(codigo, apellidos, nombre, telefono, correo, direccion);
			String complemento;
			
			if (codigo == 0) {
				clienteService.insert(cliente);
				complemento = "Registrado";	
			} else {
				clienteService.update(cliente);
				complemento = "Actualizado";
			}
			
			redirect.addFlashAttribute("MENSAJE", "Cliente " + complemento);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return "redirect:/cliente/lista";
	}
	
	@GetMapping("/buscar")
	@ResponseBody
	public Cliente buscar(@RequestParam("id") int codigo) {
		return clienteService.findById(codigo);
	}
}

