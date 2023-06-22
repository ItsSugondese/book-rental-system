package com.hobes.book_rental.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobes.book_rental.model.BookTransaction;

public interface BookTransactionRepo extends JpaRepository<BookTransaction, Integer> {

	Optional<BookTransaction> getReferenceById(Long id);
}
