package com.selsoft.trackme.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;



@Repository
public class TransactionDAOImpl implements TransactionDAO {

	private static final Logger logger = Logger.getLogger(TransactionDAOImpl.class);
	private static final String[] QUARTERS_IN_YEAR = {"Q1", "Q2", "Q3", "Q4"};
	private static final String[] MONTHS_IN_YEAR = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

	@Autowired
	private MongoTemplate template;

	@Override
	public void saveTransaction(Transaction transaction) {
		try {
			template.save(transaction);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	@Override
	public List<Transaction> getTransaction(int transactionId) {
		List<Transaction> transactionList = null;

		if (transactionId != 0) {

			Query query = new Query(Criteria.where("transactionId").is(transactionId));
			transactionList = template.find(query, Transaction.class);
		} else {

			transactionList = template.findAll(Transaction.class);
		}

		return transactionList;
	}

	public List<Transaction> getTransactionsForProperty(@PathVariable("propertyid") int propertyId,
			@PathVariable("fromdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable("todate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {

		String fromDateValue = toDate.toString();
		String toDateValue = toDate.toString();
		String result = compareTwoDates(fromDateValue, toDateValue);
		if (result.equals(fromDateValue)) {
			logger.info("From Date is greater that to date");
			// return validation error
		} else {
			logger.info("NO validation error- toDate is greater");
			// do not do anything. allow for next step
		}
		return null;

		// return
		// transactionService.getTransactionsForProperty(propertyId,fromDate,toDate);
	}

	private String compareTwoDates(String fromDate, String toDate) {
		// split year, month and days from the date using StringBuffer.
		StringBuffer sBuffer = new StringBuffer(fromDate);
		String year = sBuffer.substring(2, 4);
		String mon = sBuffer.substring(5, 7);
		String dd = sBuffer.substring(8, 10);

		// split year, month and days from the date using StringBuffer.
		StringBuffer sBuffer1 = new StringBuffer(toDate);
		String year1 = sBuffer1.substring(2, 4);
		String mon1 = sBuffer1.substring(5, 7);
		String dd1 = sBuffer1.substring(8, 10);

		String modifiedFromDate = dd + '/' + mon + '/' + year;
		String modifiedToDate = dd1 + '/' + mon1 + '/' + year1;
		// int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;

		/*
		 * Use SimpleDateFormat to get date in the format as passed in the constructor.
		 * This object can be used to covert date in string format to java.util.Date and
		 * vice versa
		 */
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
		java.util.Date dateSelectedFrom = null;
		java.util.Date dateSelectedTo = null;

		// convert date present in the String to java.util.Date.
		try {
			dateSelectedFrom = dateFormat.parse(modifiedFromDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// convert date present in the String to java.util.Date.
		try {
			dateSelectedTo = dateFormat.parse(modifiedToDate);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// use the compareTo method of java.util.Date to compare two java.util.Dates.
		if (dateSelectedFrom.compareTo(dateSelectedTo) > 0) {
			return fromDate;
		} else {
			return toDate;
		}
	}

	@Override
	public List<Transaction> getTransactionReport(String reportType, int year, String duration) throws Throwable {

		List<Transaction> transactionList = null;
		

		 reportType = StringUtils.upperCase(StringUtils.trimToEmpty(reportType));
		 duration = StringUtils.upperCase(StringUtils.trimToEmpty(duration));
			
	      DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	      DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");  

	       String todate= "2017-05-02";
	      // Date fromDate = outputFormat.parse(duration);      
	       //Date toDate = outputFormat.parse(todate);

           Query query = new Query();
           
           String fromDate = null, toDate = null;
           
           if(StringUtils.equals("Y", reportType)) { //Yearly report
           	
           	if(year <= 0) 
           	throw new ValidError("Error", " Year can not be less than Zero");
           	fromDate = year + "-01-01";
           	toDate = year + "-12-31";
           	
           } else if(StringUtils.equals("Q", reportType)) { //Quarterly report
           	if(year == 0 || StringUtils.isBlank(duration) || !ArrayUtils.contains(QUARTERS_IN_YEAR, duration)) 
           		throw new ValidError("Error", " Year and  Quarterly can not be  Zero");
           		          	
           	if(StringUtils.equals("Q1", duration)) {
           		fromDate = year + "-01-01";
           		toDate = year + "-03-31";
           	} else if(StringUtils.equals("Q2", duration)) {
           		fromDate = year + "-04-01";
           		toDate = year + "-06-30";
           	} else if(StringUtils.equals("Q3", duration)) {
           		fromDate = year + "-07-01";
           		toDate = year + "-09-30";
           	} else if(StringUtils.equals("Q4", duration)) {
           		fromDate = year + "-10-01";
           		toDate = year + "-12-31";
           	}
           	
           } else if(StringUtils.equals("M", reportType)) { //Monthly report
           	if(year == 0 || StringUtils.isBlank(duration) || !ArrayUtils.contains(MONTHS_IN_YEAR, duration)) 
           		throw new ValidError("Error", " Year and Month can not be null");
           	int selectedMonth = ArrayUtils.indexOf(MONTHS_IN_YEAR, duration) + 1;           	//(index of array + 1, for Jan =1, Feb = 2, ...., Dec = 12)
           	int lastDayOfMonth = 0; //Based on year (leap or regular year) and month, set the last day of the month and use it against paidOn
           	
           	fromDate = year + "-01-01";
           	toDate = year + "-" + StringUtils.leftPad(String.valueOf(selectedMonth), 2, "0") + "-" + StringUtils.leftPad(String.valueOf(lastDayOfMonth), 2, "0");
           	
           }
           
           if(StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
           	query.addCriteria(Criteria.where("paidOn").gte(fromDate).lte(toDate));
           	transactionList=template.find(query, Transaction.class);
           }
           return transactionList;
	} 
	   
	   	@Override
	public List<Transaction> getTransactionReportYearly(String year) {
		LocalDate startDate = new LocalDate(Integer.parseInt(year), 01, 01);
		LocalDate endDate = new LocalDate(Integer.parseInt(year), 12, 31);
		Query query = new Query(Criteria.where("paidOn").gte(startDate.toDate()).lte(endDate.toDate()));
		List<Transaction> transactionList = template.find(query, Transaction.class);

		return transactionList;
	}

		@Override
		public List<Transaction> getTransactionReport(String reportType, String year, String duration) {

			List<Transaction> transactionList = null;
			DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
			String todate = "2017-05-02";
			Query query = new Query();
			Criteria c = Criteria.where("paidOn").lte("2017-12-07");
			query.addCriteria(c);
			transactionList = template.find(query, Transaction.class);

			return null;
		}

}
		   