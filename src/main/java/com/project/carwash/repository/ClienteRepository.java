package com.project.carwash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.carwash.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
