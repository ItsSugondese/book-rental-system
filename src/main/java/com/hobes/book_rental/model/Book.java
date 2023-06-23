package com.hobes.book_rental.model;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_book", uniqueConstraints = {
		@UniqueConstraint(columnNames = "isbn", name = "UNIQUE_tbl_book_isbn") })
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "VARCHAR(100)")
	private String name;

	private Integer noOfPages;

	@Column(columnDefinition = "VARCHAR(30)")
	private Integer isbn;

	@Value("0.0")
	private Double rating;

	private Integer stockCount;

	private Date publishedDate;

	@Column(columnDefinition = "VARCHAR(200)")
	private String photo;

	@JoinColumn(name = "category_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tbl_book_tbl_category"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Category categoryId;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_book_author", joinColumns = {
			@JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tbl_book_author_tbl_book")) }, inverseJoinColumns = {
					@JoinColumn(name = "author_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tbl_book_author_tbl_author")) })
	private List<Author> authors;

}
