package com.selsoft.trackme.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.selsoft.trackme.dto.PasswordDto;
import com.selsoft.trackme.dto.TransactionDto;
import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;
import com.selsoft.trackme.service.TransactionService;
import com.selsoft.trackme.utils.TransactionException;

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
		String filePath = "C:\\output2";
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
	public ResponseEntity downloadFilebyID( HttpServletRequest request,
            HttpServletResponse response,@PathParam("id") String transactionId) throws IOException {
		
		 final int BUFFER_SIZE = 4096;
		  String filePath = "C:\\output1";
		  
		// get absolute path of the application
	        ServletContext context = request.getServletContext();
	        String appPath = context.getRealPath("");
	        logger.info("appPath = " + appPath);
	 
	        // construct the complete absolute path of the file
	        String fullPath = appPath + filePath;      
	        File downloadFile = new File(fullPath);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	         
	        // get MIME type of the file
	        String mimeType = context.getMimeType(fullPath);
	        if (mimeType == null) {
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        logger.info("MIME type: " + mimeType);
	 
	        // set content attributes for the response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	 
	        // get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	 
	        byte[] buffer = new byte[BUFFER_SIZE];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
	 
	    
		/*
		String fileName="1.pdf";
		//String fileName = file.getOriginalFilename();
		String absolutePath=filePath+"\\"+fileName;
		
		String dataDirectory = request.getServletContext().getRealPath("C:\\output1\\1.pdf");
        //Path file = Paths.get(dataDirectory, fileName);
        //if (Files.exists(file))
        {
            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            try
            {
               // Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }*/
		return null;//transactionService.downloadFilebyID(request,response,transactionId);
	}

}