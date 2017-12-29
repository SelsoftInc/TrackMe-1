package com.selsoft.auto.email.test;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.selsoft.auto.email.config.EmailServiceBeanConfiguration;
import com.selsoft.auto.email.service.MailSenderService;

public class MailServiceTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(EmailServiceBeanConfiguration.class);
		final Logger logger = Logger.getLogger(MailServiceTest.class);
		System.setProperty("java.net.preferIPv4Stack", "true");
		MailSenderService service = (MailSenderService) context.getBean("mailSenderService");
		List<String> mailId = new ArrayList<>();
		mailId.add("****@gmail.com");
		//logger.info(service.sendMail(mailId, "Sample Text","Hi *** How are you"));
	}

}