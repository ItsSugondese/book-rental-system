package com.hobes.book_rental.service;

import java.util.NoSuchElementException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.exception.DoesNotExistException;
import com.hobes.book_rental.model.LoginDetails;
import com.hobes.book_rental.model.Member;
import com.hobes.book_rental.pojo.jwt.JwtRequest;
import com.hobes.book_rental.repo.LoginDetailsRepo;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PasswordService {

	private EmailSenderService emailSenderService;
	private LoginDetailsRepo loginDetailsRepo;
	
	private PasswordEncoder passwordEncoder;
	
	public PasswordService(EmailSenderService emailSenderService, LoginDetailsRepo loginDetailsRepo, PasswordEncoder passwordEncoder) {
		this.emailSenderService = emailSenderService;
		this.loginDetailsRepo = loginDetailsRepo;
		this.passwordEncoder = passwordEncoder;
	}
	
	public String forgotPassword(JwtRequest jwtRequest) {
		try {
			Member member = loginDetailsRepo.findMemberByEmail(jwtRequest.getUserEmail());
		} catch (NoSuchElementException e) {
			throw new DoesNotExistException((jwtRequest.getUserEmail()).toString(), "password");
		}
		String url = "http://localhost:9192/reset?email="+ jwtRequest.getUserEmail() + "&password=" + jwtRequest.getUserPassword();
		
		emailSenderService.sendEmail(jwtRequest.getUserEmail(), "Password Change Request", url);
		return "The request to change the password has been send to email : " + jwtRequest.getUserEmail();
	}
	
	public String resetingPassword(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		LoginDetails loginDetails = loginDetailsRepo.findLoginDetailsByEmail(email);
		loginDetails.setPassword(passwordEncoder.encode(password));
		
		loginDetailsRepo.save(loginDetails);
		return "Password Updated";
	}
}
