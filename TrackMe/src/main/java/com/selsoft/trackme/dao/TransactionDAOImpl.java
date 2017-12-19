package com.selsoft.trackme.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import com.itextpdf.text.pdf.parser.Path;
import com.itextpdf.text.pdf.parser.clipper.Paths;
import com.selsoft.trackme.model.Tenant;
import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.User;
import com.selsoft.trackme.model.ValidError;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

	private static final Logger logger = Logger.getLogger(TransactionDAOImpl.class);
	private static final String[] QUARTERS_IN_YEAR = { "Q1", "Q2", "Q3", "Q4" };
	private static final String[] MONTHS_IN_YEAR = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP",
			"OCT", "NOV", "DEC" };

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
	public List<Transaction> getTransaction(String transactionId) {
		List<Transaction> transactionList = null;
		if (transactionId != null) {

			Query query = new Query(Criteria.where("transactionId").is(transactionId));
			transactionList = template.find(query, Transaction.class);
		} else {

			transactionList = template.findAll(Transaction.class);
		}

		return transactionList;
	}

	public List<Transaction> getTransactionsForProperty(@PathVariable("propertyid") String propertyId,
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
		 * Use SimpleDateFormat to get date in the format as passed in the
		 * constructor. This object can be used to covert date in string format
		 * to java.util.Date and vice versa
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

		// use the compareTo method of java.util.Date to compare two
		// java.util.Dates.
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
		String fromDate = null, toDate = null;

		if (StringUtils.equals("Y", reportType)) { // Yearly report

			if (year <= 0)
				throw new ValidError("Error", " Year can not be less than Zero");
			fromDate = year + "-01-01";
			toDate = year + "-12-31";

		} else if (StringUtils.equals("Q", reportType)) { // Quarterly report
			if (year == 0 || StringUtils.isBlank(duration) || !ArrayUtils.contains(QUARTERS_IN_YEAR, duration))
				throw new ValidError("Error", " Year and  Quarterly can not be  Zero");

			if (StringUtils.equals("Q1", duration)) {
				fromDate = year + "-01-01";
				toDate = year + "-03-31";
			} else if (StringUtils.equals("Q2", duration)) {
				fromDate = year + "-04-01";
				toDate = year + "-06-30";
			} else if (StringUtils.equals("Q3", duration)) {
				fromDate = year + "-07-01";
				toDate = year + "-09-30";
			} else if (StringUtils.equals("Q4", duration)) {
				fromDate = year + "-10-01";
				toDate = year + "-12-31";
			}

		} else if (StringUtils.equals("M", reportType)) { // Monthly report
			if (year == 0 || StringUtils.isBlank(duration) || !ArrayUtils.contains(MONTHS_IN_YEAR, duration))
				throw new ValidError("Error", " Year and Month can not be null");

			int selectedMonth = ArrayUtils.indexOf(MONTHS_IN_YEAR, duration) + 1; // (index
																					// of
																					// array
																					// +
																					// 1,
																					// for
																					// Jan
																					// =1,
																					// Feb
																					// =
																					// 2,
																					// ....,
																					// Dec
																					// =
																					// 12)
			int lastDayOfMonth = 0; // Based on year (leap or regular year) and
									// month, set the last day of the month
									// and use it against paidOn

			// **Leap year check - if leap year - input will be reporttype,
			// year, month
			boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
			// **if leap year true - checking months for deciding start date &
			// end date
			// **Since exiting array is available not creating new array.

			if (StringUtils.equalsIgnoreCase("JAN", duration)) {
				fromDate = year + "-01-01";
				toDate = year + "-01-31";
			} else if (StringUtils.equalsIgnoreCase("FEB", duration) && isLeapYear) {
				fromDate = year + "-02-01";
				toDate = year + "-02-29";
			} else if (StringUtils.equalsIgnoreCase("FEB", duration) && !isLeapYear) {
				fromDate = year + "-02-01";
				toDate = year + "-02-28";
			} else if (StringUtils.equalsIgnoreCase("MAR", duration)) {
				fromDate = year + "-03-01";
				toDate = year + "-03-31";
			} else if (StringUtils.equalsIgnoreCase("APR", duration)) {
				fromDate = year + "-04-01";
				toDate = year + "-04-30";
			} else if (StringUtils.equalsIgnoreCase("MAY", duration)) {
				fromDate = year + "-05-01";
				toDate = year + "-05-31";
			} else if (StringUtils.equalsIgnoreCase("JUN", duration)) {
				fromDate = year + "-06-01";
				toDate = year + "-06-30";
			} else if (StringUtils.equalsIgnoreCase("JUL", duration)) {
				fromDate = year + "-07-01";
				toDate = year + "-07-31";
			} else if (StringUtils.equalsIgnoreCase("AUG", duration)) {
				fromDate = year + "-08-01";
				toDate = year + "-08-31";
			} else if (StringUtils.equalsIgnoreCase("SEP", duration)) {
				fromDate = year + "-09-01";
				toDate = year + "-09-30";
			} else if (StringUtils.equalsIgnoreCase("OCT", duration)) {
				fromDate = year + "-10-01";
				toDate = year + "-10-31";
			} else if (StringUtils.equalsIgnoreCase("NOV", duration)) {
				fromDate = year + "-11-01";
				toDate = year + "-11-30";
			} else if (StringUtils.equalsIgnoreCase("DEC", duration)) {
				fromDate = year + "-12-01";
				toDate = year + "-12-31";
			}

			Calendar calendar = Calendar.getInstance();
			int yearpart = year;
			int monthPart = getExactMonth(duration);
			int dateDay = 1;
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			calendar.set(yearpart, monthPart, dateDay);
			int numOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			System.out.println("Number of Days: " + numOfDaysInMonth);
			fromDate = df.format(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);
			toDate = df.format(calendar.getTime());
			// fromDate = year + "-01-01";
			// toDate = year + "-" +
			// StringUtils.leftPad(String.valueOf(selectedMonth), 2,
			// "0") + "-" + StringUtils.leftPad(String.valueOf(lastDayOfMonth),
			// 2, "0");

		}

		if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {

			Query query = new Query().addCriteria(Criteria.where("paidOn").gte(fromDate).lte(toDate));
			transactionList = template.find(query, Transaction.class);
		}
		return transactionList;
	}

	private static int getExactMonth(String month) {
		Map<String, Integer> calenderMap = new ConcurrentHashMap<>();
		calenderMap.put("JAN", 0);
		calenderMap.put("FEB", 1);
		calenderMap.put("MAR", 2);
		calenderMap.put("APR", 3);
		calenderMap.put("MAY", 4);
		calenderMap.put("JUN", 5);
		calenderMap.put("JUL", 6);
		calenderMap.put("AUG", 7);
		calenderMap.put("SEP", 8);
		calenderMap.put("OCT", 9);
		calenderMap.put("NOV", 10);
		calenderMap.put("DEC", 11);
		return calenderMap.get(month);
	}

	@Override
	public String getFileNameById(String transactionId) {

		String filePath = null;
		Query query = new Query(Criteria.where("transactionId").is(transactionId));
		// query.fields().include("filePath");
		List<Transaction> transactionList = template.find(query, Transaction.class);
		if (transactionList != null && !transactionList.isEmpty())
			filePath = transactionList.get(0).getFilePath();
		return filePath;
	}
}