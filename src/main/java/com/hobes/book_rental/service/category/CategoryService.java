package com.hobes.book_rental.service.category;

import java.util.List;

import com.hobes.book_rental.pojo.category.CategoryRequest;
import com.hobes.book_rental.pojo.category.CategoryResponse;

public interface CategoryService {

	List<CategoryResponse> getAllCategories();
	
	CategoryResponse getSingleCategory(Long id);
	
	CategoryResponse addCategory(CategoryRequest categoryRequest);
	
	void removeCategory(Long id);
}
