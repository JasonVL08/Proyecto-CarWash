package com.project.carwash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbvehiculo")
public class Vehiculo {
	@Id
	@Column(name = "codigo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(name = "placavehiculo")
	private String placa;
	
	@Column(name = "marcavehiculo")
	private String marca;
	
	@Column(name = "modelovehiculo")
	private String modelo;
	
	@Column(name = "colorvehiculo")
	private String color;
	
	@ManyToOne
	@JoinColumn(name = "idcliente")
	private Cliente cliente;
	

	public Vehiculo() {}
	
	public Vehiculo(Integer codigo) {
		this(codigo, null, null, null, null, null);
	}
	
	public Vehiculo(Integer codigo, String placa, String marca, String modelo, String color, Cliente cliente) {
		this.codigo = codigo;
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
		this.cliente = cliente;
	}
	

	public Integer getCodigo() {
		return codigo;
	}


	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}


	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}


