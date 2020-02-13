package com.cursomicroservicios.springboot.app.productos.models.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cursomicroservicios.springboot.app.productos.exceptions.ProductoNoExistenteException;
import com.cursomicroservicios.springboot.app.productos.models.repository.ProductoRepository;
import com.cursomicroservicios.springboot.commons.models.entity.Producto;

@Service
public class ProductoService implements IProductoService {

	private final ProductoRepository productoRepository;

	@Autowired
	public ProductoService(final ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		return (List<Producto>) this.productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Producto findById(Long id) {
		return this.productoRepository.findById(id).orElseThrow(() -> new ProductoNoExistenteException(id));
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {
		producto.setCreadoEn(new Date());
		return this.productoRepository.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.productoRepository.findById(id).orElseThrow(() -> new ProductoNoExistenteException(id));
		this.productoRepository.deleteById(id);

	}

}
