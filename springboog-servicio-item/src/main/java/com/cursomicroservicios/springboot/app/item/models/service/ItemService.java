package com.cursomicroservicios.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cursomicroservicios.springboot.app.item.models.Item;
import com.cursomicroservicios.springboot.commons.models.entity.Producto;

@Service
@Qualifier(value = "serviceRestTemplate")
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

	@Override
	public Producto save(Producto producto) {
		StringBuilder url = new StringBuilder();
		url.append("http://servicio-productos/createProducto");

		HttpEntity<Producto> body = new HttpEntity<>(producto);

		ResponseEntity<Producto> resp = restTemplate.exchange(url.toString(), HttpMethod.POST, body, Producto.class);

		return resp.getBody();
	}

	@Override
	public Producto update(Producto producto, Long id) {
		StringBuilder url = new StringBuilder();
		url.append("http://servicio-productos/updateProducto/{id}");

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());

		HttpEntity<Producto> body = new HttpEntity<>(producto);

		ResponseEntity<Producto> resp = restTemplate.exchange(url.toString(), HttpMethod.PUT, body, Producto.class,
				pathVariables);

		return resp.getBody();
	}

	@Override
	public void delete(Long id) {
		StringBuilder url = new StringBuilder();
		url.append("http://servicio-productos/deleteProducto/{id}");

		Map<String, String> pathVariables = new HashMap<>();
		pathVariables.put("id", id.toString());

		restTemplate.delete(url.toString(), pathVariables);

	}

}
