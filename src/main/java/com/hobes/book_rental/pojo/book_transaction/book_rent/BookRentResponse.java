package com.hobes.book_rental.pojo.book_transaction.book_rent;

import java.time.LocalDate;

import com.hobes.book_rental.pojo.book.BookResponse;
import com.hobes.book_rental.pojo.member.MemberResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookRentResponse {


	private String code;
	
	private BookResponse book;
	
	private LocalDate fromDate;
	
	private LocalDate toDate;
	
	private MemberResponse member;
	
}
