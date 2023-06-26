package com.hobes.book_rental.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hobes.book_rental.model.LoginDetails;
import com.hobes.book_rental.model.Member;

public interface LoginDetailsRepo extends JpaRepository<LoginDetails, Long> {
	
	@Query("SELECT memberId FROM LoginDetails ld WHERE ld.memberId.email = ?1")
	Member findMemberByEmail(String email);
	
	@Query("SELECT ld FROM LoginDetails ld WHERE ld.memberId.email = ?1")
	LoginDetails findLoginDetailsByEmail(String email);
	

}
