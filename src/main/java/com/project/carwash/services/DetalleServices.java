package com.project.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.detalle;
import com.project.carwash.repository.DetalleRepository;


@Service
public class DetalleServices {
	@Autowired
	private DetalleRepository repo;
	
	public List<detalle> listarTodo(){
		return repo.findAll();
	}
	public void Registrar(detalle tipo) {
		repo.save(tipo);
	}
	public void Actualizar(detalle tipo) {
		repo.save(tipo);
	}
	public detalle Buscar (Integer cod) {
		return repo.findById(cod).orElse(null);
	}
	public void Eliminar(Integer cod) {
		repo.deleteById(cod);
	}
	private List<detalle> listaDetalle;

    // Constructor o método para establecer la lista de detalles
    public DetalleServices(List<detalle> listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    // Resto del código de tu servicio

    public void limpiarListaDetalles() {
        listaDetalle.clear();
    }
}
