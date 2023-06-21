package com.hobes.book_rental.pojo.response;

import java.util.List;

import com.hobes.book_rental.model.Book;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AuthorResponse {

	
	private String name;
	
	
	private String email;
	
	
	private String mobileNumber;
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "tbl_book_author",
			joinColumns = {
					@JoinColumn(name="book_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name="author_id")
			}
			)
	private List<Book> books;
}
