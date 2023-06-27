package com.hobes.book_rental.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hobes.book_rental.exception.DoesNotExistException;
import com.hobes.book_rental.helper.ResponseHandler;
import com.hobes.book_rental.pojo.book.BookRequest;
import com.hobes.book_rental.pojo.book.BookResponse;
import com.hobes.book_rental.service.book.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping
	public ResponseEntity<Object> addUpdateBook(@RequestParam("pic") MultipartFile uploadedFile,
			@RequestParam("data") String bookDetails) throws IllegalStateException, IOException {

		BookResponse bookResponse = bookService.addBook(bookDetails, uploadedFile );
	

		if (bookResponse == null) {
			return ResponseHandler.generateErrorResponse("Book already exists", HttpStatus.CONFLICT);
		}
		return ResponseHandler.generateResponse("Book Successfully added", HttpStatus.CREATED, bookResponse);
	}

	@GetMapping
	public ResponseEntity<Object> getBooks() throws FileNotFoundException, IOException {
		List<BookResponse> bookResponse = bookService.getAllBooks();
		return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, bookResponse);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getBook(@PathVariable("id") Long id) {
		BookResponse bookResponse = bookService.getSingleBook(id);
		return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, bookResponse);
	}

	@DeleteMapping
	public ResponseEntity<Object> removeBook(@RequestBody Long id) {
		bookService.removeBook(id);
		return ResponseHandler.generateRemoveResponse();
	}

	@PostMapping("/test")
	public String formData(@RequestParam("pic") List<MultipartFile> uploadedFile,
			@RequestParam("data") String bookDetails) throws IllegalStateException, IOException {

		if (uploadedFile.size() == 0) {
			for (MultipartFile multipartFile : uploadedFile) {
				if (multipartFile.getSize() == 0)
					throw new DoesNotExistException();
			}
		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		BookRequest book = objectMapper.readValue(bookDetails, BookRequest.class);
		System.out.println(book);
		System.out.println(uploadedFile);

		File fileSaveDir = new File("C:\\Users\\Admin\\Desktop\\testPic");
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}

		for (MultipartFile file : uploadedFile)
			file.transferTo(
					new File(fileSaveDir + File.separator + getRegisterFileName(file, (book.getIsbn()).toString())));

		return "working??";
	}

	@GetMapping(value = "/getTest", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	public @ResponseBody byte[] getImages() throws IOException {
		String filePath = "C:\\Users\\Admin\\Desktop\\testPic" + File.separator
				+ "545635-923c4d16-907a-4f84-95fc-8a8b0fb0e33c.png";
		File file = new File(filePath);
		InputStream inputStream = new FileInputStream(file);
		return IOUtils.toByteArray(inputStream);
	}

	private String getRegisterFileName(MultipartFile part, String constantVar) {
		String fileName = part.getOriginalFilename();
		return constantVar + "-" + UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."), fileName.length());

	}

}
