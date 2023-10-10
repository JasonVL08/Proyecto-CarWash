package com.project.carwash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.carwash.entity.Categoria;
import com.project.carwash.services.CategoriaServices;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaServices cate;
	
	@RequestMapping("/lista")
	public String listar(Model model) {
		model.addAttribute("categoria", cate.listarTodo());
		return "Categoria";
	}
	@RequestMapping("/grabar")
	public String grabar(
			@RequestParam("codigo") Integer cod,
			@RequestParam("categoria") String nom,
			@RequestParam("precio") double pre,
			RedirectAttributes redirect
			) {
		try {
			Categoria objetoCate = new Categoria();
			objetoCate.setCategoria(nom);
			objetoCate.setPrecio(pre);
			if(cod==0) {
				
				cate.Registrar(objetoCate);
				redirect.addFlashAttribute("MENSAJE", "Categoria registrada");
			}else {
				objetoCate.setCodigo(cod);
				cate.Actualizar(objetoCate);
				redirect.addFlashAttribute("MENSAJE", "Categoria actualizada");
			}
		}catch (Exception e) {
			// TODO: handle exception
		}		
	return "redirect:/categoria/lista";
}
	@RequestMapping("/buscar")
	@ResponseBody
	public Categoria Buscar(@RequestParam("codigo") Integer cod) {
		return cate.Buscar(cod);
	}
	
}

