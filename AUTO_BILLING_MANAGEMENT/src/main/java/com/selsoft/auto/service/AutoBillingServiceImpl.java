package com.selsoft.auto.service;

import java.util.List;
import java.util.Locale;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import com.selsoft.auto.dao.AutoBillingDAO;
import com.selsoft.auto.model.Tenant;


@Service
public class AutoBillingServiceImpl implements AutoBillingService {

	@Autowired
	private AutoBillingDAO autoBillingDAO;

	private static final Logger logger = Logger.getLogger(AutoBillingServiceImpl.class);

	@Override
	public void manageBilling() {

		List<String> tenantEmails = autoBillingDAO.autoBilling();

	}

	@Override
	public SimpleMailMessage sendEmailForBilling(String tenantEmails,String contextPath, Locale locale, String token,Tenant tenant){
	
		String url = contextPath + "/tenant/changePassword?id=" + tenant.getTenantEmailId() + "&token=" + token;
		String message = generateContent(tenant, token, contextPath);
		return  constructEmail("Reset Password", message, tenant);
	}

	public String generateContent(Tenant tenant, String token, String appUrl) {

		StringBuilder builder = new StringBuilder();
		builder.append("<html>Hi " + tenant.getTenantFirstName()
				+ "<br> Please use below link to Reset your password.<br><a href='" + appUrl + "?token=" + token
				+ "'>Click here to Reset Password</a><br>" + "Thanks,<br> TrackMe Inc.</html>");
		return builder.toString();
	}

	private SimpleMailMessage constructEmail(String subject, String body, Tenant tenant) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(tenant.getTenantEmailId());
		return email;
	}

}