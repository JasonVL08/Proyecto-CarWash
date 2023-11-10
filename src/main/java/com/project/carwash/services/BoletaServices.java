package com.project.carwash.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Boleta;
import com.project.carwash.repository.BoletaRepository;

@Service
public class BoletaServices {
	@Autowired
	private BoletaRepository repo;
	
	public List<Boleta> buscarBoletasXRangoDeFecha(LocalDate inicio, LocalDate fin, Integer codSede) {
		return repo.buscarBoletasXRangoDeFecha(inicio, fin, codSede);
	}
}
