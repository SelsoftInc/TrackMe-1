package com.selsoft.demo.dao;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;

import com.selsoft.demo.model.Lease;
public interface AutoBillingDAO {

	public List<Lease> getActiveLease();
	public List<String> updateLeaseDate(List<Lease> lease);
	public SimpleMailMessage sendEmailForBilling(String tenantEmails);
	
	}