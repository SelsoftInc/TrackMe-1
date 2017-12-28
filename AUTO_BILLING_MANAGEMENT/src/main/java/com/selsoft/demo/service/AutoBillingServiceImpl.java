package com.selsoft.demo.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import com.selsoft.demo.dao.AutoBillingDAO;
import com.selsoft.demo.model.Lease;

@Service("AutoBillingService")
public class AutoBillingServiceImpl implements AutoBillingService {

	@Autowired
	private AutoBillingDAO autoBillingDAO;

	private static final Logger logger = Logger.getLogger(AutoBillingServiceImpl.class);

	@Override
	public List<Lease> getActiveLease() {
		return autoBillingDAO.getActiveLease();
	}

	@Override
	public List<String> updateLeaseDate(List<Lease> lease) {
		return autoBillingDAO.updateLeaseDate(lease);
	}

	@Override
	public void manageBilling() {

		List<Lease> activeLeases = autoBillingDAO.getActiveLease();
		List<String> tenantEmails = autoBillingDAO.updateLeaseDate(activeLeases);

	}

	@Override
	public SimpleMailMessage sendEmailForBilling(String tenantEmails) {

		return autoBillingDAO.sendEmailForBilling(tenantEmails);
	}

}
