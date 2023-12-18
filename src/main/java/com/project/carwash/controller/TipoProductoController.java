package com.project.carwash.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.carwash.entity.Producto;
import com.project.carwash.entity.TipoProducto;
import com.project.carwash.services.ProductoServices;
import com.project.carwash.services.TipoProductoServices;


@Controller
@RequestMapping("/tipoProducto")
public class TipoProductoController {

	@Autowired
	private TipoProductoServices tipo;
	
	@RequestMapping("/lista")
	public String index(Model model) {
		model.addAttribute("tipo", tipo.listaTipo());
		return "tipoProducto";
	}
	@RequestMapping("/grabar")
	public String grabar(
	        @RequestParam("codigo") Integer cod,
	        @RequestParam("nombre") String nom,
	        @RequestParam("foto") MultipartFile fotoFile,
	        RedirectAttributes redirect
	) {
	    try {
	        TipoProducto producto = new TipoProducto();
	        producto.setNombre(nom);

	        if (!fotoFile.isEmpty()) {
	            Path directorioImage = Paths.get("src/main/resources/static/img");
	            String rutaAbsoluta = directorioImage.toFile().getAbsolutePath();
	            try {
	                byte[] bytesImg = fotoFile.getBytes();
	                Path rutaCompleta = Paths.get(rutaAbsoluta + "/" + fotoFile.getOriginalFilename());
	                Files.write(rutaCompleta, bytesImg);

	                producto.setFoto(fotoFile.getOriginalFilename());
	            } catch (IOException e) {
	                e.printStackTrace();
	                redirect.addFlashAttribute("MENSAJE", "Error al guardar la imagen: " + e.getMessage());
	                return "redirect:/tipoProducto/lista";
	            }
	        }
	        if(cod==0) {
				tipo.grabar(producto);
				redirect.addFlashAttribute("MENSAJE","Categoria registrado");
                return "redirect:/tipoProducto/lista";

			}
	        else {
	            producto.setCodigo(cod);
	            TipoProducto productoActualizar = tipo.buscarPorId(cod);
	            for (TipoProducto productoComparacion : tipo.listaTipo()) {
	        	    if (productoActualizar.getFoto().equals(fotoFile.getOriginalFilename())) {
	        	    	String rutaAbsoluta = "src/main/resources/static/img/" + productoActualizar.getFoto();
	     	            Path rutaCompleta = Paths.get(rutaAbsoluta);
	     	            Files.delete(rutaCompleta);
	        	    }   
	            }
	            tipo.actualizar(producto);
	            redirect.addFlashAttribute("MENSAJE", "Categoria actualizada");
	            return "redirect:/tipoProducto/lista";
	        	}
	        } catch (Exception e) {
				e.printStackTrace();
			}	
			return "redirect:/tipoProducto/lista";
		}

	@RequestMapping("/buscar")
	@ResponseBody
	public TipoProducto Buscar(@RequestParam("codigo") Integer cod) {
		return tipo.buscarPorId(cod);
	}
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo") Integer cod, RedirectAttributes redirect) {
	    try {
	        // Obtener el producto antes de eliminarlo
	        TipoProducto tipoProducto  = tipo.buscarPorId(cod);

	        // Eliminar el producto
	        tipo.eliminar(cod);

	        // Eliminar la foto asociada al producto
	        if (tipoProducto != null && tipoProducto.getFoto() != null && !tipoProducto.getFoto().isEmpty()) {
	            String rutaAbsoluta = "src/main/resources/static/img/" + tipoProducto.getFoto();
	            Path rutaCompleta = Paths.get(rutaAbsoluta);
	            Files.delete(rutaCompleta);
	        }
	        redirect.addFlashAttribute("MENSAJE", "Categoria eliminada");
	    } catch (IOException e) {
	        e.printStackTrace(); 
	        redirect.addFlashAttribute("MENSAJE", "Error al eliminar la categoria: " + e.getMessage());
	    }
	    return "redirect:/productoCrud/lista";
	}
}