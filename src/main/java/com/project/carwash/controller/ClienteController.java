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
			List<Cliente> listaCliente = clienteService.listarTodos();
			Boolean telefonoRepetido = false;
			Boolean correoRepetido = false;
			for(int i=0; i<listaCliente.size();i++) {
				Cliente cli = listaCliente.get(i);
				String tel = cli.getTelefono();
				String cor = cli.getCorreo();
				if(tel.equals(telefono)) {
					telefonoRepetido=true;
				}
				if(cor.equals(correo)) {
					correoRepetido=true;
				}
			}
			if(telefonoRepetido==true || correoRepetido==true) {
				if(telefonoRepetido==true) {
					redirect.addFlashAttribute("ERROR", "Error en el registro. Telefono ya existe");
				}else if (correoRepetido==true) {
					redirect.addFlashAttribute("ERROR", "Error en el registro. Correo ya existe");
				}
			}
			
			else{
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
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		return "redirect:/cliente/lista";
	}
	
	@GetMapping("/buscar")
	@ResponseBody
	public Cliente buscar(@RequestParam("id") int codigo) {
		return clienteService.findById(codigo);
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo") Integer cod,
			RedirectAttributes redirect) {
		clienteService.deleteById(cod);
		redirect.addFlashAttribute("MENSAJE" , "Cliente eliminado");
		return "redirect:/cliente/lista";
	}
	
	
}

