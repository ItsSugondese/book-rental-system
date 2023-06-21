package com.hobes.book_rental.model;

import java.util.Date;

import com.hobes.book_rental.enums.RentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tbl_book_transaction")
@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name = "book_id")
	@ManyToOne(targetEntity = BookTransaction.class)
	private Book bookId;
	
	private Integer code;
	
	@Column(name = "from_date")
	private Date fromDate;
	
	@Column(name = "to_date")
	private Date toDate;
	
	@Column(name = "rent_status")
	@Enumerated(EnumType.STRING)
	private RentType rentStatus;
	
	@JoinColumn(name = "member_id")
	@ManyToOne(targetEntity = BookTransaction.class)
	private Member memberId;
	
	@Column(name = "active_closed")
	private Boolean activeClosed;
}
