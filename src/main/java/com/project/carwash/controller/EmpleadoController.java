package com.project.carwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.carwash.entity.Empleado;
import com.project.carwash.entity.Sede;
import com.project.carwash.services.EmpleadoServices;
import com.project.carwash.services.SedeServices;

@Controller
@RequestMapping ("/empleado")
public class EmpleadoController {
	@Autowired
	private EmpleadoServices servicioEmp;
	
	@Autowired
	private SedeServices servicioSede;
	
	@RequestMapping("/lista")
	public String index (Model model) { //model interface{
		model.addAttribute("empleados", servicioEmp.listarEmpleado());
		model.addAttribute("sedes", servicioSede.listarSede());
		return "empleado";
	}
	
	@RequestMapping("/grabar")
	public String grabar (@RequestParam("codigo") Integer cod,
						  @RequestParam("nombre") String nom,
			              @RequestParam("apellido") String ape,
			              @RequestParam("cargo") String cargo,
			              @RequestParam("telefono") int tele,
			              @RequestParam("correo") String correo,
			              @RequestParam("sede") int codSede,
			              RedirectAttributes redirect) {
		try {
			//
			Empleado emp = new Empleado();			
			emp.setNombre(nom);
			emp.setApellido(ape);
			emp.setCargo(cargo);
			emp.setTelefono(tele);
			emp.setCorreo(correo);
		
			//
			Sede sd = new Sede();
			sd.setCodigo(codSede);
			
			//
			emp.setSede(sd);
			
			if(cod == 0) {
				servicioEmp.registrar(emp);
				//flash
				redirect.addFlashAttribute("MENSAJE", "Empleado registrado");
			} 
			else {
				emp.setCodigo(cod);
				servicioEmp.actualizar(emp);
				redirect.addFlashAttribute("MENSAJE", "Empleado actualizado");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/empleado/lista";
	}
	
	@RequestMapping("/buscar")
	@ResponseBody
	public Empleado buscar (@RequestParam("codigo") Integer cod) {
		return servicioEmp.buscarPorId(cod);
	}
	
}