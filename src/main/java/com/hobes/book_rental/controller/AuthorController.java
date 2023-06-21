package com.hobes.book_rental.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hobes.book_rental.model.Author;
import com.hobes.book_rental.service.AuthorService;

import jakarta.validation.Valid;

@RestController
public class AuthorController {
	
	
	private AuthorService authorService;
	
	@PostMapping("/add")
	public  ResponseEntity<Author> addAtr(@Valid @RequestBody Author atr) {
		Author author = this.authorService.addAuthor(atr);
		
		if(author == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.of(Optional.of(author));
	}

}
