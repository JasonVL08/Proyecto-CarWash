package com.project.carwash.entity;

import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductoCarritoPK {
	private int cod_carrito;
	private int cod_producto;
	@Override
	public int hashCode() {
		return Objects.hash(cod_carrito, cod_producto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoCarritoPK other = (ProductoCarritoPK) obj;
		return cod_carrito == other.cod_carrito && cod_producto == other.cod_producto;
	}
	public int getCod_carrito() {
		return cod_carrito;
	}
	public void setCod_carrito(int cod_carrito) {
		this.cod_carrito = cod_carrito;
	}
	public int getCod_producto() {
		return cod_producto;
	}
	public void setCod_producto(int cod_producto) {
		this.cod_producto = cod_producto;
	}
	
	
	
}
