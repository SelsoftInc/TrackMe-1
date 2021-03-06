package com.selsoft.lease.service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.selsoft.lease.constants.ErrorConstants;
import com.selsoft.lease.constants.TrackMeConstants;
import com.selsoft.lease.dao.LeaseDAO;
import com.selsoft.lease.model.Lease;
import com.selsoft.lease.model.PropertyStatus;
import com.selsoft.lease.model.RentalDetail;
import com.selsoft.lease.model.TenantStatus;
import com.selsoft.lease.model.ValidError;

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
		rentalDetail.setProperytId(propId);
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
	public List<RentalDetail> getAllRentalDetails(Integer propertyId) {

		return leaseDAO.getAllRentalDetails(propertyId);
	}

	@Override
	public RentalDetail getRentalDetail(Integer propertyId, String inputDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(inputDate);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return leaseDAO.getRentalDetail(propertyId, date);
	}

	@Override
	public String getFileNameById(String leaseId) {
		File downloadFile = new File("C:\\downloadfile");
		String filename = downloadFile.getName();
		String fileExt = FilenameUtils.getExtension(filename);
		String mimeType = "";
		
		if (fileExt == null) {
			mimeType = "application/octet-stream";
		} else if (StringUtils.equalsIgnoreCase("pdf", fileExt)) {
			mimeType = "application/pdf";
		}
		else if(StringUtils.equalsIgnoreCase("text", fileExt)){
			mimeType = "application/text";
		}
		else if(StringUtils.equalsIgnoreCase("doc", fileExt)){
			mimeType = "application/doc";
		}
		else if(StringUtils.equalsIgnoreCase("image", fileExt)){
			mimeType = "application/image";
		}
		else if(StringUtils.equalsIgnoreCase("jpg", fileExt)){
			mimeType = "application/jpg";
		}
		else if(StringUtils.equalsIgnoreCase("png", fileExt)){
			mimeType = "application/png";
		}
		else if(StringUtils.equalsIgnoreCase("gif", fileExt)){
			mimeType = "application/gif";
		}
		logger.info("MIME type: " + mimeType);
		
		return leaseDAO.getFileNameById(leaseId);
		
	}
}


