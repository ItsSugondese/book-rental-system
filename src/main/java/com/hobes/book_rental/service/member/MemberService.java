package com.hobes.book_rental.service.member;

import java.util.List;

import com.hobes.book_rental.model.Member;
import com.hobes.book_rental.pojo.book.BookRequest;
import com.hobes.book_rental.pojo.book.BookResponse;
import com.hobes.book_rental.pojo.member.MemberRequest;
import com.hobes.book_rental.pojo.member.MemberResponse;

public interface MemberService {

	List<MemberResponse> getAllMembers();

	MemberResponse getSingleMember(Long id);

	MemberResponse addMember(MemberRequest memberRequest);
	
	void removeMember(Long id);
	
}
