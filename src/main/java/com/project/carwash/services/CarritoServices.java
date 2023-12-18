package com.project.carwash.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.carwash.entity.Carrito;
import com.project.carwash.entity.ProductoCarrito;
import com.project.carwash.entity.ProductoCarritoPK;
import com.project.carwash.repository.CarritoProductoRepository;
import com.project.carwash.repository.CarritoRepository;

import jakarta.transaction.Transactional;

@Service
public class CarritoServices {
	@Autowired
	private CarritoRepository repoReque;
	
	@Autowired
	private CarritoProductoRepository repoDet;
	
	@Transactional
	public void saveRequerimiento(Carrito bean) {
		try {
			repoReque.save(bean);
			//bucle para guardar el detalle
			
			for(ProductoCarrito det:bean.getListaCarrito()){
				//crear objeto llave
				ProductoCarritoPK llave=new ProductoCarritoPK();
				
				//setear 
				llave.setCod_carrito(bean.getCodigo());
				llave.setCod_producto(det.getProducto().getCodigo());
				
				//enviar objeto llave al objeto "det"
				det.setPk(llave);
				
				//grabar detalle "BienRequerimiento"
				repoDet.save(det);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
