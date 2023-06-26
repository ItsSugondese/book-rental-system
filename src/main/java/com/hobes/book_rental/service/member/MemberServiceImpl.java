package com.hobes.book_rental.service.member;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.hobes.book_rental.model.LoginDetails;
import com.hobes.book_rental.model.Member;
import com.hobes.book_rental.model.Role;
import com.hobes.book_rental.pojo.member.MemberRequest;
import com.hobes.book_rental.pojo.member.MemberResponse;
import com.hobes.book_rental.repo.LoginDetailsRepo;
import com.hobes.book_rental.repo.MemberRepo;
import com.hobes.book_rental.repo.RoleRepo;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberRepo memberRepo;
	private LoginDetailsRepo loginDetailsRepo;
	private RoleRepo roleRepo;
	
	private ModelMapper modelMapper;
	private PasswordEncoder passwordEncoder;
	

	public MemberServiceImpl(MemberRepo memberRepo, LoginDetailsRepo loginDetailsRepo, RoleRepo roleRepo, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
		this.memberRepo = memberRepo;
		this.loginDetailsRepo = loginDetailsRepo;
		this.roleRepo = roleRepo;
		
		this.modelMapper = modelMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<MemberResponse> getAllMembers() {
		List<MemberResponse> memberResponses = (memberRepo.findAll()).stream()
				.map(e -> modelMapper.map(e, MemberResponse.class)).collect(Collectors.toList());

		return memberResponses;
	}

	@Override
	public MemberResponse getSingleMember(Long id) {
		MemberResponse memberResponse = modelMapper.map((memberRepo.findById(id)).get(), MemberResponse.class);
		return memberResponse;
	}


	@Override
	public MemberResponse addMember(MemberRequest memberRequest) {
		Member member;

		if (memberRequest.getId() != null)
			member = memberRepo.findById(memberRequest.getId()).orElse(new Member());

		member = modelMapper.map(memberRequest, Member.class);
		
		
		
		//setting roles
		Role role = roleRepo.findById("User").get();
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		member.setRole(roles);
		
		member = memberRepo.save(member);
		
		loginDetailsRepo.save(new LoginDetails(memberRepo.save(member), passwordEncoder.encode(memberRequest.getPassword())));
		
		MemberResponse response = modelMapper.map(member, MemberResponse.class);

		return response;
	}

	@Override
	public void removeMember(Long id) {
		memberRepo.delete(memberRepo.findById(id).get());

	}

}
