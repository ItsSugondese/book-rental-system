package com.hobes.book_rental.service;

import com.hobes.book_rental.model.Category;
import com.hobes.book_rental.pojo.request.CategoryRequest;
import com.hobes.book_rental.pojo.response.CategoryResponse;

public interface CategoryService {

	CategoryResponse addCategory(CategoryRequest category);
}
