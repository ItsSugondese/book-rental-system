package com.hobes.book_rental.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.model.LoginDetails;
import com.hobes.book_rental.model.Member;
import com.hobes.book_rental.repo.LoginDetailsRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private LoginDetailsRepo loginDetailsRepo;
	
	
	@Autowired
	public CustomUserDetailsService(LoginDetailsRepo loginDetailsRepo) {
		this.loginDetailsRepo = loginDetailsRepo;
	}

	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LoginDetails loginDetails = loginDetailsRepo.findLoginDetailsByEmail(email);
		
		Member user = loginDetails.getMemberId();

		
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getEmail(), loginDetails.getPassword(),
					getAuthority(user));
		} else {
			throw new UsernameNotFoundException("User with the email '" + email + "' doesn't exist");
		}
	}

	private Set<SimpleGrantedAuthority> getAuthority(Member member) {
		Set<SimpleGrantedAuthority> authorities = new HashSet();
		member.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});

		return authorities;
	}



}
