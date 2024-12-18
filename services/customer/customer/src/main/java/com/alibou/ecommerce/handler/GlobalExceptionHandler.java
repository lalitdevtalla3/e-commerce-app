package com.alibou.ecommerce.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alibou.ecommerce.exception.CustomerNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException ex){
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMsg());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException ex){
		
		
	var erros = new HashMap<String , String>();
	ex.getBindingResult().getAllErrors().forEach(
			error -> {
				var fieldName = ((FieldError)error).getField();
				var errorMessage = error.getDefaultMessage();
				erros.put(fieldName, errorMessage);
			}
			);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(erros));
	}

}
