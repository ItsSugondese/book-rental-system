package com.hobes.book_rental.service.book;

import java.util.List;

import com.hobes.book_rental.exception.DoesNotExistException;
import com.hobes.book_rental.pojo.book.BookRequest;
import com.hobes.book_rental.pojo.book.BookResponse;

public interface BookService {

	List<BookResponse> getAllBooks();

	BookResponse getSingleBook(Long id);

	BookResponse addBook(BookRequest bookRequest) throws DoesNotExistException;
	
	void removeBook(Long id);
		
}
