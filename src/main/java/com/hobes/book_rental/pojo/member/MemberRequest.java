package com.hobes.book_rental.pojo.member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberRequest {

	private Long id;
	
	@Email(message = "{member.email}")
	private String email;
	
	@NotEmpty(message = "{member.name}")
	private String name;
	

	@Pattern(regexp = "^9\\d{9}$" ,message = "{member.mobileNumber}")
	private String mobileNumber;
	
	@NotEmpty(message="{member.address}")
	private String address;
}
