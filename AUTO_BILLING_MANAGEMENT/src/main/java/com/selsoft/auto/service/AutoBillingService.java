package com.selsoft.auto.service;

import java.util.Locale;
import org.springframework.mail.SimpleMailMessage;
import com.selsoft.auto.model.Tenant;
public interface AutoBillingService {

	public void manageBilling() ;

	public SimpleMailMessage sendEmailForBilling(String tenantEmails, String contextPath, Locale locale, String token,
			Tenant tenant);

}
