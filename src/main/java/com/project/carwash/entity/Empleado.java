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
@Table (name="tbempleado")
public class Empleado {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "codempleado")
	private Integer codigo;
	@Column (name = "nomempleado")
	private String nombre;
	@Column (name = "apeempleado")
	private String apellido;
	@Column (name = "cargoempleado")
	private String cargo;
	@Column (name = "teleempleado")
	private Integer telefono;
	@Column (name = "correoempleado")
	private String correo;
	
	@ManyToOne
	@JoinColumn(name = "codsede")
	private Sede sede;

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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
	
}
