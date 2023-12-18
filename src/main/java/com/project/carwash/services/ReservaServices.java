package com.project.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Reserva;
import com.project.carwash.repository.ReservaRepository;

@Service
public class ReservaServices {
	@Autowired
	private ReservaRepository reservaRepository;
	
	public void save(Reserva reserva) {
		reservaRepository.save(reserva);
	}
	
	public void update(Reserva reserva) {
		reservaRepository.save(reserva);
	}
	
	public void deleteById(Integer id) {
		reservaRepository.deleteById(id);
	}
	
	public List<Reserva> findAll() {
		return reservaRepository.findAll();
	}
	
	public Reserva findById(int id) {
		return reservaRepository.findById(id).orElse(null);
	}
}
