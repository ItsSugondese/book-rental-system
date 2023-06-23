package com.hobes.book_rental.pojo.author;

import java.util.List;

import com.hobes.book_rental.model.Book;
import com.hobes.book_rental.pojo.book.BookResponse;

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
}
