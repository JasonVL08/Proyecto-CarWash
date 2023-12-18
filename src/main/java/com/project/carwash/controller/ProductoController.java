package com.project.carwash.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.carwash.entity.Carrito;
import com.project.carwash.entity.Producto;
import com.project.carwash.entity.ProductoCarrito;
import com.project.carwash.entity.TipoProducto;
import com.project.carwash.entity.Usuario;
import com.project.carwash.entity.detalle;
import com.project.carwash.services.CarritoServices;
import com.project.carwash.services.DetalleServices;
import com.project.carwash.services.ProductoServices;
import com.project.carwash.services.TipoProductoServices;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	private ProductoServices pro;
	@Autowired
	private TipoProductoServices tipo;
	@Autowired
	private CarritoServices carro;
	
	@Autowired
	private DetalleServices deta;
	

	public List<Producto> productoFiltrado= new ArrayList<>();
	public TipoProducto tipoEntidad;
	
	@RequestMapping("/lista")
	public String index(Model model) {
		model.addAttribute("productos", pro.listaProductos());
		model.addAttribute("tipo", tipo.listaTipo());
		return "MenuProducto";
	}
	@RequestMapping("/cambiarMenu")
	public String MenuCategoria(@RequestParam("codigoEnlace") Integer cod) {
		tipoEntidad= tipo.buscarPorId(cod);
	    if (tipoEntidad != null) {
		        List<Producto> listaProductos = pro.listaProductos();
		        List<Producto> lizta = new ArrayList<>();

		        for (Producto producto : listaProductos) {
		            if (producto.getTipoProducto().getCodigo().equals(cod)) {
		            		lizta.add(producto);
		            }
		        }
		         if(productoFiltrado==null) {
		        	productoFiltrado.addAll(lizta);
		        }else {
		        	productoFiltrado.clear();
		        	productoFiltrado.addAll(lizta);
				}
		        return "redirect:/producto/tipoProducto";
	    	}
	     return "redirect:/producto/lista"; 
	  }
	@RequestMapping("/tipoProducto")
	public String NuevoListado(Model model) {
		model.addAttribute("tipo", tipo.listaTipo());
		model.addAttribute("productosFiltrados", productoFiltrado);
		return "CambiarMenu";
	}
	/*añadir al carrito*/
	@RequestMapping("/buscar")
	@ResponseBody
	public Producto buscar(@RequestParam("codigo") int cod,
	        @RequestParam("nombre") String nombre,
	        @RequestParam("cantidad") int cantidad,
	        @RequestParam("precio") double precio) {
	    detalle det = new detalle();

	    det.setNombre(nombre);
	    det.setCantidad(cantidad);
	    det.setPrecio(precio);
	    det.setPrecioTotal(precio*cantidad);
	    System.out.println("codigo : " + cod);

	    boolean detalleE = false;

	

	    if (deta.listarTodo() != null) {
	        for (detalle detalleExistente : deta.listarTodo()) {
	            if (detalleExistente.getCodigo() == cod) {
	                System.out.println("Producto duplicado, no se agrega a la lista");
	                detalleE = true;
	                break;
	            }
	        }
	    }
	    if (!detalleE) {
	        deta.Registrar(det);
	        System.out.println("Número de productos agregados a la lista: " + deta.listarTodo().size());
	    }
	    System.out.println("codigo : " + cod);
	    return pro.buscarPorId(cod);
	}

	
	@RequestMapping("/resumen")
	public String mostrarCarrito(Model model){
		List<detalle> listaDetalles = deta.listarTodo();

	    // Verifica si la lista de detalles está vacía y actualiza el modelo
	    if (listaDetalles.isEmpty()) {
	        model.addAttribute("mensaje", "El carrito está vacío");
	    } else {
	        model.addAttribute("detalle", listaDetalles);
	    }

	    return "resumen";
	}
	@RequestMapping("/modificarCantidad")
	@ResponseBody
	public String modificarCantidad( @RequestParam("codigo") Integer cod,
	        @RequestParam("nombre") String nom,
	        @RequestParam("precio") double precio,
	        @RequestParam("cantidad") int cantidad,
	        @RequestParam("precioTotal") double precioTotal,
	        RedirectAttributes redirect){
			
		detalle d = new detalle();
		d.setCodigo(cod);
		d.setNombre(nom);
		d.setPrecio(precio);
		d.setCantidad(cantidad);
		d.setPrecioTotal(precioTotal);
		
		deta.Actualizar(d);
		
		return "redirect:/producto/resumen";
	}
	
	
	
	
	
	
	/*fializar compra*/
	@RequestMapping("/grabar")
	public String grabar(HttpServletRequest request){
		try {
			Carrito bean=new Carrito();
			//setear
			//carrito tieme fecha, codusu
			LocalDate fecha= LocalDate.now();
			bean.setFecha(fecha);
	        System.out.println("Fecha " + fecha);
			Usuario usu=new Usuario();
			int cod=(int) request.getSession().getAttribute("CODIGOUSUARIO");
	        System.out.println("Codigo del usuario : " + cod);
			usu.setCodigo(cod);
			bean.setUsuario(usu);
			
			List<ProductoCarrito> listaProductoCarrito= new ArrayList<ProductoCarrito>();
			//bucle
			for(detalle det:deta.listarTodo()) {
				//crear objeto de la entidad BienRequerimiento
				ProductoCarrito br=new ProductoCarrito();
				br.setProducto(new Producto(det.getCodigo()));
				br.setCantidad(det.getCantidad());
				listaProductoCarrito.add(br);
			}
	        System.out.println("Número de productos agregados a la listaProductoCarrito : " + listaProductoCarrito.size());

			//enviar detalles dentor de bean
			bean.setListaCarrito(listaProductoCarrito);

			carro.saveRequerimiento(bean);
			deta.limpiarListaDetalles();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/producto/lista";
	}
	@RequestMapping("/eliminar")
	public String eliminar(@RequestParam("codigo") Integer codigo,
			RedirectAttributes redirect) {
		
		for(detalle d: deta.listarTodo()) {
			if(d.getCodigo()==codigo) {
				deta.Eliminar(codigo);
				break;
			}
		}
		redirect.addFlashAttribute("MENSAJE","Producto eliminado");
		return "redirect:/producto/resumen";
	}
}
