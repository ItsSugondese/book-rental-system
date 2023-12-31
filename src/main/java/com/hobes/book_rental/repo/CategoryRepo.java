package com.hobes.book_rental.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobes.book_rental.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>{

	Optional<Category> findById(Long id);
	
}
