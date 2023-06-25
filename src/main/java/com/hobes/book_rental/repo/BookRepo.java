package com.hobes.book_rental.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobes.book_rental.model.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

	Optional<Book> findById(Long id);
}
