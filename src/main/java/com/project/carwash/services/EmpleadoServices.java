package com.project.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Empleado;
import com.project.carwash.repository.EmpleadoRepository;

@Service
public class EmpleadoServices {
@Autowired
	private EmpleadoRepository repoEmpleado;
	
	public void registrar (Empleado emp) {
		repoEmpleado.save(emp);
	}
	public void actualizar (Empleado emp) {
		repoEmpleado.save(emp);
	}
	public void eliminarPorId (Integer cod) {
		repoEmpleado.deleteById(cod);
	}
	public Empleado buscarPorId(Integer cod) {
		return repoEmpleado.findById(cod).orElse(null);
	}
	public List<Empleado> listarEmpleado(){
		return repoEmpleado.findAll();
	}
}
