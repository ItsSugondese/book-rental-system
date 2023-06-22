package com.hobes.book_rental.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobes.book_rental.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer>{

	Optional<Author> getReferenceById(Long id);
}
