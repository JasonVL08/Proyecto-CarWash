package com.project.carwash.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.carwash.entity.Boleta;

public interface BoletaRepository extends JpaRepository<Boleta, Integer> {
	// @Query(value = "SELECT b.NumBoleta, b.FechaBoleta, c.NomCliente, c.ApeCliente, e.NomEmpleado, e.ApeEmpleado FROM tbboleta b JOIN tbcliente c ON b.IdCliente = c.IdCliente JOIN tbempleado e ON b.CodEmpleado = e.CodEmpleado WHERE DATE(b.FechaBoleta) BETWEEN ? AND ? ORDER BY b.FechaBoleta", nativeQuery = true)
	
	@Query(value = """
			select b from Boleta b where b.sede.codigo = :codSede AND DATE(b.fecha) BETWEEN :inicio AND :fin order by b.fecha
			""")
	public List<Boleta> buscarBoletasXRangoDeFecha(LocalDate inicio, LocalDate fin, Integer codSede);
	
	public List<Boleta> findByFechaBetween(LocalDate min, LocalDate max);
	
}
