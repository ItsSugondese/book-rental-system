package com.hobes.book_rental.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookReturnTransactionException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public BookReturnTransactionException() {
		super("Didn't find any transaction details with the information provided");
	}

}
