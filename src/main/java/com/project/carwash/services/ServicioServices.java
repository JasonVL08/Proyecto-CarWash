package com.project.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Servicio;
import com.project.carwash.repository.ServicioRepository;

@Service
public class ServicioServices {
	@Autowired
	private ServicioRepository repo;
	public List<Servicio> listarTodo(){
		return repo.findAll();
	}
	public void Registrar(Servicio serv) {
		repo.save(serv);
	}
	public void Actualizar (Servicio serv) {
		repo.save(serv);
	}
	public Servicio Buscar(int cod) {
		return repo.findById(cod).orElse(null);
	}
	public void Eliminar(Servicio ser) {
		repo.delete(ser);
	}
	

}