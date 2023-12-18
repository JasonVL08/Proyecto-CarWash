package com.project.carwash.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="tb_tipo_producto")
public class TipoProducto {
	@Id
	@Column(name="cod_tipo_producto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(name="nom_tipo_producto")
	private String nombre;
	
	@Column(name="foto_tipo_producto")
	private String foto;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "tipoProducto")
	private List<Producto> listaProductos;

	public Integer getCodigo() {
		return codigo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	
	
}
