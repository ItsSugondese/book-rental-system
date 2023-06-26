package com.hobes.book_rental.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hobes.book_rental.pojo.jwt.JwtRequest;
import com.hobes.book_rental.service.PasswordService;

import jakarta.servlet.http.HttpServletRequest;

@RestController()
public class EmailController {

	private PasswordService passwordService;

	public EmailController(PasswordService passwordService) {
		this.passwordService = passwordService;
	}

	@PostMapping("/forgot")
	public String sendingMail(@RequestBody JwtRequest jwtRequest) {
		return passwordService.forgotPassword(jwtRequest);
	}
	
	@GetMapping("/reset")
	public String check(HttpServletRequest request) {
		return passwordService.resetingPassword(request);
	}
}
