package com.project.carwash.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_detalle_reserva")
public class DetalleReserva {
	@EmbeddedId
	private DetalleReservaPK id;

	@ManyToOne
	@JoinColumn(name = "numero_reserva", insertable = false, updatable = false, referencedColumnName = "numero")
	private Reserva reserva;
	
	@ManyToOne
	@JoinColumn(name = "cod_servicio", insertable = false, updatable = false, referencedColumnName = "codservicio")
	private Servicio servicio;
	
	public DetalleReservaPK getId() {
		return id;
	}

	public void setId(DetalleReservaPK id) {
		this.id = id;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}
	
	
}
