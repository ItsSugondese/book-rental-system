package com.hobes.book_rental.pojo.jwt;

import com.hobes.book_rental.model.LoginDetails;
import com.hobes.book_rental.pojo.member.MemberResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {

	private MemberResponse member;
	private String jwtToken;
}