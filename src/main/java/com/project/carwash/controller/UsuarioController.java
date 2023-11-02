package com.project.carwash.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.carwash.entity.Enlace;
import com.project.carwash.entity.Rol;
import com.project.carwash.entity.Usuario;
import com.project.carwash.services.UsuarioServices;

//ATRIBUTOS DE TIPO SESSION
@SessionAttributes("ENLACES")

@Controller
@RequestMapping("/session")
public class UsuarioController {
	
	@Autowired
	private UsuarioServices servicioUsu;
     
	
	/*@GetMapping("/registrarse")
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	@PostMapping("/grabar")
	public String registro(@RequestParam("codigo") Integer codigo, @RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido, @RequestParam("telefono") String telefono,
			@RequestParam("direccion") String direccion, @RequestParam("correo") String login,
			@RequestParam("contraseña") String password,Model model,
			RedirectAttributes redirect) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwordEncriptado = passwordEncoder.encode(password);

		Usuario usuario = new Usuario();
		usuario.setCodigo(codigo);
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setDireccion(direccion);
		usuario.setTelefono(telefono);
		usuario.setLogin(login);
		usuario.setClave(passwordEncriptado);

		Rol rol = new Rol();
		rol.setCodigo(2);
		usuario.setRol(rol);
	
			servicioUsu.grabar(usuario);		
		return "redirect:/session/registrarse?exito";
	}*/
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/home")
	public String home(){		
		return "home";
	}	
	@RequestMapping("/intranet")
	public String intranet(Authentication auth,Model model) {
		String nomRol=auth.getAuthorities().stream()
			      .map(GrantedAuthority::getAuthority)
			      .collect(Collectors.joining(","));
		
		List<Enlace> lista=servicioUsu.enlacesDelUsuario(nomRol);
		model.addAttribute("ENLACES", lista);
		return "categoria";
	}
	
	
}
