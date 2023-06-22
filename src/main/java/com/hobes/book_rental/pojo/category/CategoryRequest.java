package com.hobes.book_rental.pojo.category;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryRequest {

	private Long id;
	
	@NotEmpty(message = "{category.name}")
	private String name;
	
	@NotEmpty(message = "{category.description}")
	private String description;
}
