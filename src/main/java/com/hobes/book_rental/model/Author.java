package com.hobes.book_rental.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_author", uniqueConstraints = {
		@UniqueConstraint(columnNames = "email", name = "UNIQUE_tbl_author_email"),
		@UniqueConstraint(columnNames = "mobile_number", name = "UNIQUE_tbl_author_mobile_number")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(unique = true)
	private String email;

	@Column(name = "mobile_number")
	private String mobileNumber;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_book_author", joinColumns = {
			@JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tbl_book_author_tbl_book")) }, inverseJoinColumns = {
					@JoinColumn(name = "author_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tbl_book_author_tbl_author")) })
	private List<Book> books;
}
