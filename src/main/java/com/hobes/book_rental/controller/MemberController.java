package com.hobes.book_rental.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hobes.book_rental.model.Member;
import com.hobes.book_rental.service.MemberService;

import jakarta.validation.Valid;

@RestController
public class MemberController {


	private MemberService memberService;
	
	@PostMapping("/add_member")
	public  ResponseEntity<Member> addMbr(@Valid @RequestBody Member mbr) {
		Member member = this.memberService.addMember(mbr);
		
		if(member == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.of(Optional.of(member));
	}
}
