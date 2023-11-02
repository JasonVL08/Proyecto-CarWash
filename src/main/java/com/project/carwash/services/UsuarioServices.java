package com.project.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Enlace;
import com.project.carwash.entity.Usuario;
import com.project.carwash.repository.UsuarioRepository;

@Service
public class UsuarioServices {
	@Autowired
	private UsuarioRepository repo;
	
	public Usuario sesionUsuario(String vLogin) {
		return repo.iniciarSesion(vLogin);
	}
	public List<Enlace> enlacesDelUsuario(String desRol){
		return repo.traerEnlacesDelUsuario(desRol);
	}
	public void grabar(Usuario usuario) {
		repo.save(usuario);
	}
	public void actualizar(Usuario usuario) {
		repo.save(usuario);
	}
	public List<Usuario> listarTodo(){
		return repo.findAll();
	}	
	public void eliminar(Integer cod) {
		repo.deleteById(cod);
	}
}