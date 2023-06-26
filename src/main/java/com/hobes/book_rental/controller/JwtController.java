package com.hobes.book_rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hobes.book_rental.pojo.jwt.JwtRequest;
import com.hobes.book_rental.pojo.jwt.JwtResponse;
import com.hobes.book_rental.service.JwtService;



@RestController
@CrossOrigin
public class JwtController {

	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/authenticate")
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {		
        return jwtService.createJwtToken(jwtRequest);
    }
}
