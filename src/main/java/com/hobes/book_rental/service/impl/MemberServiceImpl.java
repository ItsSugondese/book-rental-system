package com.hobes.book_rental.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.model.Member;
import com.hobes.book_rental.repo.MemberRepo;
import com.hobes.book_rental.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepo memberRepo;
	
	@Override
	public Member addMember(Member member) {
		Member mbr = null;
		

		if (this.memberRepo.findAll().size() > 0) {
			if(this.memberRepo.findAll().stream().filter(e -> e.getEmail().equals(member.getEmail())).findAny().isPresent());
				mbr = this.memberRepo.findAll().stream().filter(e -> e.getEmail().equals(member.getEmail())).findAny().get();
		}
		
		if (mbr != null) {
			return null;
		}
		
		
		this.memberRepo.save(member);
		return member;
	}
}
