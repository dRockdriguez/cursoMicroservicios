package com.cursomicroservicios.springboot.app.item.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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

import com.cursomicroservicios.springboot.app.item.models.Item;
import com.cursomicroservicios.springboot.app.item.models.service.IItemService;
import com.cursomicroservicios.springboot.commons.models.entity.Producto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class ItemController {
	private final IItemService itemService;
	@Value("${configuracion.env}")
	private String env;

	@Autowired
	public ItemController(final @Qualifier("serviceFeign") IItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping(value = "/pruebaconfig")
	public ResponseEntity<?> pruebaconfig() {
		Map<String, String> resp = new HashMap<String, String>();
		resp.put("env", env);
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.OK);
	}

	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Item>> findAll() {
		return new ResponseEntity<>(this.itemService.findAll(), HttpStatus.OK);
	}

	@HystrixCommand(fallbackMethod = "alternativeMethod")
	@GetMapping(value = "/findById/{id}/cantidad/{cantidad}")
	public ResponseEntity<Item> findById(@PathVariable(value = "id") Long id,
			@PathVariable(value = "cantidad") Integer cantidad) {
		return new ResponseEntity<>(this.itemService.findById(id, cantidad), HttpStatus.OK);
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> create(@RequestBody Producto producto) {
		return new ResponseEntity<>(this.itemService.save(producto), HttpStatus.CREATED);
	}

	@PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> update(@RequestBody Producto producto, @PathVariable(name = "id") Long id) {
		return new ResponseEntity<>(this.itemService.update(producto, id), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(name = "id") Long id) {
		this.itemService.delete(id);
	}

	public ResponseEntity<Item> alternativeMethod(Long id, Integer cantidad) {
		Producto p = new Producto(id, "prod no existente", 0d, new Date());
		return new ResponseEntity<>(new Item(p, cantidad), HttpStatus.OK);
	}

}
