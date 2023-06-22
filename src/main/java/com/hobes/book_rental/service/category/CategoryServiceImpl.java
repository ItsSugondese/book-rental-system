package com.hobes.book_rental.service.category;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.model.Category;
import com.hobes.book_rental.pojo.category.CategoryRequest;
import com.hobes.book_rental.pojo.category.CategoryResponse;
import com.hobes.book_rental.repo.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepo categoryRepo;
	private ModelMapper modelMapper;

	public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
		this.categoryRepo = categoryRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryResponse addCategory(CategoryRequest categoryRequest) {
		Category category;

		if (categoryRequest.getId() != null)
			category = categoryRepo.getReferenceById(categoryRequest.getId()).orElse(new Category());

//		category = dtoToModel(categoryRequest);

		category = modelMapper.map(categoryRequest, Category.class);

//		CategoryResponse response = modelToDto(categoryRepo.save(category));

		CategoryResponse response = modelMapper.map(categoryRepo.save(category), CategoryResponse.class);

		return response;
	}

	public Category dtoToModel(CategoryRequest categoryRequest) {
		Category category = new Category();
		category.setName(categoryRequest.getName());
		category.setDescription(categoryRequest.getDescription());
		category.setId(categoryRequest.getId());
		return category;
	}

	public CategoryResponse modelToDto(Category category) {
		CategoryResponse response = new CategoryResponse();

		response.setDescription(category.getDescription());
		response.setName(category.getName());

		return response;
	}

	@Override
	public List<CategoryResponse> getAllCategories() {
		List<CategoryResponse> categoryResponses = (categoryRepo.findAll()).stream()
				.map(e -> modelMapper.map(e, CategoryResponse.class)).collect(Collectors.toList());
		
		
		return categoryResponses;
	}

	@Override
	public CategoryResponse getSingleCategory(Long id) {
		CategoryResponse categoryResponse = modelMapper.map((categoryRepo.getReferenceById(id)).get(), CategoryResponse.class);
		return categoryResponse;
	}

	@Override
	public void removeCategory(Long id) {
		categoryRepo.delete(categoryRepo.getReferenceById(id).get());
	}

}
