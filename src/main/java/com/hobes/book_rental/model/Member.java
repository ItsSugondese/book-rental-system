package com.hobes.book_rental.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_member")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Email(message = "{member.email}")
	private String email;
	
	@NotEmpty(message = "{member.name}")
	private String name;
	
	@Column(name = "mobile_no")
	@Pattern(regexp = "^9\\d{9}$" ,message = "{member.mobileNumber}")
	private String mobileNumber;
	
	@NotEmpty(message="{member.address}")
	private String address;
}
