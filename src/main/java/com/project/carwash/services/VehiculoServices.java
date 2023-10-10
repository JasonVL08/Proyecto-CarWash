package com.project.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Vehiculo;
import com.project.carwash.repository.VehiculoRepository;

@Service
public class VehiculoServices {
	@Autowired
	private VehiculoRepository repo;
	
	public void insert(Vehiculo vehiculo) {
		repo.save(vehiculo);
	}
	
	public void update(Vehiculo vehiculo) {
		repo.save(vehiculo);
	}

	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public Vehiculo findById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<Vehiculo> findAll() {
		return repo.findAll();
	}
}

