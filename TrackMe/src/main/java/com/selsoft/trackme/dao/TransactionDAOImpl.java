package com.selsoft.trackme.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
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

@Repository
public class TransactionDAOImpl implements TransactionDAO {

	private static final Logger logger = Logger.getLogger(TransactionDAOImpl.class);

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

	@RequestMapping(value = "/getTransactionsForProperty/{transactionid}/{fromdate}/{todate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
	public List<Transaction> getTransactionsForProperty(@PathVariable("propertyid") int propertyId,
			@PathVariable("fromdate") @DateTimeFormat(pattern="yyyy-MM-dd")  Date fromDate
			,@PathVariable("todate") @DateTimeFormat(pattern="yyyy-MM-dd") Date toDate) {
		
		String fromDateValue = toDate.toString();
		String toDateValue = toDate.toString();
		String result = compareTwoDates(fromDateValue, toDateValue);
		if(result.equals(fromDateValue)) {
			logger.info("From Date is greater that to date");
			// return validation error
		}else {
			logger.info("NO validation error- toDate is greater");
			// do not do anything. allow for next step
		}
		return null;
		
		//return transactionService.getTransactionsForProperty(propertyId,fromDate,toDate);
	}
	
	   private String compareTwoDates(String fromDate,String toDate)
	   {
	       //split year, month and days from the date using StringBuffer.
	       StringBuffer sBuffer = new StringBuffer(fromDate);
	       String year = sBuffer.substring(2,4);
	       String mon = sBuffer.substring(5,7);
	       String dd = sBuffer.substring(8,10);

	       //split year, month and days from the date using StringBuffer.
	       StringBuffer sBuffer1 = new StringBuffer(toDate);
	       String year1 = sBuffer1.substring(2,4);
	       String mon1 = sBuffer1.substring(5,7);
	       String dd1 = sBuffer1.substring(8,10);

	       String modifiedFromDate = dd +'/'+mon+'/'+year;
	       String modifiedToDate = dd1 +'/'+mon1+'/'+year1;
	       //int MILLIS_IN_DAY = 1000 * 60 * 60 * 24;

	       /* Use SimpleDateFormat to get date in the format as
		*passed in the constructor. This object can be used to
		*covert date in string format to java.util.Date and vice versa*/
	       SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
	       java.util.Date dateSelectedFrom = null;
	       java.util.Date dateSelectedTo = null;

	       // convert date present in the String to java.util.Date.
	       try
	       {
		   dateSelectedFrom = dateFormat.parse(modifiedFromDate);
	       }
	       catch(Exception e)
	       {
		   e.printStackTrace();
	       }

		// convert date present in the String to java.util.Date.
		try
		{
		    dateSelectedTo = dateFormat.parse(modifiedToDate);
		}
		catch(Exception e)
		{
		    e.printStackTrace();
		}

		//use the compareTo method of java.util.Date to compare two java.util.Dates.
		if(dateSelectedFrom.compareTo(dateSelectedTo)>0)
		{
		    return fromDate;
		}
		else
		{
		    return toDate;
		}
	   }

	@Override
	public List<Transaction> getTransactionReport(String reportType, String duration) {
		 List<Transaction> transactionList = null;
			
	      DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
	       DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");  

	       String todate= "2017-05-02";
	      // Date fromDate = outputFormat.parse(duration);      
	       //Date toDate = outputFormat.parse(todate);

	                Query query = new Query();

//	         Criteria c = new Criteria().andOperator(Criteria.where("paidOn").gte("2017-05-12"),  
	                Criteria c =Criteria.where("paidOn").lte("2017-12-07");

	        query.addCriteria(c);
	        transactionList=template.find(query, Transaction.class);
	        
//			if(duration!=null){
//				 query = new Query(Criteria.where("duration").is(duration));
//				transactionList = template.find(query, Transaction.class);
//			} else {
//
//				transactionList = template.findAll(Transaction.class);
//			}
//			
//				Object paidOn = null;
//				if(reportType.equals("Y")){
//				 query = new Query(Criteria.where("paidOn").is(paidOn));
//				transactionList = template.find(query, Transaction.class);
//			}
//				else if(reportType.equals("Q")){
//					Transaction transaction=new Transaction();
//					 query = new Query(Criteria.where("paidOn").is(transaction.getPaidOn()));
//					Update update = new Update();
//					update.set(" duration:Jan to Mar", "Q1");
//					update.set("duration: Apr to Jun", "Q2");
//					update.set("duration: Jul to Sep", "Q3");
//					update.set("duration: Oct to Nov", "Q4");
//					template.updateFirst(query, update, Transaction.class);
//					
//					}
//				else if(reportType.equals("M")){
//					 query = new Query(Criteria.where("paidOn").is(paidOn));
//				transactionList = template.find(query, Transaction.class);
//			}
//			
	        return transactionList;
		} 
		   
		}
		   