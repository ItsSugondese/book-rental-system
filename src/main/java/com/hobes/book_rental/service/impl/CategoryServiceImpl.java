package com.hobes.book_rental.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.model.Category;
import com.hobes.book_rental.pojo.request.CategoryRequest;
import com.hobes.book_rental.pojo.response.CategoryResponse;
import com.hobes.book_rental.repo.CategoryRepo;
import com.hobes.book_rental.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public CategoryResponse addCategory(CategoryRequest category) {
		Category cty = null;
		

		if (this.categoryRepo.findAll().size() > 0) {
			if(this.categoryRepo.findAll().stream().filter(e -> e.getName().equals(category.getName())).findAny().isPresent());
				cty = this.categoryRepo.findAll().stream().filter(e -> e.getName().equals(category.getName())).findAny().get();
		}
		
		if (cty != null) {
			return null;
		}
		cty = dtoToModel(category);
		
		CategoryResponse response =modelToDto(this.categoryRepo.save(cty));
		
		
		return response;
	}
	
	public Category dtoToModel(CategoryRequest categoryRequest) {
		Category category = new Category();
		category.setName(categoryRequest.getName());
		category.setDescription(categoryRequest.getDescription());
		
		return category;
	}
	
	public CategoryResponse modelToDto(Category category) {
		CategoryResponse response = new CategoryResponse();
		
		response.setDescription(category.getDescription());
		response.setName(category.getName());
		
		return response;
	}
	
}
