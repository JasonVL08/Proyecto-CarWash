package com.project.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.carwash.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}