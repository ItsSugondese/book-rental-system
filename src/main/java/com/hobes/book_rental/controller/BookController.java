package com.hobes.book_rental.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobes.book_rental.exception.AlreadyExistsException;
import com.hobes.book_rental.helper.ResponseHandler;
import com.hobes.book_rental.pojo.book.BookRequest;
import com.hobes.book_rental.pojo.book.BookResponse;
import com.hobes.book_rental.service.book.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}


	@PostMapping
	public ResponseEntity<Object> addUpdateBook(@Valid @RequestBody BookRequest bookRequest)
			throws AlreadyExistsException {
		BookResponse bookResponse = bookService.addBook(bookRequest);

		if (bookResponse == null) {
			return ResponseHandler.generateErrorResponse("Category already exists", HttpStatus.CONFLICT);
		}
		return ResponseHandler.generateResponse("Book Successfully added", HttpStatus.CREATED, bookResponse);
	}
	
	@GetMapping
	public ResponseEntity<Object> getBooks() {
			List<BookResponse> bookResponse = bookService.getAllBooks();
			return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, bookResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getBook(@PathVariable("id") Long id) {
		BookResponse bookResponse = bookService.getSingleBook(id);
		return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, bookResponse);
	}
	
	@DeleteMapping
	public ResponseEntity<Object> removeBook(@RequestBody Long id) {
		bookService.removeBook(id);
		return ResponseHandler.generateRemoveResponse();
	}
}
