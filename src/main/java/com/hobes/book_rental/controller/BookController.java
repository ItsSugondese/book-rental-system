package com.hobes.book_rental.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.hobes.book_rental.model.Book;
import com.hobes.book_rental.service.BookService;
import com.hobes.book_rental.service.impl.BookServiceImpl;

import jakarta.validation.Valid;

@RestController
public class BookController {

	private BookService bookService;
	
	@PostMapping("/add_book")
	public  ResponseEntity<Book> addBk(@Valid @RequestBody Book bk) {
		Book book = this.bookService.addBook(bk);
		
		if(book == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
}
