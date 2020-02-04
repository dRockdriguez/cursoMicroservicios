package com.cursomicroservicios.springboot.app.item.models.service;

import java.util.List;

import com.cursomicroservicios.springboot.app.item.models.Item;

public interface IItemService {
	List<Item> findAll();

	Item findById(Long id, Integer cantidad);
}
