package com.hobes.book_rental.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_member", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email", name = "UNIQUE_tbl_member_email"),
		@UniqueConstraint(columnNames = "mobile_number", name = "UNIQUE_tbl_member_mobile_number")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String email;

	private String name;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@NotEmpty(message = "{member.address}")
	private String address;
}
