package com.hobes.book_rental.errors;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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

import com.hobes.book_rental.exception.DoesNotExistException;
import com.hobes.book_rental.helper.ResponseHandler;

@RestControllerAdvice
public class FileErrorRepo {

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<Object> show(FileNotFoundException ex) {
		return ResponseHandler.generateErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
}
