package com.hobes.book_rental.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DoesNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public DoesNotExistException(String key, String column) {
		super("No element exists of key " + key + " for column " + column +".");
	}
}
