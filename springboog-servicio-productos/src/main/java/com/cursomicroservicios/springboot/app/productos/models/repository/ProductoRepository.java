package com.cursomicroservicios.springboot.app.productos.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.cursomicroservicios.springboot.commons.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
