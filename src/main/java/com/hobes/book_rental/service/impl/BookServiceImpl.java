package com.hobes.book_rental.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.model.Book;
import com.hobes.book_rental.repo.BookRepo;
import com.hobes.book_rental.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;
	
	@Override
	public Book addBook(Book book) {
		Book bk = null;
		

		if (this.bookRepo.findAll().size() > 0) {
			if(this.bookRepo.findAll().stream().filter(e -> e.getIsbn() == (book.getIsbn())).findAny().isPresent());
				bk = this.bookRepo.findAll().stream().filter(e -> e.getIsbn() == (book.getIsbn())).findAny().get();
		}
		
		if (bk != null) {
			return null;
		}
		
		
		this.bookRepo.save(book);
		return book;
	}
}
