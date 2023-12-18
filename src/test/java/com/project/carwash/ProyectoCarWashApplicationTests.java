package com.project.carwash;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.carwash.entity.Boleta;
import com.project.carwash.entity.DetalleReserva;
import com.project.carwash.entity.DetalleReservaPK;
import com.project.carwash.entity.Reserva;
import com.project.carwash.repository.BoletaRepository;
import com.project.carwash.repository.ReservaRepository;
import com.project.carwash.services.ReservaService;

@SpringBootTest
class ProyectoCarWashApplicationTests {

	@Autowired
	private BCryptPasswordEncoder encriptar;
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	
	
	
	@Test
	void contextLoads() {
		/*System.out.println("CLAVE: " + encriptar.encode("123"));*/
		
		/*
		Reserva reserva = reservaRepository.findTopByOrderByNumeroDesc();
		System.out.println(reserva.getNumero());
		*/

		try {
			Reserva reserva = new Reserva();
			
			reserva.setNumero(1);
			reserva.setCodUsuario(1);
			reserva.setEstado("Habilitado");
			reserva.setFecha(LocalDateTime.parse("2023-11-19T13:07:00"));
			
			reservaRepository.save(reserva);
		} catch (Exception e) {
			System.out.println("Fallo");
			System.out.println(e);
		}
		
	}

}
