package com.hobes.book_rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_role")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Role {

	@Id
	private String roleName;
}
