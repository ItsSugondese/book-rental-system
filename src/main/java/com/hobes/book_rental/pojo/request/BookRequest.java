package com.hobes.book_rental.pojo.request;

import java.util.Date;

import com.hobes.book_rental.model.Category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookRequest {

	
	@NotEmpty(message = "{book.name}")
	private String name;
	
	@NotEmpty(message = "{book.noOfPages}")
	private int noOfPages;
	
	@NotEmpty(message = "{book.isbn}")
	private Integer isbn;
	
	@NotEmpty(message = "{book.stockCount}")
	private Integer stockCount;
	
	@NotEmpty(message = "{book.publishedDate}")
	private Date publishedDate;
	
	
	@NotEmpty(message = "{book.photo}")
	private String photo;
	
	@NotEmpty(message = "{book.category}")
	private Category categoryId;
}
