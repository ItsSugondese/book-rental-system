package com.hobes.book_rental.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hobes.book_rental.model.Category;
import com.hobes.book_rental.pojo.request.CategoryRequest;
import com.hobes.book_rental.pojo.response.CategoryResponse;
import com.hobes.book_rental.service.CategoryService;

import jakarta.validation.Valid;

@RestController
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
//	@PostMapping("/add_category")
//	public  ResponseEntity addStd(@Valid @RequestBody Category cty) {
//		Category category = this.categoryService.addCategory(cty);
//		
//		if(category == null) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body("Category of name " + cty.getName() + " alreayd exists.");
//		}
//		return ResponseEntity.of(Optional.of(category));
//	}

	@PostMapping("/add_category")
	public  ResponseEntity addStd(@Valid @RequestBody CategoryRequest cty) {
		CategoryResponse category = this.categoryService.addCategory(cty);
		
		if(category == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Category of name " + cty.getName() + " alreayd exists.");
		}
		return ResponseEntity.of(Optional.of(category));
	}

}
