package com.hobes.book_rental.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookStockException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookStockException() {
		super("Book you are requesting is not availabe on stock");
	}

}
