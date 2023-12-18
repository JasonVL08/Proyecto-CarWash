package com.project.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.carwash.entity.ProductoCarrito;

public interface CarritoProductoRepository extends JpaRepository<ProductoCarrito, Integer>{

}
