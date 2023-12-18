package com.project.carwash.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tb_detalle_carrito")
public class ProductoCarrito {
	@EmbeddedId
	private ProductoCarritoPK pk;
	
	@ManyToOne
	@JoinColumn(name = "cod_producto",insertable = false,updatable = false,referencedColumnName ="cod_producto")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "cod_carrito",insertable = false,updatable = false,referencedColumnName ="cod_carrito")
	private Carrito carrito;

	@Column(name="cantidad")
	private int cantidad;

	

	public ProductoCarritoPK getPk() {
		return pk;
	}

	public void setPk(ProductoCarritoPK pk) {
		this.pk = pk;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
