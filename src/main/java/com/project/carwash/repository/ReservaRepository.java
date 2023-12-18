package com.project.carwash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.carwash.entity.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
	Reserva findTopByOrderByNumeroDesc();
	
	List<Reserva> findByCodUsuario(int codUsuario);
}
