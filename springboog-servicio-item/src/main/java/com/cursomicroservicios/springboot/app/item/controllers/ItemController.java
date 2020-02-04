package com.cursomicroservicios.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cursomicroservicios.springboot.app.item.models.Item;
import com.cursomicroservicios.springboot.app.item.models.service.ItemService;

@RestController
public class ItemController {
	private final ItemService itemService;

	@Autowired
	public ItemController(final ItemService itemService) {
		this.itemService = itemService;
	}

	@GetMapping(value = "/findAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Item>> findAll() {
		return new ResponseEntity<>(this.itemService.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "findById/{id}/cantidad/{cantidad}")
	public ResponseEntity<Item> findById(@PathVariable(value = "id") Long id,
			@PathVariable(value = "cantidad") Integer cantidad) {
		return new ResponseEntity<>(this.itemService.findById(id, cantidad), HttpStatus.OK);
	}
}