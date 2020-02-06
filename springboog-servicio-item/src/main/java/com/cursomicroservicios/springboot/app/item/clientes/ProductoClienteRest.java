package com.cursomicroservicios.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cursomicroservicios.springboot.app.item.models.Producto;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {
	@GetMapping("/findAllProducto")
	List<Producto> findAll();

	@GetMapping("findByIdProducto/{id}")
	Producto findById(@PathVariable(value = "id") Long id);

}
