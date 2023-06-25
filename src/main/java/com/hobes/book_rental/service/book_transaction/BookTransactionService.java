package com.hobes.book_rental.service.book_transaction;

import java.util.List;

import com.hobes.book_rental.exception.DoesNotExistException;
import com.hobes.book_rental.pojo.book_transaction.BookTransactionResponse;
import com.hobes.book_rental.pojo.book_transaction.book_rent.BookRentRequest;
import com.hobes.book_rental.pojo.book_transaction.book_rent.BookRentResponse;
import com.hobes.book_rental.pojo.book_transaction.book_return.BookReturnRequest;
import com.hobes.book_rental.pojo.book_transaction.book_return.BookReturnResponse;

public interface BookTransactionService {

	List<BookTransactionResponse> getAllBookTransactions();

	BookTransactionResponse getSingleBookTransaction(Long id);

	BookRentResponse addBookTransaction(BookRentRequest bookRentRequest) throws DoesNotExistException;
	
	BookReturnResponse returnBookTransaction(BookReturnRequest bookReturnRequest);
	
	void removeBookTransaction(Long id);
	
}
