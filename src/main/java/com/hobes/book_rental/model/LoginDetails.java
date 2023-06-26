package com.hobes.book_rental.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_login_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "member_Id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tbl_login_details_tbl_member"))
	@OneToOne
	private Member memberId;

	private String password;

	public LoginDetails(Member memberId, String password) {
		this.memberId = memberId;
		this.password = password;
	}
}
