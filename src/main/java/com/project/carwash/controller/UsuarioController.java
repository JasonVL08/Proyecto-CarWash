package com.project.carwash.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.carwash.entity.Enlace;
import com.project.carwash.entity.Usuario;
import com.project.carwash.services.UsuarioServices;

//ATRIBUTOS DE TIPO SESSION
@SessionAttributes({"ENLACES","CODIGOUSUARIO","LOGIN"})
@Controller
@RequestMapping("/session")
public class UsuarioController {
	@Autowired
	private UsuarioServices servicioUsu;
	
	@RequestMapping("/proyecto")
	public String proyecto() {
		return "proyecto";
	}
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/pagina")
	public String intranet(Authentication auth,Model model) {
		//obtener nombre del rol del usuario que inicio sesiòn
		String nomRol=auth.getAuthorities().stream()
			      .map(GrantedAuthority::getAuthority)
			      .collect(Collectors.joining(","));
		//
		List<Enlace> lista=servicioUsu.enlacesDelUsuario(nomRol);
		Usuario u=servicioUsu.sesionUsuario(auth.getName());
		model.addAttribute("CODIGOUSUARIO",u.getCodigo());
		model.addAttribute("ENLACES",lista);
		model.addAttribute("LOGIN",u.getLogin());
		return "pagina";
	}
	@RequestMapping("/cuenta")
	public String cuenta() {
		return "cuenta";
	}
}
