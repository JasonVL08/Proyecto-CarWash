package com.project.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Empleado;
import com.project.carwash.entity.Producto;
import com.project.carwash.repository.ProductoRepository;

@Service
public class ProductoServices {

	@Autowired
	private ProductoRepository repo;
	
	public List<Producto> listaProductos(){
		return repo.findAll();
	}
	
	public void grabar(Producto pro) {
		repo.save(pro);
	}
	public void actualizar (Producto pro) {
		repo.save(pro);
	}
	
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}
	public Producto buscarPorId(Integer cod) {
		return repo.findById(cod).orElse(null);
	}
}
