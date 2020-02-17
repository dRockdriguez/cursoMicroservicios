package com.cursomicroservicios.springboot.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.cursomicroservicios.springboot.usuarios.commons.models.entity.Usuario;


@RepositoryRestResource(path = "usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {
	@RestResource(path = "buscar-username")
	Usuario findByUsername(String username);

	@Query("SELECT u FROM Usuario u WHERE u.username = ?1")
	Usuario buscarPorUsername(String username);
}
