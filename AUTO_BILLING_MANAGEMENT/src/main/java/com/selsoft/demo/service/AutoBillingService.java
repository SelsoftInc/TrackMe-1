package com.selsoft.demo.service;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;

import com.selsoft.demo.model.Lease;

public interface AutoBillingService {
	
	public List<Lease> getActiveLease();
	public List<String> updateLeaseDate(List<Lease> lease);
	public void manageBilling();
	public SimpleMailMessage sendEmailForBilling(String tenantEmails);
	
}
