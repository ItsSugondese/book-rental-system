package com.hobes.book_rental.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobes.book_rental.model.Member;


public interface MemberRepo extends JpaRepository<Member, Long>{

	Optional<Member> findById(Long id);
}
