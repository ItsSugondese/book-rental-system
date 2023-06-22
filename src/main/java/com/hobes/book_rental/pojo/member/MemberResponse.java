package com.hobes.book_rental.pojo.member;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MemberResponse {

	private String email;

	private String name;

	private String mobileNumber;

	private String address;
}
