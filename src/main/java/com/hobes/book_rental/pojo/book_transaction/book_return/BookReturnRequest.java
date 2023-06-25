package com.hobes.book_rental.pojo.book_transaction.book_return;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookReturnRequest {

	
	private Long id;
	
	private String code;
	
	private Long memberId;
}
