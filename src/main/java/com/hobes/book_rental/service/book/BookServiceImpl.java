package com.hobes.book_rental.service.book;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
	public List<BookResponse> getAllBooks() throws FileNotFoundException, IOException {

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
	public BookResponse addBook(String data, MultipartFile file) throws IllegalStateException, IOException {
		Book book;
		Category category;
		List<Author> authors;
		
		if (file.getSize() == 0) {
			throw new FileNotFoundException("File not found");
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		BookRequest bookRequest = objectMapper.readValue(data, BookRequest.class);

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
		
		
		
		//images 
		File fileSaveDir = new File("C:\\Users\\Admin\\Desktop\\testPic");
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}

		String imageDirectory = fileSaveDir + File.separator + getRegisterFileName(file, (bookRequest.getIsbn()).toString());
			
			
		bookRequest.setPhoto(imageDirectory);
		
		
		
		

		book = modelMapper.map(bookRequest, Book.class);

		book.setRating(0.0);
		
		book.setCategoryId(category);
		book.setAuthors(authors);

		BookResponse response = modelMapper.map(bookRepo.save(book), BookResponse.class);

		//uploading file to the path
		file.transferTo(new File(imageDirectory));
		
		
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

	
	private String getRegisterFileName(MultipartFile part, String constantVar) {
		String fileName = part.getOriginalFilename();
		return constantVar + "-" + UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."), fileName.length());

	}
}
