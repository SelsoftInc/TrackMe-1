package com.selsoft.trackme.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selsoft.trackme.dao.LeaseDAO;
import com.selsoft.trackme.model.Errors;
import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.PropertyStatus;
import com.selsoft.trackme.model.ValidError;

@Service("leaseService")
public class LeaseServiceImpl implements LeaseService {

	@Autowired
	private LeaseDAO leaseDAO;

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(LeaseServiceImpl.class);

	@Override
	public ValidError priorDataValidation(Lease lease) {

		if (PropertyStatus.NEW.toString().equals(getPropertyStatusById(lease.getPropertyId()))) {
			// logger.info("Property needs to be activated before assigning to a tenan:");
			// need to put error
			ValidError validError = new ValidError("ERROR100", "user type new");
			return validError;
		}

		if (PropertyStatus.NEW.toString().equals(getPropertyStatusById(lease.getPropertyId()))) {
			// logger.info("Property needs to be activated before assigning to a tenan:");
			ValidError validError = new ValidError("ERROR101", "user type occupid");
			return validError;

		}
		if (PropertyStatus.NEW.toString().equals(getPropertyStatusById(lease.getPropertyId()))) {
			logger.info("Property needs to be activated before assigning to a tenan:");

		}
		if (PropertyStatus.NEW.toString().equals(getPropertyStatusById(lease.getPropertyId()))) {
			logger.info("Property needs to be activated before assigning to a tenan:");

		}
		return null;

	}

	@Override
	public String getPropertyStatusById(int id) {
		return leaseDAO.getPropertyStatusById(id);
	}

	@Override
	public ValidError createLease(Lease lease) {
		priorDataValidation(lease);

		return null;
	}

}
