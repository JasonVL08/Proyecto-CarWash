package com.project.carwash.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="tb_carrito")
public class Carrito {
	@Id
	@Column(name="cod_carrito")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(name="fec_carrito")
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name="cod_usu")
	private Usuario usuario;
	
	@JsonIgnore
	@OneToMany(mappedBy = "carrito")
	private List<ProductoCarrito> listaCarrito;

	public List<ProductoCarrito> getListaCarrito() {
		return listaCarrito;
	}

	public void setListaCarrito(List<ProductoCarrito> listaCarrito) {
		this.listaCarrito = listaCarrito;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
