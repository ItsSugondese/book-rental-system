package com.hobes.book_rental.model;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.hobes.book_rental.enums.RentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tbl_book_transaction", uniqueConstraints = {
		@UniqueConstraint(columnNames = "code", name = "UNIQUE_tbl_book_transaction_code") })
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "book_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tbl_book_transaction_tbl_book"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Book bookId;

	@UuidGenerator
	private UUID code;

	@Column(name = "from_date")
	private Date fromDate;

	@Column(name = "to_date")
	private Date toDate;

	@Column(name = "rent_status")
	private RentType rentStatus;

	@JoinColumn(name = "member_id", 
			referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tbl_book_transaction_tbl_member"))
	@ManyToOne(fetch = FetchType.LAZY)
	private Member memberId;

	@Column(name = "active_closed")
	private Boolean activeClosed;
}
