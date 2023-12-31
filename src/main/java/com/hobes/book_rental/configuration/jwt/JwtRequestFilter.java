package com.hobes.book_rental.configuration.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hobes.book_rental.service.CustomUserDetailsService;
import com.hobes.book_rental.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	
	private JwtUtil jwtUtil;
	private CustomUserDetailsService customUserDetailsService;
	
	
	public JwtRequestFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
		this.jwtUtil = jwtUtil;
		this.customUserDetailsService = customUserDetailsService;
	}
	

	private final RequestMatcher requestMatcher = new OrRequestMatcher(
			new AntPathRequestMatcher("/authenticate"),
	        new AntPathRequestMatcher("/addUser"),
	        new AntPathRequestMatcher("/forgot"),
	        new AntPathRequestMatcher("/reset"),
	        new AntPathRequestMatcher("/book"),
	        new AntPathRequestMatcher("/book/test"),
	        new AntPathRequestMatcher("/book/getTest")

			);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		System.out.println(this.requestMatcher.matches(request));
		if (!this.requestMatcher.matches(request)) {
			final String requestTokenHeader = request.getHeader("Authorization");

			System.out.println(requestTokenHeader);
			String email = null;
			String jwtToken = null;

			if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
				jwtToken = requestTokenHeader.substring(7);

				try {
					email = jwtUtil.getEmailFromToken(jwtToken);
				} catch (IllegalArgumentException e) {
					System.out.println("Unable to get JWT Token");
				} catch (ExpiredJwtException e) {
					System.out.println("Its here pani");
					System.out.println("JWT Token has expired");
				}
			} else {
				System.out.println("JWT token didn't start with Bearer");
			}

			if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

				if (jwtUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
