package com.selsoft.trackme.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.Property;
import org.springframework.stereotype.Service;

import com.selsoft.trackme.dao.LeaseDAO;
import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.PropertyStatus;
import com.selsoft.trackme.model.TenantStatus;
import com.selsoft.trackme.model.ValidError;

@Service("leaseService")
@PropertySource("classpath:ErrorMsg.properties")
public class LeaseServiceImpl implements LeaseService {
	
	
	@Value("${property_error_code_101}")
	private String propertErrorCode;
	
	@Value("${property_error_message_101}")
	private String propertErrorMessage;

	@Autowired
	private LeaseDAO leaseDAO;

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(LeaseServiceImpl.class);

	@Override
	public ValidError validateNewLeaseData(Lease lease) {

		String  leaseType = lease.getLeaseType();

		if (PropertyStatus.NEW.getValue().equals(leaseType)) {
			
			ValidError validError = new ValidError(propertErrorCode,propertErrorMessage);//try to put error message and codefrom
			return validError;
		}

		else if (PropertyStatus.OCCUPIED.getValue().equals(leaseType)) {

			ValidError validError = new ValidError("error_code_102= 102","Property is occupied, cannot assign a tenant");
			return validError;

		} else if (PropertyStatus.MAINTENANCE.getValue().equals(leaseType)) {
			
			ValidError validError = new ValidError("error_code_103= 103","Property under maintenance, cannot assign a tenant");
			return validError;
		} else if (PropertyStatus.INACTIVE.getValue().equals(leaseType)) {
			
			ValidError validError = new ValidError("error_code_104= 104","Property is INACTIVE, cannot assign a tenant");
			return validError;
			
		}

		if (!TenantStatus.NEW.equals(leaseType)) {
			

	
			ValidError validError = new ValidError("error_code_105= 105","Tenant cannot be assigned to this Lease until it is active");
			return validError;
			
			
		}
		return null;

	}

	@Override
	public void createLease(Lease lease) {
		leaseDAO.createLease(lease);
	}

}
