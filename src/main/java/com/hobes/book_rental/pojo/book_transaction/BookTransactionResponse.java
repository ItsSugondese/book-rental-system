package com.hobes.book_rental.pojo.book_transaction;

import java.util.Date;

import com.hobes.book_rental.enums.RentType;
import com.hobes.book_rental.pojo.book.BookResponse;
import com.hobes.book_rental.pojo.member.MemberResponse;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookTransactionResponse {

	private BookResponse book;

	private Integer code;

	private Date fromDate;

	private Date toDate;

	@Enumerated(EnumType.STRING)
	private RentType rentStatus;

	private MemberResponse member;
}
