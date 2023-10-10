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
@Table(name = "tbcliente")
public class Cliente {
	@Id
	@Column(name = "idcliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nomcliente" )
	private String nombre;
	
	@Column(name = "apecliente")
	private String apellido;
	
	@Column(name = "telecliente")
	private String telefono;
	
	@Column(name = "correocliente") 
	private String correo;
	
	@Column(name = "direccioncliente") 
	private String direccion;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Vehiculo> listaVehiculos;
	
	public Cliente() {}
	
	public Cliente(Integer id) {
		this(id, null, null, null, null, null);
	}

	public Cliente(Integer id, String apellido, String nombre, String telefono, String correo, String direccion) {
		this.id = id;
		this.apellido = apellido;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.direccion = direccion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getNombreCompleto() {
		return nombre + " " + apellido;
	}
	
}
