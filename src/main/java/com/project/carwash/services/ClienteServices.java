package com.project.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Cliente;
import com.project.carwash.entity.Enlace;
import com.project.carwash.repository.ClienteRepository;

@Service
public class ClienteServices {
	@Autowired
	private ClienteRepository repo;
	
	public Cliente insert(Cliente cliente) {
		return repo.save(cliente);
		
	}
	
	public void update(Cliente cliente) {
		repo.save(cliente);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public Cliente findById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<Cliente> listarTodos() {
		return  repo.findAll();
	}
	public Cliente findByTelefono(String telefono) {
		return repo.findByTelefono(telefono);
	}
	public Cliente findByCorreo(String correo) {
		return repo.findByCorreo(correo);
	}

	
	
}
