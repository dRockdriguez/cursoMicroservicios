package com.cursomicroservicios.springboot.app.item.models;

public class Item {
	private final Producto producto;
	private final Integer cantidad;

	public Item(final Producto producto, final Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public Double getTotal() {
		return producto.getPrecio() * cantidad.doubleValue();
	}
}
