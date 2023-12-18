package com.project.carwash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.project.carwash.entity.Cliente;
import com.project.carwash.entity.Enlace;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	Cliente findByTelefono(String telefono);
	Cliente findByCorreo(String correo);
}
