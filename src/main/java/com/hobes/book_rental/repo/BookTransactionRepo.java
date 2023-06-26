package com.hobes.book_rental.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hobes.book_rental.model.BookTransaction;
import com.hobes.book_rental.model.Member;

public interface BookTransactionRepo extends JpaRepository<BookTransaction, Long> {

	Optional<BookTransaction> findById(Long id);
	
	@Query("SELECT id FROM BookTransaction ORDER BY id DESC LIMIT 1")
	Long getLastPrimaryKey();
	
	
	@Query("SELECT b FROM BookTransaction b WHERE b.rentStatus= 'RENT' AND b.activeClosed=true AND b.memberId=?1")
	BookTransaction findUserByStatus(Member member);
	
	@Query("SELECT b FROM BookTransaction b WHERE b.rentStatus= 'RENT' AND b.id=?1 AND b.code=?2 AND b.memberId=?3")
	BookTransaction findByIdAndCodeAndMemberId(Long id, String code, Member memberId);
	
	@Query("SELECT b FROM BookTransaction b WHERE b.rentStatus= 'RENT' AND (b.code=?2 AND b.memberId=?3) OR (b.id=?1 AND b.memberId=?3)")
	BookTransaction findByAnyTwo(Long id, String code, Member memberId);
	
	@Query("SELECT b FROM BookTransaction b WHERE b.rentStatus= 'RENT' AND b.id=?1 AND b.code=?2")
	BookTransaction findByIdAndCode(Long id, String code);
	
	@Query("SELECT b FROM BookTransaction b WHERE b.rentStatus= 'RENT' AND b.memberId=?1")
	BookTransaction findByMemberId(Member memberId);
	
	@Query("SELECT b FROM BookTransaction b WHERE b.id= ?1 OR b.code=?2")
	BookTransaction findByIdOrCode(Long id, String code);
	
	@Query("SELECT b FROM BookTransaction b WHERE b.memberId= ?1")
	List<BookTransaction> findAllTransactionOfMember(Member memberId);
}

