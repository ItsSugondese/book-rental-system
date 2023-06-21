package com.hobes.book_rental.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobes.book_rental.model.Book;

public interface BookRepo extends JpaRepository<Book, Integer> {

}
