package com.project.carwash.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tbboleta")
public class Boleta {
	@Id
	@Column(name = "NumBoleta")
	private Integer numero;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FechaBoleta")
	private LocalDateTime fecha;
	
	@ManyToOne
	@JoinColumn(name = "IdCliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "CodEmpleado")
	private Empleado empleado;
	
	@ManyToOne
	@JoinColumn(name = "id_sede")
	private Sede sede;

	@Override
	public String toString() {
		return "Boleta [numero=" + numero + ", fecha=" + fecha + ", cliente=" + cliente + ", empleado=" + empleado
				+ "]";
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
	
}
