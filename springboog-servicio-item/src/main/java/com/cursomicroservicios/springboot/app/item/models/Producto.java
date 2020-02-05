package com.cursomicroservicios.springboot.app.item.models;

import java.util.Date;

public class Producto {
	private Long id;
	private String nombre;
	private Double precio;
	private Date creadoEn;
	private Integer port;

	public Producto() {

	}

	public Producto(final Long id, final String nombre, final Double precio, final Date creadoEn, final Integer port) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.creadoEn = creadoEn;
		this.port = port;
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

	public Integer getPort() {
		return port;
	}

}
