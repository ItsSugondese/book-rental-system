package com.hobes.book_rental.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hobes.book_rental.model.Role;

public interface RoleRepo extends JpaRepository<Role, String> {

}
