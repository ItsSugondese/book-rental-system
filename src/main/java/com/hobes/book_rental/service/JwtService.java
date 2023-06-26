package com.hobes.book_rental.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.hobes.book_rental.model.Member;
import com.hobes.book_rental.pojo.jwt.JwtRequest;
import com.hobes.book_rental.pojo.jwt.JwtResponse;
import com.hobes.book_rental.pojo.member.MemberResponse;
import com.hobes.book_rental.repo.LoginDetailsRepo;
import com.hobes.book_rental.util.JwtUtil;

@Component
public class JwtService {

	
	private CustomUserDetailsService userDetailsService;
	private LoginDetailsRepo loginDetailsRepo;
	private JwtUtil jwtUtil;
	private AuthenticationManager authenticationManager;
	private ModelMapper modelMapper;

	public JwtService(CustomUserDetailsService userDetailsService, LoginDetailsRepo loginDetailsRepo, JwtUtil jwtUtil,
			AuthenticationManager authenticationManager, ModelMapper modelMapper) {
		this.userDetailsService = userDetailsService;
		this.loginDetailsRepo = loginDetailsRepo;
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
		this.modelMapper = modelMapper;
	}

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userEmail = jwtRequest.getUserEmail();
		String userPassword = jwtRequest.getUserPassword();

		authenticate(userEmail, userPassword);

		UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);

		Member user = (loginDetailsRepo.findLoginDetailsByEmail(userEmail)).getMemberId();

		JwtResponse response = new JwtResponse();
		
		
		response.setMember(modelMapper.map(user, MemberResponse.class));
		response.setJwtToken(newGeneratedToken);

		return response;
	}

	private void authenticate(String userEmail, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			System.out.println(e);
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
