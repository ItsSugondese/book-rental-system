package com.hobes.book_rental.service.book;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hobes.book_rental.pojo.book.BookResponse;

public interface BookService {

	List<BookResponse> getAllBooks() throws FileNotFoundException, IOException;

	BookResponse getSingleBook(Long id);

	BookResponse addBook(String data, MultipartFile file) throws IllegalStateException, IOException;
	
	void removeBook(Long id);
		
}
