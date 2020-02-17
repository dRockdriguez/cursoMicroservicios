package com.cursomicroservicios.springboot.oauth.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cursomicroservicios.springboot.usuarios.commons.models.entity.Usuario;

@FeignClient(name = "servicio-usuarios")
public interface UsuarioFeignClient {
	@GetMapping(value = "usuarios/buscar-username")
	public Usuario findByUsername(@RequestParam String username);
}
