package com.project.carwash.controller;

import java.util.List;

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

import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;



@Controller
@RequestMapping("/productoCrud")
public class ProductoCrudController {
	@Autowired
	private ProductoServices pro;
	
	@Autowired
	private TipoProductoServices tipo;
	
	@RequestMapping("/lista")
	public String index(Model model) {
		model.addAttribute("producto", pro.listaProductos());
		model.addAttribute("tipoProducto", tipo.listaTipo());
		return "productoCrud";
	}
	@RequestMapping("/grabar")
	public String grabar(
	        @RequestParam("codigo") Integer cod,
	        @RequestParam("nombre") String nom,
	        @RequestParam("foto") MultipartFile fotoFile,
	        @RequestParam("precio") double pre,
	        @RequestParam("codTipo") int codTipo,
	        RedirectAttributes redirect
	) {
	    try {
	        Producto producto = new Producto();
	        producto.setNombre(nom);
	        System.out.println("Nombre: " + nom);

	        producto.setPrecio(pre);
	        System.out.println("Precio: " + pre);

	        if (!fotoFile.isEmpty()) {
	        	for (Producto productoComparacion : pro.listaProductos()) {
	        	    if (productoComparacion.getFoto().equals(fotoFile.getOriginalFilename())) {
	        	        redirect.addFlashAttribute("MENSAJE", "Foto ya ingresada, registre otra");
	        	        break;
	        	    }
	        	}
	        
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
	                return "redirect:/productoCrud/lista";
	            }
	        }

	        TipoProducto tipo = new TipoProducto();
	        tipo.setCodigo(codTipo);
	        producto.setTipoProducto(tipo);
	        System.out.println("Codigo Tipo: " + codTipo);
	        
	        if(cod==0) {
				pro.grabar(producto);
				redirect.addFlashAttribute("MENSAJE","Medicamento registrado");
                return "redirect:/productoCrud/lista";

			}
	        else {
	            producto.setCodigo(cod);
	            Producto productoActualizar = pro.buscarPorId(cod);
	            for (Producto productoComparacion : pro.listaProductos()) {
	        	    if (productoActualizar.getFoto().equals(fotoFile.getOriginalFilename())) {
	        	    	String rutaAbsoluta = "src/main/resources/static/img/" + productoActualizar.getFoto();
	     	            Path rutaCompleta = Paths.get(rutaAbsoluta);
	     	            Files.delete(rutaCompleta);
	        	    }   
	            }
	            pro.actualizar(producto);
	            redirect.addFlashAttribute("MENSAJE", "Producto actualizado");
	            return "redirect:/productoCrud/lista";
	        	}
	        } catch (Exception e) {
				e.printStackTrace();
			}	
			return "redirect:/productoCrud/lista";
		}

	@RequestMapping("/buscar")
	@ResponseBody
	public Producto Buscar(@RequestParam("codigo") Integer cod) {
		return pro.buscarPorId(cod);
	}
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo") Integer cod, RedirectAttributes redirect) {
	    try {
	        // Obtener el producto antes de eliminarlo
	        Producto producto = pro.buscarPorId(cod);

	        // Eliminar el producto
	        pro.eliminar(cod);

	        // Eliminar la foto asociada al producto
	        if (producto != null && producto.getFoto() != null && !producto.getFoto().isEmpty()) {
	            String rutaAbsoluta = "src/main/resources/static/img/" + producto.getFoto();
	            Path rutaCompleta = Paths.get(rutaAbsoluta);
	            Files.delete(rutaCompleta);
	        }

	        redirect.addFlashAttribute("MENSAJE", "Producto eliminado");
	    } catch (IOException e) {
	        e.printStackTrace(); // Maneja la excepción según tus requisitos
	        redirect.addFlashAttribute("MENSAJE", "Error al eliminar el producto: " + e.getMessage());
	    }

	    return "redirect:/productoCrud/lista";
	}
}