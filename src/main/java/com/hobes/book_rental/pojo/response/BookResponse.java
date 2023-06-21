package com.hobes.book_rental.pojo.response;

import java.util.Date;

import com.hobes.book_rental.model.Category;

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
	
	
	private Category categoryId;
}
