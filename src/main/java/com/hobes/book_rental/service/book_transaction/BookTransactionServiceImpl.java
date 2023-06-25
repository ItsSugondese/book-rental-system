package com.hobes.book_rental.service.book_transaction;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.enums.RentType;
import com.hobes.book_rental.exception.BookStockException;
import com.hobes.book_rental.exception.BookReturnTransactionException;
import com.hobes.book_rental.exception.DoesNotExistException;
import com.hobes.book_rental.model.Author;
import com.hobes.book_rental.model.Book;
import com.hobes.book_rental.model.BookTransaction;
import com.hobes.book_rental.model.Category;
import com.hobes.book_rental.model.Member;
import com.hobes.book_rental.pojo.author.AuthorResponse;
import com.hobes.book_rental.pojo.book.BookRequest;
import com.hobes.book_rental.pojo.book.BookResponse;
import com.hobes.book_rental.pojo.book_transaction.BookTransactionResponse;
import com.hobes.book_rental.pojo.book_transaction.book_rent.BookRentRequest;
import com.hobes.book_rental.pojo.book_transaction.book_rent.BookRentResponse;
import com.hobes.book_rental.pojo.book_transaction.book_return.BookReturnRequest;
import com.hobes.book_rental.pojo.book_transaction.book_return.BookReturnResponse;
import com.hobes.book_rental.pojo.category.CategoryResponse;
import com.hobes.book_rental.pojo.member.MemberResponse;
import com.hobes.book_rental.repo.AuthorRepo;
import com.hobes.book_rental.repo.BookRepo;
import com.hobes.book_rental.repo.BookTransactionRepo;
import com.hobes.book_rental.repo.MemberRepo;

@Service
public class BookTransactionServiceImpl implements BookTransactionService {

	private BookTransactionRepo bookTransactionRepo;
	private ModelMapper modelMapper;
	private BookRepo bookRepo;
	private MemberRepo memberRepo;

	public BookTransactionServiceImpl(BookTransactionRepo bookTransactionRepo, ModelMapper modelMapper,
			BookRepo bookRepo, MemberRepo memberRepo, AuthorRepo authorRepo) {
		this.bookTransactionRepo = bookTransactionRepo;
		this.modelMapper = modelMapper;
		this.bookRepo = bookRepo;
		this.memberRepo = memberRepo;
	}

	@Override
	public List<BookTransactionResponse> getAllBookTransactions() {
		List<BookTransactionResponse> bookTransactionResponses = (bookTransactionRepo.findAll()).stream()
				.map(e -> modelMapper.map(e, BookTransactionResponse.class)).collect(Collectors.toList());

		return bookTransactionResponses;
	}

	@Override
	public BookTransactionResponse getSingleBookTransaction(Long id) {
		BookTransactionResponse bookTransactionResponse = modelMapper.map((bookTransactionRepo.findById(id)).get(),
				BookTransactionResponse.class);
		return bookTransactionResponse;
	}

	@Override
	public BookRentResponse addBookTransaction(BookRentRequest bookRentRequest) throws DoesNotExistException {

		BookTransaction bookTransaction = new BookTransaction();
		Book book;
		Member member;

		if (bookRentRequest.getId() != null)
			bookTransaction = bookTransactionRepo.findById(bookRentRequest.getId()).orElse(bookTransaction);

		try {
			book = bookRepo.findById(bookRentRequest.getBookId()).get();
		} catch (NoSuchElementException e) {
			throw new DoesNotExistException((bookRentRequest.getBookId()).toString(), "bookId");
		}

		try {
			member = memberRepo.findById(bookRentRequest.getMemberId()).get();
		} catch (NoSuchElementException e) {
			throw new DoesNotExistException((bookRentRequest.getMemberId()).toString(), "memberId");
		}

		if (bookTransaction.getId() == null) {
			Long val = bookTransactionRepo.getLastPrimaryKey();
			bookTransaction.setCode(book.getName() + "_" + member.getName() + "_" + ((val != null) ? val + 1 : 1));
		} else {
			bookTransaction.setCode(bookTransaction.getCode());
			bookTransaction.setId(bookRentRequest.getId());
		}

		bookTransaction.setFromDate(bookRentRequest.getFromDate());
		bookTransaction.setToDate(bookRentRequest.getFromDate().plusDays(bookRentRequest.getNoOfDays()));
		bookTransaction.setRentStatus(RentType.RENT);
		bookTransaction.setActiveClosed(true);

		book.setStockCount(book.getStockCount() - 1);

		// updating book count
		if (book.getStockCount() < 0)
			throw new BookStockException();
		else
			bookRepo.save(book);

		bookTransaction.setBookId(book);
		bookTransaction.setMemberId(member);

		bookTransactionRepo.findUserByStatus(member);

		BookRentResponse response = modelMapper.map(bookTransactionRepo.save(bookTransaction), BookRentResponse.class);

		BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
		List<AuthorResponse> authorResponses = (book.getAuthors()).stream()
				.map(e -> modelMapper.map(e, AuthorResponse.class)).collect(Collectors.toList());
		bookResponse.setAuthors(authorResponses);
		response.setBook(bookResponse);

		response.setMember(modelMapper.map(bookTransaction.getMemberId(), MemberResponse.class));

		return response;
	}

	@Override
	public BookReturnResponse returnBookTransaction(BookReturnRequest bookReturnRequest) {
		BookTransaction bookTransaction = new BookTransaction();
		Book book;
		Member member;

		if (bookReturnRequest.getId() != null && bookReturnRequest.getCode() != null
				&& bookReturnRequest.getMemberId() != null) {

			member = getMember(bookReturnRequest.getMemberId());

			bookTransaction = bookTransactionRepo.findByIdAndCodeAndMemberId(bookReturnRequest.getId(),
					bookReturnRequest.getCode(), member);
		
		}

		else if ((bookReturnRequest.getId() != null && bookReturnRequest.getMemberId() != null)
				|| (bookReturnRequest.getCode() != null && bookReturnRequest.getMemberId() != null)) {
			
			member = getMember(bookReturnRequest.getMemberId());

			bookTransaction = bookTransactionRepo.findByAnyTwo(bookReturnRequest.getId(), bookReturnRequest.getCode(),
					member);
			
		}

		else if (bookReturnRequest.getId() != null && bookReturnRequest.getCode() != null) {
			bookTransaction = bookTransactionRepo.findByIdAndCode(bookReturnRequest.getId(),
					bookReturnRequest.getCode());
			System.out.println("Inside here");
	
		}
		else if(bookReturnRequest.getMemberId() != null) {
			member = getMember(bookReturnRequest.getMemberId());
			
			bookTransaction = bookTransactionRepo.findByMemberId(member);
		}else if(bookReturnRequest.getId() != null || bookReturnRequest.getCode() != null) {
			bookTransaction = bookTransactionRepo.findByIdOrCode(bookReturnRequest.getId(), bookReturnRequest.getCode());
			
	
		}
		
		if(bookTransaction == null)
			throw new BookReturnTransactionException();
		
		book = bookTransaction.getBookId();
		book.setStockCount(book.getStockCount() + 1);
		bookRepo.save(book);
		
		bookTransaction.setActiveClosed(false);
		bookTransaction.setRentStatus(RentType.RETURN);

		bookTransactionRepo.save(bookTransaction);
		
		
		BookReturnResponse response = new BookReturnResponse();
		
		
		BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
		List<AuthorResponse> authorResponses = (book.getAuthors()).stream()
				.map(e -> modelMapper.map(e, AuthorResponse.class)).collect(Collectors.toList());
		bookResponse.setAuthors(authorResponses);
		response.setBook(bookResponse);

		response.setMember(modelMapper.map(bookTransaction.getMemberId(), MemberResponse.class));
		
		return response;
	}

	@Override
	public void removeBookTransaction(Long id) {
		bookTransactionRepo.delete(bookTransactionRepo.findById(id).get());

	}

	
	private Member getMember(Long id) {
		
		try {
			return memberRepo.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new DoesNotExistException(id.toString(), "memberId");
		}
		
		
	}
}
