package com.project.carwash.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "tb_reserva")
public class Reserva {
	@Id
	private Integer numero;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private LocalDateTime fecha;
	
	@Column(name = "cod_usu", nullable = false)
	private Integer codUsuario;
	
	@Column(nullable = false)
	private String estado;
	
	private Double monto;
	
	@ManyToOne
	@JoinColumn(name = "cod_usu", insertable = false, updatable = false)
	private Usuario usuario;
	
	@JsonIgnore
	@OneToMany(mappedBy = "reserva")
	private List<DetalleReserva> detalleReservas;

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

	public Integer getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(Integer codUsuario) {
		this.codUsuario = codUsuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<DetalleReserva> getDetalleReservas() {
		return detalleReservas;
	}

	public void setDetalleReservas(List<DetalleReserva> detalleReservas) {
		this.detalleReservas = detalleReservas;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	

}
