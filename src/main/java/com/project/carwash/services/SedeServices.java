package com.project.carwash.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Sede;
import com.project.carwash.repository.SedeRepository;

@Service
public class SedeServices {
@Autowired
	private SedeRepository repoSede;
	
	public List<Sede> listarSede(){
		return repoSede.findAll();
	}
}
