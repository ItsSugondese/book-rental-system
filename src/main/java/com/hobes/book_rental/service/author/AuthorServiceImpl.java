package com.hobes.book_rental.service.author;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.hobes.book_rental.model.Author;
import com.hobes.book_rental.pojo.author.AuthorRequest;
import com.hobes.book_rental.pojo.author.AuthorResponse;
import com.hobes.book_rental.repo.AuthorRepo;

@Service
public class AuthorServiceImpl implements AuthorService {

	private AuthorRepo authorRepo;
	private ModelMapper modelMapper;

	public AuthorServiceImpl(AuthorRepo authorRepo, ModelMapper modelMapper) {
		this.authorRepo = authorRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<AuthorResponse> getAllAuthor() {
		List<AuthorResponse> authorResponses = (authorRepo.findAll()).stream()
				.map(e -> modelMapper.map(e, AuthorResponse.class)).collect(Collectors.toList());

		return authorResponses;
	}

	@Override
	public AuthorResponse getSingleAuthor(Long id) {
		AuthorResponse authorResponse = modelMapper.map((authorRepo.getReferenceById(id)).get(), AuthorResponse.class);
		return authorResponse;
	}

	@Override
	public AuthorResponse addAuthor(AuthorRequest authorRequest) {
		Author author;

		if (authorRequest.getId() != null)
			author = authorRepo.getReferenceById(authorRequest.getId()).orElse(new Author());

		author = modelMapper.map(authorRequest, Author.class);

		AuthorResponse response = modelMapper.map(authorRepo.save(author), AuthorResponse.class);

		return response;
	}

	@Override
	public void removeAuthor(Long id) {
		authorRepo.delete(authorRepo.getReferenceById(id).get());
	}
}
