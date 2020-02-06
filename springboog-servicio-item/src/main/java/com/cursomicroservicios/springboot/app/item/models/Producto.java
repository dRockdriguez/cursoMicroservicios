package com.cursomicroservicios.springboot.app.item.models;

import java.util.Date;

public class Producto {
	private Long id;
	private String nombre;
	private Double precio;
	private Date creadoEn;

	public Producto() {

	}

	public Producto(final Long id, final String nombre, final Double precio, final Date creadoEn) {
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.creadoEn = creadoEn;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public Date getCreadoEn() {
		return creadoEn;
	}

}
