package com.selsoft.trackme.dao;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.selsoft.trackme.constants.TrackMeConstants;
import com.selsoft.trackme.model.Lease;
import com.selsoft.trackme.model.Property;
import com.selsoft.trackme.model.RentalDetail;
import com.selsoft.trackme.model.Tenant;

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

		RentalDetail rentalDetail = null;

		if (propertyId != null) {

			Query query = new Query(Criteria.where("propertyId").is(propertyId));
			Property property = template.findOne(query, Property.class);
			Date date = property.getRentalDetail().getEffectiveDate();

			Timestamp timestamp1 = new Timestamp(date.getTime());
			Timestamp timestamp2 = new Timestamp(inputDate.getTime());

			//ObjectId.getTimestamp();
			
		/*	
			try
		    {
		        SimpleDateFormat sdf = new SimpleDateFormat(timestamp2);
		        if (sdf.format(sdf.parse(timestamp1)).equals(timestamp1))
		            return rentalDetail;
		    }
		    catch (ParseException pe) {}

		    return null;
		}
			
			db.example.insert({"inputDate":new Date()});
			Date now = new Date();

			BasicDBObject timeNow = new BasicDBObject("date", now);
			example.insert(timeNow);
			
			BasicDBObject query = new BasicDBObect();
			 BasicDBObject update = new BasicDBObject("$currentDate",
			     new BasicDBObject("date", true));
			 
			 query.put("date", new Date());
			 String serialize = JSON.serialize(query);
			     db.example.insert({"date":ISODate("2016-03-03T08:00:00.000")});
			 );

			 example.update(query,update,true,false);
			 
			 DBObject object = new BasicDBObject();
			 object.put("date", new Date());
			 String serialize = JSON.serialize(object)*/;
			
				if (timestamp2.compareTo(timestamp1) < 0) {
				logger.info(" input date is most recently used date");

			                      }

		} else {

			template.save(rentalDetail);

		}

		return null;
	}

}
