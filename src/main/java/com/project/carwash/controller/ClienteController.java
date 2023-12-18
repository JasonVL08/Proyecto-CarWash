package com.project.carwash.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.carwash.entity.Cliente;
import com.project.carwash.services.ClienteServices;
import com.project.carwash.services.UsuarioServices;
//ATRIBUTOS DE TIPO SESSION
@SessionAttributes("ENLACES")
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
			RedirectAttributes redirect)
	{
		try {
			Cliente c = new Cliente();
			c.setNombre(nombre);
			c.setApellido(apellidos);
			c.setTelefono(telefono);
			c.setCorreo(correo);
			c.setDireccion(direccion);
			
			List<Cliente> cli= clienteService.listarTodos();
			
			if(codigo != 0) {
				for(Cliente clien:cli ) {
					if(clien.getId() != codigo) {
						if(clien.getTelefono() == telefono) {
							redirect.addFlashAttribute("MENSAJE","Telefono YA EXISTE");
							return "redirect:/cliente/lista";
						} 
						if(clien.getCorreo() == correo) {
							redirect.addFlashAttribute("MENSAJE","CORREO YA EXISTE");
							return "redirect:/cliente/lista";
						}
						
					} 
					c.setId(codigo);
					clienteService.update(c);
					redirect.addFlashAttribute("MENSAJE","CLIENTE ACTUALIZADO");	
				}
				//Cliente cod= clienteService.findById(codigo);
				
			}else {
				if(clienteService.findByTelefono(telefono) != null ) {
					redirect.addFlashAttribute("MENSAJE","TELEFONO YA EXISTE");
					return "redirect:/cliente/lista";
				}
				if(clienteService.findByCorreo(correo) != null) {
					redirect.addFlashAttribute("MENSAJE","CORREO YA EXISTE");
					return "redirect:/cliente/lista";
				}
				
					clienteService.insert(c);
					redirect.addFlashAttribute("MENSAJE","CLIENTE REGISTRADO");
				
			}
			
			
			
			
			/*if(cli != null && cli.getTelefono() != telefono) {
				redirect.addFlashAttribute("MENSAJE","TELEFONO YA EXISTE");
				return "redirect:/cliente/lista";
			}
			if(clienteService.findByCorreo(correo) !=null) {
				redirect.addFlashAttribute("MENSAJE","CORREO YA EXISTE");
				return "redirect:/cliente/lista";
			}
			if(codigo == 0) {
				clienteService.insert(c);
				redirect.addFlashAttribute("MENSAJE","CLIENTE REGISTRADO");
			} else {
				c.setId(codigo);
				clienteService.update(c);
				redirect.addFlashAttribute("MENSAJE","CLIENTE ACTUALIZADO");
			}*/ 
			
			
		}
		catch (Exception e) {
			redirect.addFlashAttribute("ERROR MENSAJE","ERROR AL REGISTRAR O ACTUALIZAR CLIENTE");
			System.out.println(e.getMessage());
		}
		
		return "redirect:/cliente/lista";
	}
	
	@GetMapping("/buscar")
	@ResponseBody
	public Cliente buscar(@RequestParam("id") int codigo) {
		return clienteService.findById(codigo);
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo") Integer codigo,
			RedirectAttributes redirect) {
		clienteService.deleteById(codigo);
		redirect.addFlashAttribute("MENSAJE" , "Cliente eliminado");
		return "redirect:/cliente/lista";
	}
	
	
}

