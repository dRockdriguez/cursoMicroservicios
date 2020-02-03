package com.cursomicroservicios.springboot.app.productos.exceptions;

public class ProductoNoExistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7717059395069620811L;
	private static final String MESSAGE = "No se ha encontrado el producto con id ";
	public ProductoNoExistenteException(Long id) {
		super(MESSAGE + id);
	}

}
