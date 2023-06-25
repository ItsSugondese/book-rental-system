package com.hobes.book_rental.errors;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hobes.book_rental.exception.BookReturnTransactionException;
import com.hobes.book_rental.exception.BookStockException;
import com.hobes.book_rental.exception.DoesNotExistException;
import com.hobes.book_rental.helper.ResponseHandler;

import jakarta.persistence.NonUniqueResultException;

@RestControllerAdvice
public class ErrorRepo {

	// When user tries to insert already existing value in column that requires
	// unique value
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Object> dublicateValue(SQLIntegrityConstraintViolationException ex) {
		String result = ex.getMessage();

		Pattern pattern = Pattern.compile("'(.*?)'");

		Matcher matcher = pattern.matcher(result);
		String insertedValue = "";

		if (matcher.find()) {
			insertedValue = matcher.group(1);
		}

		return ResponseHandler.generateErrorResponse("Value " + insertedValue + " already exisits",
				HttpStatus.CONFLICT);
	}

	// when user pass the value or key that doesn't exist in database
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> noValue(NoSuchElementException ex) {

		return ResponseHandler.generateErrorResponse("No data exists with that primary key", HttpStatus.NOT_FOUND);
	}

	// when user passes the fk value that doesn't exist in parent table
	@ExceptionHandler(DoesNotExistException.class)
	public ResponseEntity<Object> noValue(DoesNotExistException ex) {
		return ResponseHandler.generateErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	// when user send bad request
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> badRequest(HttpMessageNotReadableException ex) {
		String exceptionName = ex.getMostSpecificCause().getClass().getSimpleName();

		if (exceptionName.equals("MismatchedInputException")) {
			return ResponseHandler.generateErrorResponse(
					"The way you are providing value is in wrong format. " + ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return ResponseHandler.generateErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	
	//when user tries to rent book again
	@ExceptionHandler(NonUniqueResultException.class)
	public ResponseEntity<Object> sameTransaction() {

		return ResponseHandler.generateErrorResponse("The user have already rented a book.", HttpStatus.CONFLICT);

	}
	
	
	
	
	// when user tries to rent book not avaialbe for stock
	@ExceptionHandler(BookStockException.class)
	public ResponseEntity<Object> notInStock(BookStockException ex) {
		return ResponseHandler.generateErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);

	}
	
	
	
	
	// when there is no transaction history avaiale when returning the book
		@ExceptionHandler(BookReturnTransactionException.class)
		public ResponseEntity<Object> notInStock(BookReturnTransactionException ex) {
			return ResponseHandler.generateErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);

		}
	
	

	// when user leave out compulsory field when providing data
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, List<String>>> show(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();

		List<String> errors = new ArrayList<>();

		for (FieldError err : result.getFieldErrors()) {
			errors.add(err.getDefaultMessage());
		}

		return new ResponseEntity<>(displayErrors(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	Map<String, List<String>> displayErrors(List<String> errors) {
		Map<String, List<String>> errorResponse = new HashMap<>();
		errorResponse.put("errors", errors);
		return errorResponse;
	}

}
