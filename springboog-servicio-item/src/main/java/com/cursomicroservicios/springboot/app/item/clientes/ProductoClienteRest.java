package com.cursomicroservicios.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cursomicroservicios.springboot.app.item.models.Producto;

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {
	@GetMapping("/findAllProducto")
	List<Producto> findAll();

	@GetMapping("/findByIdProducto/{id}")
	Producto findById(@PathVariable(value = "id") Long id);

	@PostMapping("/createProducto")
	Producto createProducto(@RequestBody Producto producto);

	@PutMapping("updateProducto/{id}")
	Producto updateProducto(@RequestBody Producto producto, @PathVariable(value = "id") Long id);

	@DeleteMapping("/deleteProducto/{id}")
	void deleteProducto(@PathVariable(value = "id") Long id);
}
