package com.selsoft.trackme.email.service;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.selsoft.trackme.email.common.Constants;
import com.selsoft.trackme.email.common.MailResponse;
import com.selsoft.trackme.model.User;

@Component("mailSenderService")
@PropertySource("classpath:emailServices.properties")
public class MailSenderService {

	@Autowired(required = true)
	private MailSender mailSender;
	@Autowired(required = true)
	private Environment environment;

	public MailResponse sendMail(SimpleMailMessage msg) {
		MailResponse response = new MailResponse();
		SimpleMailMessage message = new SimpleMailMessage();
		try {
			message.setFrom(environment.getProperty(Constants.USERNAME));
			message.setTo(msg.getTo());
			message.setSubject(msg.getSubject());
			message.setText(msg.getText());
			mailSender.send(message);
			response.setStatus("Success");
			response.setErrorCode("0000");
		} catch (Exception e) {
			response.setStatus(e.getLocalizedMessage());
			response.setErrorCode("9999");
		}
		return response;
	}

}
