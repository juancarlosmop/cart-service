package com.example.cart.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.cart.model.RpBase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CartControllerExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RpBase> handleException(Exception ex) {
		log.error("Ocurrio un error en el servicio", ex);
		RpBase resp = new RpBase();
		
		resp.setCode("ERR99");
		resp.setMessage("Ocurrio un error en el servicio " + ex.getMessage());
		return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
