package com.hobes.book_rental.pojo.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CategoryRequest {

	@NotEmpty(message = "{category.name}")
	private String name;
	
	@NotEmpty(message = "{category.description}")
	private String description;
}
