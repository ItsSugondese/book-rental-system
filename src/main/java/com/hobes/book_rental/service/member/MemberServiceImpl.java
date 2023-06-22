package com.hobes.book_rental.service.member;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.model.Member;
import com.hobes.book_rental.pojo.member.MemberRequest;
import com.hobes.book_rental.pojo.member.MemberResponse;
import com.hobes.book_rental.repo.MemberRepo;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberRepo memberRepo;
	private ModelMapper modelMapper;

	public MemberServiceImpl(MemberRepo memberRepo, ModelMapper modelMapper) {
		this.memberRepo = memberRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<MemberResponse> getAllMembers() {
		List<MemberResponse> memberResponses = (memberRepo.findAll()).stream()
				.map(e -> modelMapper.map(e, MemberResponse.class)).collect(Collectors.toList());

		return memberResponses;
	}

	@Override
	public MemberResponse getSingleMember(Long id) {
		MemberResponse memberResponse = modelMapper.map((memberRepo.getReferenceById(id)).get(), MemberResponse.class);
		return memberResponse;
	}


	@Override
	public MemberResponse addMember(MemberRequest memberRequest) {
		Member member;

		if (memberRequest.getId() != null)
			member = memberRepo.getReferenceById(memberRequest.getId()).orElse(new Member());

		member = modelMapper.map(memberRequest, Member.class);
		
		MemberResponse response = modelMapper.map(memberRepo.save(member), MemberResponse.class);

		return response;
	}

	@Override
	public void removeMember(Long id) {
		memberRepo.delete(memberRepo.getReferenceById(id).get());

	}

}
