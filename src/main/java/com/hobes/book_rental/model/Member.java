package com.hobes.book_rental.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "tbl_user_role",
			joinColumns = {
					@JoinColumn(name="memberId",
							referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_tbl_user_role_tbl_member"))
			},
			inverseJoinColumns = {
					@JoinColumn(name="roleId",
							referencedColumnName = "roleName", foreignKey = @ForeignKey(name = "fk_tbl_user_role_tbl_role"))
			}
			)
	private Set<Role> role;
}
