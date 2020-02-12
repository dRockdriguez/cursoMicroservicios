package com.cursomicroservicios.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cursomicroservicios.springboot.app.item.clientes.ProductoClienteRest;
import com.cursomicroservicios.springboot.app.item.models.Item;
import com.cursomicroservicios.springboot.app.item.models.Producto;

@Service
@Qualifier(value = "serviceFeign")
public class ItemServiceFeign implements IItemService {

	private final ProductoClienteRest productoClienteRest;

	@Autowired
	public ItemServiceFeign(final ProductoClienteRest productoClienteRest) {
		this.productoClienteRest = productoClienteRest;
	}

	@Override
	public List<Item> findAll() {
		return productoClienteRest.findAll().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		return new Item(productoClienteRest.findById(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		return productoClienteRest.createProducto(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		return productoClienteRest.updateProducto(producto, id);
	}

	@Override
	public void delete(Long id) {
		productoClienteRest.deleteProducto(id);		
	}

}
