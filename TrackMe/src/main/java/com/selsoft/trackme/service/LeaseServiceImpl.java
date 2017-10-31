package com.selsoft.trackme.service;

import java.sql.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.selsoft.trackme.constants.ErrorConstants;
import com.selsoft.trackme.constants.TrackMeConstants;
import com.selsoft.trackme.dao.LeaseDAO;
import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.PropertyStatus;
import com.selsoft.trackme.model.RentalDetail;
import com.selsoft.trackme.model.TenantStatus;
import com.selsoft.trackme.model.ValidError;

@Service("leaseService")
@PropertySource("classpath:ErrorMsg.properties")
public class LeaseServiceImpl implements LeaseService {

	@Autowired
	private LeaseDAO leaseDAO;

	@SuppressWarnings(TrackMeConstants.UNUSED)
	private static final Logger logger = Logger.getLogger(LeaseServiceImpl.class);

	@Override
	public ValidError validateNewLeaseData(Lease lease) {

		String leaseType = lease.getLeaseType(); // RENT, LEASE, BOTH

		if (StringUtils.equals(PropertyStatus.NEW.getValue(), leaseType)) {

			ValidError validError = new ValidError(ErrorConstants.ERROR101, ErrorConstants.ERROR101_MESSAGE);
			return validError;
		}

		else if (StringUtils.equals(PropertyStatus.ACTIVE.getValue(), leaseType)) {

			ValidError validError = new ValidError(ErrorConstants.ERROR102, ErrorConstants.ERROR102_MESSAGEessage);
			return validError;

		} else if (StringUtils.equals(PropertyStatus.OCCUPIED.getValue(), leaseType)) {

			ValidError validError = new ValidError(ErrorConstants.ERROR103, ErrorConstants.ERROR103_MESSAGE);
			return validError;
		} else if (StringUtils.equals(PropertyStatus.MAINTENANCE.getValue(), leaseType)) {

			ValidError validError = new ValidError(ErrorConstants.ERROR103, ErrorConstants.ERROR103_MESSAGE);
			return validError;
		} else if (StringUtils.equals(PropertyStatus.INACTIVE.getValue(), leaseType)) {

			ValidError validError = new ValidError(ErrorConstants.ERROR104, ErrorConstants.Error104_Message);
			return validError;

		}

		if (StringUtils.equals(TenantStatus.NEW.getValue(), leaseType)) {

			ValidError validError = new ValidError(ErrorConstants.ERROR105, ErrorConstants.ERRROR105_MESSAGE);
			return validError;

		}
		return null;

	}

	@Override
	public void createLease(Lease lease) {
		leaseDAO.createLease(lease);
	}

	@Override
	public void saveRentalDetail(RentalDetail rentalDetail, String propertyId) {
		int propId = Integer.parseInt(propertyId);

		leaseDAO.saveRentalDetail(rentalDetail, propId);
	}

	@Override
	public ValidError validateNewRentalData(RentalDetail rentalDetail) {

		String leaseType = rentalDetail.getLeaseType();
		logger.info(rentalDetail.getLeaseType() + " data comes into LeaseController saveRentalDetail() for processing");

		if (StringUtils.equals("RENT", leaseType)) {
			ValidError validError = new ValidError(ErrorConstants.ERROR106, ErrorConstants.ERRROR106_MESSAGE);
			return validError;

		}

		else if (StringUtils.equals("LEASE", leaseType)) {
			ValidError validError = new ValidError(ErrorConstants.ERROR107, ErrorConstants.ERRROR107_MESSAGE);
			return validError;

		} else if (StringUtils.equals("BOTH", leaseType)) {
			ValidError validError = new ValidError(ErrorConstants.ERROR108, ErrorConstants.ERRROR108_MESSAGE);
			return validError;

		}
		return null;
	}

	@Override
	public List<Property> getAllRentalDetails(Integer propertyId) {

		return leaseDAO.fetchLeases(propertyId);
	}

	public RentalDetail getRentalDetail(Integer propertyId,String inputDate) {
		return leaseDAO.getRentalDetail(propertyId, inputDate);
	}

	
	

	

}
