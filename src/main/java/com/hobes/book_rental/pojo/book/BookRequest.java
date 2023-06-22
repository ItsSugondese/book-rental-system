package com.hobes.book_rental.pojo.book;

import java.util.Date;

import org.hibernate.validator.constraints.UniqueElements;

import com.hobes.book_rental.model.Category;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookRequest {

	private Long id;
	
	@NotEmpty(message = "{book.name}")
	private String name;
	
	@NotEmpty(message = "{book.noOfPages}")
	private int noOfPages;
	
	@NotEmpty(message = "{book.isbn}")
	@UniqueElements
	private Integer isbn;
	
	@NotEmpty(message = "{book.stockCount}")
	private Integer stockCount;
	
	@NotEmpty(message = "{book.publishedDate}")
	private Date publishedDate;
	
	
	@NotEmpty(message = "{book.photo}")
	private String photo;
	
	@NotEmpty(message = "{book.category}")
	private Long categoryId;
	
	private Long authorId;
}
