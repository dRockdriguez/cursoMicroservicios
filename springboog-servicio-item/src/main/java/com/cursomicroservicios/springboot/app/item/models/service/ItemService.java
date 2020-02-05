package com.cursomicroservicios.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cursomicroservicios.springboot.app.item.models.Item;
import com.cursomicroservicios.springboot.app.item.models.Producto;

@Service("serviceRestTemplate")
public class ItemService implements IItemService {

	private final RestTemplate restTemplate;

	@Autowired
	public ItemService(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<Item> findAll() {
		StringBuilder url = new StringBuilder();
		url.append("http://servicio-productos/findAll");

		List<Producto> productos = Arrays.asList(restTemplate.getForObject(url.toString(), Producto[].class));
		return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		StringBuilder url = new StringBuilder();
		url.append("http://servicio-productos/findById/{id}");

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());
		Producto producto = restTemplate.getForObject(url.toString(), Producto.class, pathVariables);
		return new Item(producto, cantidad);

	}

}
