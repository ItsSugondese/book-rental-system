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
import com.hobes.book_rental.pojo.author.AuthorRequest;
import com.hobes.book_rental.pojo.author.AuthorResponse;
import com.hobes.book_rental.service.author.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/author")
public class AuthorController {

	private AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@PostMapping
	public ResponseEntity<Object> addUpdateAuthor(@Valid @RequestBody AuthorRequest authorRequest)
			throws AlreadyExistsException {
		AuthorResponse authorResponse = authorService.addAuthor(authorRequest);

		if (authorResponse == null) {
			return ResponseHandler.generateErrorResponse("Category already exists", HttpStatus.CONFLICT);
		}
		return ResponseHandler.generateResponse("Author Successfully added", HttpStatus.CREATED, authorResponse);
	}

	@GetMapping
	public ResponseEntity<Object> getAuthors() {
			List<AuthorResponse> authorResponses = authorService.getAllAuthor();
			return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, authorResponses);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getAuthor(@PathVariable("id") Long id) {
		AuthorResponse authorResponse = authorService.getSingleAuthor(id);
		return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, authorResponse);
	}
	
	@DeleteMapping
	public ResponseEntity<Object> removeAuthor(@RequestBody Long id) {
		authorService.removeAuthor(id);
		return ResponseHandler.generateRemoveResponse();
	}

}
