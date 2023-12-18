package com.project.carwash.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tbservicio")
public class Servicio {
	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	@Column(name="codservicio")
	private Integer codigo;
	
	@Column (name="nomservicio")
	private String nombre;
	
	@Column(length = 255)
	private String descripcion;
	
	@Column (name="precioservicio")
	private double precio;
	
	@Column(length = 255)
	private String imagen;
	
	@JsonIgnore
	@OneToMany(mappedBy = "servicio")
	private List<DetalleReserva> detalleReservas;
	
	public Servicio() {
		
	}
	public Servicio(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public List<DetalleReserva> getDetalleReservas() {
		return detalleReservas;
	}
	
	public void setDetalleReservas(List<DetalleReserva> detalleReservas) {
		this.detalleReservas = detalleReservas;
	}	
	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
}