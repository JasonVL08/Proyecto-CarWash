package com.project.carwash.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="tb_producto")
public class Producto {
	@Id
	@Column(name="cod_producto")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(name="nom_producto")
	private String nombre;
	
	@Column(name="foto_producto")
	private String foto;
	
	@Column(name="pre_producto")
	private double precio;
	
	@ManyToOne
	@JoinColumn(name="cod_tipo_producto")
	private TipoProducto tipoProducto;

	@JsonIgnore
	@OneToMany(mappedBy = "producto")
	private List<ProductoCarrito> detalle;
	
	
	
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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	
	public Producto(Integer codigo){
		this.codigo=codigo;
	}
	public Producto() {
		
	}
	
	
}
