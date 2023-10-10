package com.project.carwash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tbcategoria")
public class Categoria {
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	@Column(name="codcategoria")
	private Integer codigo;

	@Column(name="nombrecategoria")
	private String categoria;
	
	@Column(name="preciocategoria")
	private double precio;
	public Categoria() {
		
	}

	public Categoria(String categoria, double precio) {
		super();
		this.categoria = categoria;
		this.precio = precio;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
