package com.hobes.book_rental.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hobes.book_rental.helper.ResponseHandler;

@RestControllerAdvice
public class SecurityErrors {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Object> notInStock(UsernameNotFoundException ex) {
		return ResponseHandler.generateErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);

	}
}
