package com.project.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Producto;
import com.project.carwash.entity.TipoProducto;
import com.project.carwash.repository.TipoProductoRepository;

@Service
public class TipoProductoServices {
	@Autowired
	private TipoProductoRepository repo;
	
	public List<TipoProducto> listaTipo(){
		return repo.findAll();
	}
	public void grabar(TipoProducto tipo) {
		repo.save(tipo);
	}
	public void actualizar(TipoProducto tipo) {
		repo.save(tipo);
	}	
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}
	public TipoProducto buscarPorId(Integer id) {
		return repo.findById(id).orElse(null);
	}
}
