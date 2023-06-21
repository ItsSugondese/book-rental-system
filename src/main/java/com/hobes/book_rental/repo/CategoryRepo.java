package com.hobes.book_rental.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobes.book_rental.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
