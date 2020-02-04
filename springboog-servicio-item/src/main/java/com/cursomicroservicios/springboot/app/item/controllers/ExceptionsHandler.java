package com.cursomicroservicios.springboot.app.item.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ExceptionsHandler {
	@ExceptionHandler(value = HttpClientErrorException.class)
	public ResponseEntity<String> exception(HttpClientErrorException exception) {
	
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		return new ResponseEntity<>( exception.getResponseBodyAsString(), responseHeaders, HttpStatus.NOT_FOUND);
	}
}
