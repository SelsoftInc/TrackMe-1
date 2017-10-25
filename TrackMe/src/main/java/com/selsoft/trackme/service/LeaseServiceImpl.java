package com.selsoft.trackme.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selsoft.trackme.dao.LeaseDAO;
import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.PropertyStatus;
import com.selsoft.trackme.model.TenantStatus;
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
			ValidError validError = new ValidError("ERROR100",
					"Property needs to be activated before assigning to a tenant");
			return validError;
		}

		if (PropertyStatus.OCCUPIED.toString().equals(getPropertyStatusById(lease.getPropertyId()))) {

			ValidError validError = new ValidError("ERROR101", "Property is occupied, cannot assign a tenant");
			return validError;

		}
		if (PropertyStatus.MAINTENANCE.toString().equals(getPropertyStatusById(lease.getPropertyId()))) {
			ValidError validError = new ValidError("ERROR102", "Property under maintenance, cannot assign a tenant");
			return validError;
		}
		if (PropertyStatus.INACTIVE.toString().equals(getPropertyStatusById(lease.getPropertyId()))) {
			ValidError validError = new ValidError("ERROR103", "Property is INACTIVE, cannot assign a tenant");
			return validError;
		}

		if (TenantStatus.NEW.toString().equals(getTenantStatusById(lease.getTenantId()))) {
			ValidError validError = new ValidError("ERROR200",
					"New property in market. Tenant cannot be assigned to this property until it is made active");
			return validError;
		}
		return null;

	}

	@Override
	public String getPropertyStatusById(int id) {
		return leaseDAO.getPropertyStatusById(id);
	}

	@Override
	public String getTenantStatusById(int id) {
		return leaseDAO.getTenantStatusById(id);
	}

	@Override
	public ValidError createLease(Lease lease) {
		priorDataValidation(lease);

		return null;
	}

}

