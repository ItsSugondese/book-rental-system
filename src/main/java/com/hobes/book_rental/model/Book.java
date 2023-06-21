package com.hobes.book_rental.model;



import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_book")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotEmpty(message = "{book.name}")
	@Column(columnDefinition = "VARCHAR(100)")
	private String name;
	
//	@NotEmpty(message = "{book.noOfPages}")
	private int noOfPages;
	
//	@NotEmpty(message = "{book.isbn}")
	@Column(columnDefinition = "VARCHAR(30)")
	private Integer isbn;
	
	@Value("0.0")
	private Double rating;
	
//	@NotEmpty(message = "{book.stockCount}")
	private Integer stockCount;
	
//	@NotEmpty(message = "{book.publishedDate}")
	private Date publishedDate;
	
	
//	@NotEmpty(message = "{book.photo}")
	@Column(columnDefinition = "VARCHAR(200)")
	private String photo;
	
	@JoinColumn(name = "category_id")
	@ManyToOne(targetEntity = Book.class)
//	@NotEmpty(message = "{book.category}")
	private Category categoryId;
	
	

	
}
