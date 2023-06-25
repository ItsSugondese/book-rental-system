package com.hobes.book_rental.pojo.book_transaction.book_return;

import com.hobes.book_rental.pojo.book.BookResponse;
import com.hobes.book_rental.pojo.member.MemberResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookReturnResponse {

	private MemberResponse member;
	
	private BookResponse book;
	
}
