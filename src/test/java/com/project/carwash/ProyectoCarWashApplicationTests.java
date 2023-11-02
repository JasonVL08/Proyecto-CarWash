package com.project.carwash;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ProyectoCarWashApplicationTests {

	@Autowired
	private BCryptPasswordEncoder encriptar;
	
	@Test
	void contextLoads() {
		System.out.println("CLAVE: " + encriptar.encode("123"));	
	}

}
