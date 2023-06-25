package com.hobes.book_rental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobes.book_rental.helper.ResponseHandler;
import com.hobes.book_rental.pojo.book.BookResponse;
import com.hobes.book_rental.pojo.book_transaction.BookTransactionResponse;
import com.hobes.book_rental.pojo.book_transaction.book_rent.BookRentRequest;
import com.hobes.book_rental.pojo.book_transaction.book_rent.BookRentResponse;
import com.hobes.book_rental.pojo.book_transaction.book_return.BookReturnRequest;
import com.hobes.book_rental.pojo.book_transaction.book_return.BookReturnResponse;
import com.hobes.book_rental.service.book_transaction.BookTransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book-transaction")
public class BookTransactionController {

	private BookTransactionService bookTransactionService;

	public BookTransactionController(BookTransactionService bookTransactionService) {
		this.bookTransactionService = bookTransactionService;
	}

	@GetMapping
	public ResponseEntity<Object> getTransactions() {
		List<BookTransactionResponse> responses = bookTransactionService.getAllBookTransactions();
		return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, responses);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getSignleTransaction(@PathVariable("id") Long id) {
		BookTransactionResponse bookTransactionResponse = bookTransactionService.getSingleBookTransaction(id);
		return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, bookTransactionResponse);
	}
	
	
	@PostMapping("/rent")
	public ResponseEntity<Object> rentBook(@Valid @RequestBody BookRentRequest bookRentRequest) {
		BookRentResponse bookRentResponse = bookTransactionService.addBookTransaction(bookRentRequest);

		if (bookRentResponse == null) {
			return ResponseHandler.generateErrorResponse("Transaction already exists", HttpStatus.CONFLICT);
		}
		return ResponseHandler.generateResponse("BookTransaction Successfully added", HttpStatus.CREATED, bookRentResponse);
	}
	
	
	
	
	@PostMapping("/return")
	public ResponseEntity<Object> returnBook(@Valid @RequestBody BookReturnRequest bookReturnRequest) {
		BookReturnResponse bookReturnResponse = bookTransactionService.returnBookTransaction(bookReturnRequest);

		if (bookReturnResponse == null) {
			return ResponseHandler.generateErrorResponse("Transaction already exists", HttpStatus.CONFLICT);
		}
		return ResponseHandler.generateResponse("Book Successfully returned", HttpStatus.CREATED, bookReturnResponse);
	}
	
	@DeleteMapping
	public ResponseEntity<Object> removeTransaction(@RequestBody Long id) {
		bookTransactionService.removeBookTransaction(id);
		return ResponseHandler.generateRemoveResponse();
	}
}
