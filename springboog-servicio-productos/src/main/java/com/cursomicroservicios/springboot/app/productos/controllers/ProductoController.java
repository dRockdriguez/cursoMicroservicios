package com.cursomicroservicios.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cursomicroservicios.springboot.app.productos.models.service.IProductoService;
import com.cursomicroservicios.springboot.commons.models.entity.Producto;

@RestController
public class ProductoController {
	@Value("${server.port}")
	private Integer port;

	private final IProductoService productoService;

	@Autowired
	public ProductoController(final IProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping(value = "/findAllProducto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> findAll() {
		return new ResponseEntity<>(this.productoService.findAll().stream().collect(Collectors.toList()),
				HttpStatus.OK);

	}

	@GetMapping(value = "/findByIdProducto/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> findById(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<>(this.productoService.findById(id), HttpStatus.OK);
	}

	@PostMapping(value = "/createProducto", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
		return new ResponseEntity<>(this.productoService.save(producto), HttpStatus.CREATED);
	}

	@PutMapping(value = "updateProducto/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto, @PathVariable(value = "id") Long id) {
		Producto p = this.productoService.findById(id);
		p.setNombre(producto.getNombre());
		p.setPrecio(producto.getPrecio());
		return new ResponseEntity<>(this.productoService.save(p), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/deleteProducto/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProducto(@PathVariable(value = "id") Long id) {
		this.productoService.deleteById(id);
	}
}
