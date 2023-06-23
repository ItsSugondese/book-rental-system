package com.hobes.book_rental.pojo.book;

import java.util.Date;
import java.util.List;

import com.hobes.book_rental.model.Category;
import com.hobes.book_rental.pojo.author.AuthorResponse;
import com.hobes.book_rental.pojo.category.CategoryResponse;

import lombok.Data;

@Data
public class BookResponse {

	
	private String name;
	
	
	private int noOfPages;
	
	
	private Integer isbn;
	
	
	private Double rating;
	
	
	private Integer stockCount;
	
	
	private Date publishedDate;
	
	private String photo;
	
	
	private CategoryResponse category;
	
	private List<AuthorResponse> authors;
}
