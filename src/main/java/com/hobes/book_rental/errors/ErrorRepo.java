package com.hobes.book_rental.errors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorRepo {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, List<String>>> show(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		
		List<String> errors = new ArrayList<>();
		
		for(FieldError err : result.getFieldErrors()) {
			errors.add(err.getDefaultMessage());
		}
		

		return new ResponseEntity<>(displayErrors(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	Map<String, List<String>> displayErrors(List<String> errors){
		 Map<String, List<String>> errorResponse = new HashMap<>();
	        errorResponse.put("errors", errors);
	        return errorResponse;
	}
}
