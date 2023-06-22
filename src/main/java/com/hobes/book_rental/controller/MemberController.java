package com.hobes.book_rental.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobes.book_rental.exception.AlreadyExistsException;
import com.hobes.book_rental.helper.ResponseHandler;
import com.hobes.book_rental.pojo.book.BookRequest;
import com.hobes.book_rental.pojo.book.BookResponse;
import com.hobes.book_rental.pojo.member.MemberRequest;
import com.hobes.book_rental.pojo.member.MemberResponse;
import com.hobes.book_rental.service.member.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/member")
public class MemberController {

	private MemberService memberService;

	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@PostMapping
	public ResponseEntity<Object> addUpdateMember(@Valid @RequestBody MemberRequest memberRequest)
			throws AlreadyExistsException {
		MemberResponse memberResponse = memberService.addMember(memberRequest);

		if (memberResponse == null) {
			return ResponseHandler.generateErrorResponse("Category already exists", HttpStatus.CONFLICT);
		}
		return ResponseHandler.generateResponse("Member Successfully added", HttpStatus.CREATED, memberResponse);
	}
	
	@GetMapping
	public ResponseEntity<Object> getMembers() {
			List<MemberResponse> memberResponses = memberService.getAllMembers();
			return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, memberResponses);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getBook(@PathVariable("id") Long id) {
		MemberResponse memberResponse = memberService.getSingleMember(id);
		return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, memberResponse);
	}
	
	@DeleteMapping
	public ResponseEntity<Object> removeBook(@RequestBody Long id) {
		memberService.removeMember(id);
		return ResponseHandler.generateRemoveResponse();
	}
	
	
}
