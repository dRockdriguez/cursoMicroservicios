package com.cursomicroservicios.springboot.app.productos.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.cursomicroservicios.springboot.app.productos.models.entity.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

}
