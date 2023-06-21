package com.hobes.book_rental.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_author")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotEmpty(message = "{author.name}")
	private String name;
	
//	@Email(message = "{author.email}")
	private String email;
	
//	@Pattern(regexp = "^9\\d{9}$" ,message = "{author.mobileNumber}")
	private String mobileNumber;
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "tbl_book_author",
			joinColumns = {
					@JoinColumn(name="book_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name="author_id")
			}
			)
	private List<Book> books;
}
