package com.hobes.book_rental.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {

	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObject){
		Map<String, Object> response = new HashMap();
		
		response.put("message", message);
		response.put("status", status.value());
		response.put("data", responseObject);
		
		return new ResponseEntity<Object>(response, status);
	}
	
	public static ResponseEntity<Object> generateErrorResponse(String message, HttpStatus status){
		Map<String, Object> response = new HashMap();
		
		response.put("message", message);
		response.put("status", status.value());
		
		return new ResponseEntity<Object>(response, status);
	}
	
	public static ResponseEntity<Object> generateRemoveResponse(){
		Map<String, Object> response = new HashMap();
		
		response.put("message", "data removed successfully");
		response.put("status", HttpStatus.OK.value());
		
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}
}
