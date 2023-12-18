package com.project.carwash.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.carwash.entity.DetalleReserva;
import com.project.carwash.entity.DetalleReservaPK;
import com.project.carwash.entity.Producto;
import com.project.carwash.entity.Reserva;
import com.project.carwash.repository.DetalleReservaRepository;
import com.project.carwash.repository.ReservaRepository;



@Service
public class ReservaService {
	private final ReservaRepository reservaRepository;
	private final DetalleReservaRepository detalleReservaRepository;
	
	public ReservaService(ReservaRepository reservaRepository, 
			DetalleReservaRepository detalleReservaRepository) {
		
		this.reservaRepository = reservaRepository;
		this.detalleReservaRepository = detalleReservaRepository;
	}
	
	@Transactional
	public void grabar(Reserva reserva, ArrayList<DetalleReserva> detalles) {
		try {
			reservaRepository.save(reserva);
			
			
			for ( DetalleReserva det : detalles ) {
				detalleReservaRepository.save(det);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int getUltimoNumero() {
		return reservaRepository.findTopByOrderByNumeroDesc().getNumero();
	}
	
	public List<Reserva> listaProductos(int codi){
		return reservaRepository.findByCodUsuario(codi);
	}
	
}
