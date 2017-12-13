package com.selsoft.tenant.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import com.selsoft.tenant.constants.TrackMeConstants;
import com.selsoft.tenant.model.Lease;
import com.selsoft.tenant.model.Property;
import com.selsoft.tenant.model.RentalDetail;
import com.selsoft.tenant.model.Tenant;

@Repository
public class LeaseDAOImpl implements LeaseDAO {

	@SuppressWarnings(TrackMeConstants.UNUSED)
	private static final Logger logger = Logger.getLogger(LeaseDAOImpl.class);

	@Autowired
	private MongoTemplate template;

	@Autowired
	@Qualifier("propertyDAO")
	private PropertyDAO propertyDao;

	@Override
	public String getPropertyStatusById(int id) {
		Query query = new Query(Criteria.where("propertyId").is(id));
		Property property = template.findOne(query, Property.class);

		return property.getPropertyStatus();
	}

	public String getTenantStatusById(int id) {

		Query query = new Query(Criteria.where("tenantId").is(id));
		Tenant tenant = template.findOne(query, Tenant.class);

		return tenant.getTenantStatus();
	}

	@Override
	public void createLease(Lease lease) {
		template.save(lease);
	}

	@Override
	public void saveRentalDetail(RentalDetail rentalDetail, int propertyId) {
		template.save(rentalDetail);
		propertyDao.updateProperty(rentalDetail, propertyId);

	}

	@Override
	public List<RentalDetail> getAllRentalDetails(Integer propertyId) {
		List<RentalDetail> rentalDetailList = null;

		if (propertyId != null) {

			Query query = new Query(Criteria.where("propertyId").is(propertyId));
			rentalDetailList = template.find(query, RentalDetail.class);
		} else {

			rentalDetailList = template.findAll(RentalDetail.class);
		}

		return rentalDetailList;
	}

	@Override
	public RentalDetail getRentalDetail(Integer propertyId, Date inputDate) {

		Query query = new Query(Criteria.where("propertyId").is(propertyId));
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "effectiveDate"));

		List<RentalDetail> rentalDetailList = template.find(query, RentalDetail.class);
		logger.info("RECENT RENTAL DETAIL: " + rentalDetailList.get(0));

		return rentalDetailList.get(0);
	}

	private Date getDateNearest(List<Date> dates, Date targetDate) {
		Date returnDate = targetDate;

		for (Date date : dates) {
			if (date.compareTo(targetDate) <= 0 && date.compareTo(returnDate) > 0) {
				returnDate = date;
			}
		}

		return returnDate;
	}

}
