package com.hobes.book_rental.pojo.jwt;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class JwtRequest {

	@NotEmpty(message = "{jwtRequest.userEmail}")
	private String userEmail;
	
	@NotEmpty(message = "{jwtRequest.userPassword}")
	private String userPassword;
}
