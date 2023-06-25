package com.hobes.book_rental.pojo.book_transaction.book_rent;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRentRequest {

	private Long id;
	
	@NotNull(message = "{bookTransaction.bookRent.bookId}")
	private Long bookId;
	
	private LocalDate fromDate;
	
	
	@NotNull(message = "{bookTransaction.bookRent.noOfDays}")
	private Integer noOfDays;
	
	
	@NotNull(message = "{bookTransaction.bookRent.memberId}")
	private Long memberId;
	
}
