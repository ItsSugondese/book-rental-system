package com.hobes.book_rental;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hobes.book_rental.model.LoginDetails;
import com.hobes.book_rental.model.Member;
import com.hobes.book_rental.model.Role;
import com.hobes.book_rental.repo.LoginDetailsRepo;
import com.hobes.book_rental.repo.MemberRepo;
import com.hobes.book_rental.repo.RoleRepo;

@SpringBootApplication
public class BookRentalApplication implements CommandLineRunner {

	private RoleRepo roleRepo;
	private MemberRepo memberRepo;
	private LoginDetailsRepo loginDetailsRepo;
	private PasswordEncoder passwordEncoder;
	
	
	String adminEmail = "admin@admin.com";
	public BookRentalApplication(RoleRepo roleRepo, MemberRepo memberRepo, LoginDetailsRepo loginDetailsRepo,PasswordEncoder passwordEncoder) {
		this.roleRepo = roleRepo;
		this.memberRepo = memberRepo;
		this.loginDetailsRepo = loginDetailsRepo;
		this.passwordEncoder = passwordEncoder;
	}
	public static void main(String[] args) {
		SpringApplication.run(BookRentalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if(loginDetailsRepo.findMemberByEmail(adminEmail) == null) {
		Member member = new Member();
		member.setEmail("admin@admin.com");
		member.setAddress("Hobes");
		member.setMobileNumber("9868389356");
		member.setName("Admin");
		

		Set<Role> roles = new HashSet<>();

		Role role = new Role();
		role.setRoleName("Admin");
		role.setRoleName("User");
		roleRepo.save(role);

		roles.add(role);

		member.setRole(roles);
		
		
		 loginDetailsRepo.save(new LoginDetails(memberRepo.save(member), passwordEncoder.encode("admin")));
		}
	}

}
