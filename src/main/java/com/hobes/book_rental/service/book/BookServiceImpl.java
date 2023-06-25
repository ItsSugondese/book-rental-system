package com.hobes.book_rental.service.book;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.exception.DoesNotExistException;
import com.hobes.book_rental.model.Author;
import com.hobes.book_rental.model.Book;
import com.hobes.book_rental.model.Category;
import com.hobes.book_rental.pojo.author.AuthorResponse;
import com.hobes.book_rental.pojo.book.BookRequest;
import com.hobes.book_rental.pojo.book.BookResponse;
import com.hobes.book_rental.pojo.category.CategoryResponse;
import com.hobes.book_rental.repo.AuthorRepo;
import com.hobes.book_rental.repo.BookRepo;
import com.hobes.book_rental.repo.CategoryRepo;

@Service
public class BookServiceImpl implements BookService {

	private BookRepo bookRepo;
	private ModelMapper modelMapper;
	private CategoryRepo categoryRepo;
	private AuthorRepo authorRepo;

	public BookServiceImpl(BookRepo bookRepo, ModelMapper modelMapper, CategoryRepo categoryRepo,
			AuthorRepo authorRepo) {
		this.bookRepo = bookRepo;
		this.modelMapper = modelMapper;
		this.categoryRepo = categoryRepo;
		this.authorRepo = authorRepo;
	}

	@Override
	public List<BookResponse> getAllBooks() {
		List<BookResponse> bookResponses = (bookRepo.findAll()).stream()
				.map(e -> modelMapper.map(e, BookResponse.class)).collect(Collectors.toList());

		return bookResponses;
	}

	@Override
	public BookResponse getSingleBook(Long id) {
		BookResponse bookResponse = modelMapper.map((bookRepo.findById(id)).get(), BookResponse.class);
		return bookResponse;
	}

	@Override
	public BookResponse addBook(BookRequest bookRequest) throws DoesNotExistException {
		Book book;
		Category category;
		List<Author> authors;

		if (bookRequest.getId() != null)
			book = bookRepo.findById(bookRequest.getId()).orElse(new Book());

		try {
			category = categoryRepo.findById(bookRequest.getCategoryId()).get();
		} catch (NoSuchElementException e) {
			throw new DoesNotExistException((bookRequest.getCategoryId()).toString(), "categoryId");
		}

		authors = bookRequest.getAuthorId().stream().map(aLong -> authorRepo.findById(aLong)
				.orElseThrow(() -> new DoesNotExistException(aLong.toString(), "AuthorId")))
				.collect(Collectors.toList());
		

		book = modelMapper.map(bookRequest, Book.class);

		book.setRating(0.0);
		
		book.setCategoryId(category);
		book.setAuthors(authors);

		BookResponse response = modelMapper.map(bookRepo.save(book), BookResponse.class);

		response.setCategory(modelMapper.map(category, CategoryResponse.class));

		List<AuthorResponse> authorResponses = authors.stream().map(e -> modelMapper.map(e, AuthorResponse.class))
				.collect(Collectors.toList());
		response.setAuthors(authorResponses);

		return response;
	}

	@Override
	public void removeBook(Long id) {
		bookRepo.delete(bookRepo.findById(id).get());

	}

}
