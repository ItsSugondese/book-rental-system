package com.hobes.book_rental.pojo.book;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookRequest {

	private Long id;
	
	@NotEmpty(message = "{book.name}")
	private String name;
	
	@NotNull(message = "{book.noOfPages}")
	private Integer noOfPages;
	
	@NotNull(message = "{book.isbn}")
	private Integer isbn;
	
	@NotNull(message = "{book.stockCount}")
	private Integer stockCount;
	
	@NotNull(message = "{book.publishedDate}")
	private LocalDate publishedDate;
	
	
	@NotEmpty(message = "{book.photo}")
	private String photo;
	
	@NotNull(message = "{book.category}")
	private Long categoryId;
	
	@NotEmpty(message = "{book.authorId}")
	private List<Long> authorId;
}
