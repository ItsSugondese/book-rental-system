package com.hobes.book_rental.service.book;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.model.Book;
import com.hobes.book_rental.pojo.book.BookRequest;
import com.hobes.book_rental.pojo.book.BookResponse;
import com.hobes.book_rental.repo.BookRepo;

@Service
public class BookServiceImpl implements BookService {

	private BookRepo bookRepo;
	private ModelMapper modelMapper;

	public BookServiceImpl(BookRepo bookRepo, ModelMapper modelMapper) {
		this.bookRepo = bookRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<BookResponse> getAllBooks() {
		List<BookResponse> bookResponses = (bookRepo.findAll()).stream()
				.map(e -> modelMapper.map(e, BookResponse.class)).collect(Collectors.toList());

		return bookResponses;
	}

	@Override
	public BookResponse getSingleBook(Long id) {
		BookResponse bookResponse = modelMapper.map((bookRepo.getReferenceById(id)).get(), BookResponse.class);
		return bookResponse;
	}

	@Override
	public BookResponse addBook(BookRequest bookRequest) {
		Book book;

		if (bookRequest.getId() != null)
			book = bookRepo.getReferenceById(bookRequest.getId()).orElse(new Book());

		book = modelMapper.map(bookRequest, Book.class);

		BookResponse response = modelMapper.map(bookRepo.save(book), BookResponse.class);

		return response;
	}

	@Override
	public void removeBook(Long id) {
		bookRepo.delete(bookRepo.getReferenceById(id).get());

	}


}
