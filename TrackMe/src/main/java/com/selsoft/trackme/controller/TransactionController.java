package com.selsoft.trackme.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selsoft.trackme.constants.TransactionConstants;
import com.selsoft.trackme.dto.TransactionDto;
import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;
import com.selsoft.trackme.service.TransactionService;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

	private static final Logger logger = Logger.getLogger(TransactionController.class);
	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/saveTransaction", method = RequestMethod.PUT)
	public void saveTransaction(@RequestParam(value="transaction", required=false) MultipartFile txnContent,
			@RequestParam("file") MultipartFile file) {
		//return transactionService.saveTransaction(txnContent, file);
		
		TransactionDto transactionDto = null;
		Transaction transactionModel = null;

		String fileContent = "";

		try {
			fileContent = new String(file.getBytes());

			if (fileContent.length() > 2 * 1024 * 1024) // 2MB

			{
				logger.info("File is too big");
				//throw new TransactionException("Fatal", t);

			}
		} catch (IOException e3) {
			e3.printStackTrace();
		}

		ObjectMapper mapper = new ObjectMapper();
		try {
			String content=new String(txnContent.getBytes());
			transactionDto = mapper.readValue(content, TransactionDto.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		logger.info(transactionDto.getTransactionId()
				+ " data comes into TransactionController saveTransaction() for processing");
		try {
			transactionDto.setFile(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String filePath = TransactionConstants.FILEPATH;
		String fileName = file.getOriginalFilename();
		String absolutePath=filePath+"\\"+fileName;
		try {
			
			File localFile=new File(absolutePath);
			file.transferTo(localFile);
			transactionModel=new Transaction();
			transactionModel.setTransactionId(transactionDto.getTransactionId());
			transactionModel.setPropertyId(transactionDto.getPropertyId());
			transactionModel.setOwnerId(transactionDto.getOwnerId());
			transactionModel.setManagerId(transactionDto.getManagerId());
			transactionModel.setAmount(transactionDto.getAmount());
			transactionModel.setTransactionType(transactionDto.getTransactionType());
			transactionModel.setTransactionCode(transactionDto.getTransactionCode());
			transactionModel.setPaidOn(transactionDto.getPaidOn());
			transactionModel.setEnteredOn(transactionDto.getEnteredOn());
			transactionModel.setFilePath(absolutePath);
		} catch (IOException e) {

			e.printStackTrace();

		}

		ValidError validError = transactionService.validateTransactionData(transactionModel);
		if (validError == null) {
			transactionService.saveTransaction(transactionModel);
		}

	}

	@RequestMapping(value = "/getTransaction/{transactionid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransaction(@PathVariable("transactionid") String transactionid) {

		return transactionService.getTransaction(transactionid);
	}

	@RequestMapping(value = "/getTransactionsForProperty/{transactionid}/{fromdate}/{todate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransactionsForProperty(@PathVariable("propertyid") String propertyId,
			@PathVariable("fromdate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@PathVariable("todate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {

		return transactionService.getTransactionsForProperty(propertyId, fromDate, toDate);
	}

	@RequestMapping(value = "/getTransactionReport", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransactionReport(
			@RequestParam(name = "reportType", required = true, defaultValue = "Y") String reportType,
			@RequestParam(name = "year", required = false, defaultValue = "0") int year,
			@RequestParam(name = "duration", required = false) String duration) throws Throwable {

		return transactionService.getTransactionReport(reportType, year, duration);
	}

	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resource> downloadFilebyID(@PathVariable("id") String transactionId,HttpServletResponse response) throws IOException {

		String fileName = transactionService.getFileNameById(transactionId);
		File file = new File(fileName);
		FileInputStream fileIn = new FileInputStream(file);
		ServletOutputStream out = response.getOutputStream();

		byte[] outputByte = new byte[4096];
		//copy binary contect to output stream
		while(fileIn.read(outputByte, 0, 4096) != -1)
		{
			out.write(outputByte, 0, 4096);
		}
		fileIn.close();
		out.flush();
		out.close();

		return null;	
	}

}