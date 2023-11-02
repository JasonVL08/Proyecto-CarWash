package com.project.carwash.security;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Usuario;
import com.project.carwash.services.UsuarioServices;

@Service
public class SecurityLogin implements UserDetailsService{
	
	//autentuca los usuarios
	@Autowired
	private UsuarioServices servicioUsu;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails bean=null;
		Usuario usu=servicioUsu.sesionUsuario(username);
		//rol del usario
		Set<GrantedAuthority> rol=new HashSet<GrantedAuthority>();
		rol.add(new SimpleGrantedAuthority(usu.getRol().getDescripcion()));
		//crear bean
		bean=new User(username, usu.getClave(), rol);
		return bean;
	}
}
