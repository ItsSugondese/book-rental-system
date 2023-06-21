package com.hobes.book_rental.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobes.book_rental.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Integer>{

}
