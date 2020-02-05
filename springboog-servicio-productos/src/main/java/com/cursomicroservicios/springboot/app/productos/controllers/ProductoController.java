package com.cursomicroservicios.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cursomicroservicios.springboot.app.productos.models.entity.Producto;
import com.cursomicroservicios.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	@Value("${server.port}")
	private Integer port;
	
	private final IProductoService productoService;

	@Autowired
	public ProductoController(final IProductoService productoService) {
		this.productoService = productoService;
	}

	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> findAll() {
		return new ResponseEntity<>(this.productoService.findAll().stream().map(prod -> {
			prod.setPort(port);
			return prod;
		}).collect(Collectors.toList()), HttpStatus.OK);

	}

	@GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> findById(@PathVariable(value = "id") Long id) {
		Producto prod = this.productoService.findById(id);
		prod.setPort(port);
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}
}
