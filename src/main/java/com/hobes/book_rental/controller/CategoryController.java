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
import com.hobes.book_rental.pojo.category.CategoryRequest;
import com.hobes.book_rental.pojo.category.CategoryResponse;
import com.hobes.book_rental.service.category.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping
	public ResponseEntity<Object> addUpdateCategory(@Valid @RequestBody CategoryRequest categoryRequest)
			throws AlreadyExistsException {
		CategoryResponse categoryResponse = categoryService.addCategory(categoryRequest);

		if (categoryResponse == null) {
			return ResponseHandler.generateErrorResponse("Category already exists", HttpStatus.CONFLICT);
		}
		return ResponseHandler.generateResponse("Category Successfully added", HttpStatus.CREATED, categoryResponse);
	}

	@GetMapping
	public ResponseEntity<Object> getCategories() {
			List<CategoryResponse> categoryResponses = categoryService.getAllCategories();
			return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, categoryResponses);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getCategory(@PathVariable("id") Long id) {
		CategoryResponse categoryResponses = categoryService.getSingleCategory(id);
		return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, categoryResponses);
	}

	@DeleteMapping
	public ResponseEntity<Object> removeCateogry(@RequestBody Long id) {
		categoryService.removeCategory(id);
		return ResponseHandler.generateRemoveResponse();
	}
}
