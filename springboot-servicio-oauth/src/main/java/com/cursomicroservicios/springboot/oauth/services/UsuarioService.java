package com.cursomicroservicios.springboot.oauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cursomicroservicios.springboot.oauth.clients.UsuarioFeignClient;
import com.cursomicroservicios.springboot.usuarios.commons.models.entity.Usuario;

@Service
public class UsuarioService implements UserDetailsService {

	private final UsuarioFeignClient client;

	@Autowired
	public UsuarioService(final UsuarioFeignClient client) {
		this.client = client;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = client.findByUsername(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("No encontrado");
		}
		List<GrantedAuthority> authorities = usuario.getRoles().stream()
				.map(r -> new SimpleGrantedAuthority(r.getNombre())).peek(a -> System.out.println(a.getAuthority()))
				.collect(Collectors.toList());
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true,
				authorities);
	}

}
