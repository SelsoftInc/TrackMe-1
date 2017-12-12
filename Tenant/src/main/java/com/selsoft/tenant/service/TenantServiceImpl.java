package com.selsoft.tenant.service;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.selsoft.tenant.constants.TenantConstants.TENANT_STATUS;
import com.selsoft.tenant.constants.TrackMeConstants;
import com.selsoft.tenant.dao.LeaseDAO;
import com.selsoft.tenant.dao.TenantDAO;
import com.selsoft.tenant.model.Errors;
import com.selsoft.tenant.model.Tenant;
import com.selsoft.tenant.model.ValidError;
import com.selsoft.tenant.utils.TenantException;
import com.selsoft.tenant.validation.TenantValidation;



@Service("tenantService")
public class TenantServiceImpl implements TenantService {

	@Autowired
	private TenantDAO tenantDAO;
	@Autowired
	private LeaseDAO leaseDAO;
	private TenantValidation validation = new TenantValidation();

	@SuppressWarnings(TrackMeConstants.UNUSED)
	private static final Logger logger = Logger.getLogger(TenantService.class);
	
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * add new tenant to the tenant table
	 * @throws Throwable 
	 */
	@Override
	public Object addNewTenant(Tenant tenant) throws Throwable {
		
		validateNewTenant(tenant);
		tenantDAO.saveNewTenant(tenant);
		return tenant;

	}

	private void validateNewTenant(Tenant tenant) throws Throwable {
		tenant.setTenantStatus(TENANT_STATUS.NEW.getValue());
		tenant.setTenantEmailId(StringUtils.trim(StringUtils.lowerCase(tenant.getTenantEmailId())));
		tenant.setTenantPhoneNumber(StringUtils.trim(tenant.getTenantPhoneNumber()));
		nameValidation(tenant.getTenantFirstName(), tenant.getTenantLastName());
		emailValidation(tenant.getTenantEmailId());
		phoneNumberValidation(tenant.getTenantPhoneNumber());
	}
	
	
	// First Name and Last Name Validation
		private void nameValidation(String firstName, String lastName) throws Throwable {
			logger.info("Validating user first name and last name");
			String letterChars = "[a-zA-Z]+";
			if (StringUtils.isBlank(firstName) || StringUtils.isBlank(lastName)) {
				throw new TenantException("Error", "First Name and/or Last Name missing, cannot create the user");
			} else if(StringUtils.length(firstName) > 30 || StringUtils.length(lastName) > 30) {
				throw new TenantException("Error", "Name should be under 30 Characters");
			} else if (!firstName.matches(letterChars) || !lastName.matches(letterChars)) {
				throw new TenantException("Error", "Name should contain only Characters");
			}
		}
		
		// Email Address Validation
		private void emailValidation(String email) throws Throwable {
			logger.info("Validating user email");
			if(StringUtils.isBlank(email)) {
				throw new TenantException("Error", "Email id missing, please enter a proper email id");
			} else if (!email.matches(EMAIL_PATTERN)) {
				throw new TenantException("Error", "Email is not Valid");
			}
		}
		
		//Phone number validation
		private void phoneNumberValidation(String phoneNumber) throws Throwable {
			if(StringUtils.isBlank(phoneNumber)) {
				throw new TenantException("Error", "Phone number missing, please enter a valid phone number");
			} else if(StringUtils.length(phoneNumber) != 10) {
				throw new TenantException("Error", "Phone number not valid, phone number should be 10 characters");
			}
		}
	

	/**
	 * get all the tenants from the tenant table
	 */
	@Override
	public List<Tenant> getAllTenants(String status) {
		return tenantDAO.fetchTenants(status);
	}

	/**
	 * save new tenant to the table
	 */
	@Override
	public Errors saveNewTenant(Tenant tenat) {
		Errors errors=null;
		errors =  validateTenant(tenat);
	      tenantDAO.saveNewTenant(tenat);
	       return errors;
	}

	public Errors validateTenant(Tenant tenat) {

		String firstName = tenat.getTenantFirstName();
		String lastName = tenat.getTenantLastName();
		String email = tenat.getTenantEmailId();
		
		ValidError nameErrors = validation.nameValidation(firstName, lastName);
		ValidError emailErrors = validation.emailValidation(email);
		
		List<ValidError> errorList = new ArrayList<ValidError>();
		errorList.add(nameErrors);
		errorList.add(emailErrors);
		Errors errors = new Errors();
		errors.setError(errorList);
		return errors;
	}

	public String getTenantStatusById(int id) {
		return leaseDAO.getTenantStatusById(id);
	}
}
