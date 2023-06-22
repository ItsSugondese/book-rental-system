package com.hobes.book_rental.service.author;

import java.util.List;

import com.hobes.book_rental.pojo.author.AuthorRequest;
import com.hobes.book_rental.pojo.author.AuthorResponse;

public interface AuthorService {

	List<AuthorResponse> getAllAuthor();

	AuthorResponse getSingleAuthor(Long id);

	AuthorResponse addAuthor(AuthorRequest authorRequest);

	void removeAuthor(Long id);

}
