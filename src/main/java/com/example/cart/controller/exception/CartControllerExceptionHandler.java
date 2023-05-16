package com.example.cart.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.cart.dto.RpBase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class CartControllerExceptionHandler {
	
	/* Method to handle exceptions
	 * 
	 * @param Exception pass exception as a parameter
	 * @return  RpBase Dto response
	 * */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RpBase> handleException(Exception ex) {
		log.error("Ocurrio un error en el servicio", ex);
		RpBase resp = new RpBase();
		
		resp.setCode("ERR99");
		resp.setMessage("Ocurrio un error en el servicio " + ex.getMessage());
		return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
