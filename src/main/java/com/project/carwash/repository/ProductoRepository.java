package com.project.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.carwash.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer>{

}
