package com.project.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Categoria;
import com.project.carwash.repository.CategoriaRepository;

@Service
public class CategoriaServices {

	@Autowired
	private CategoriaRepository repo;
	public List<Categoria> listarTodo(){
		return repo.findAll();
	}
	public void Registrar(Categoria tipo) {
		repo.save(tipo);
	}
	public void Actualizar(Categoria tipo) {
		repo.save(tipo);
	}
	public Categoria Buscar (int cod) {
		return repo.findById(cod).orElse(null);
	}
	public void Eliminar(Categoria tipo) {
		repo.delete(tipo);
	}
}


