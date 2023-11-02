package com.project.carwash.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	// metodo para encriptar claves
	@Bean
	public BCryptPasswordEncoder encriptarClave() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/session/**", "resources/**","login/**","/categoria/**").permitAll()
				.requestMatchers("/categoria/**", "/paciente/**").authenticated())
			.formLogin((form) -> form
				.loginPage("/session/login")
				.permitAll().defaultSuccessUrl("/session/intranet")
			).logout((logout) -> logout.permitAll());

		return http.build();
	}
	
	
    //metodo de la autenticaciòn
	// retorna un metodo userDetailsService
	// Y la clase SECURITYLOGIN lo recibe o lo implementa
	@Bean
	public UserDetailsService userDetailsService() {
		return new SecurityLogin();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider configure = new DaoAuthenticationProvider();
		configure.setUserDetailsService(userDetailsService());
		configure.setPasswordEncoder(encriptarClave());
		return configure;
	}
}
