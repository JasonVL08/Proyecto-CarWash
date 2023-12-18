package com.project.carwash.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DetalleReservaPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "numero_reserva")
	private Integer numReserva;
	
	@Column(name = "cod_servicio")
	private Integer codServicio;

	@Override
	public int hashCode() {
		return Objects.hash(numReserva, codServicio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetalleReservaPK other = (DetalleReservaPK) obj;
		return Objects.equals(numReserva, other.numReserva) && Objects.equals(codServicio, other.codServicio);
	}

	public Integer getIdReserva() {
		return numReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.numReserva = idReserva;
	}

	public Integer getIdServicio() {
		return codServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.codServicio = idServicio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}