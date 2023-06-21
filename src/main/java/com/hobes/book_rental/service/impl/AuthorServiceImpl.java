package com.hobes.book_rental.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.model.Author;
import com.hobes.book_rental.repo.AuthorRepo;
import com.hobes.book_rental.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService{

	@Autowired
	private AuthorRepo authorRepo;
	
	@Override
	public Author addAuthor(Author author) {
		// TODO Auto-generated method stub
		Author atr = null;
		
		if (this.authorRepo.findAll().size() > 0) {
			if(this.authorRepo.findAll().stream().filter(e -> e.getEmail().equals(author.getEmail())).findAny().isPresent());
				atr = this.authorRepo.findAll().stream().filter(e -> e.getEmail().equals(author.getEmail())).findAny().get();
		}
		if (atr != null) {
			return null;

		}
		this.authorRepo.save(author);
		return author;
	}
}
