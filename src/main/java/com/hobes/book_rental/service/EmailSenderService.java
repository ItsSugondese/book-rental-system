package com.hobes.book_rental.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	private JavaMailSender javaMailSender;

	public EmailSenderService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendEmail(String to, String header, String content) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom("Rokhan");
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(header);
		simpleMailMessage.setText(content);
		
		javaMailSender.send(simpleMailMessage);
	}
}
