package com.selsoft.lease.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
import com.selsoft.lease.constants.ErrorConstants;
import com.selsoft.lease.constants.LeaseConstants;
import com.selsoft.lease.dto.LeaseDto;
import com.selsoft.lease.model.Lease;
import com.selsoft.lease.model.RentalDetail;
import com.selsoft.lease.model.ValidError;
import com.selsoft.lease.service.LeaseService;

@RestController
@RequestMapping(value = "/lease")
public class LeaseController {

	private static final Logger logger = Logger.getLogger(LeaseController.class);

	// private static LeaseType leaseType = new LeaseType();

	@Autowired
	private LeaseService leaseService;

	@RequestMapping(value = "/createLease", method = RequestMethod.PUT)
	public ValidError createLease(@RequestParam(value = "lease", required = false) MultipartFile leaseContent,
			@RequestParam("file") MultipartFile file) {

		//ValidError error = leaseService.validateNewLeaseData(lease);
		LeaseDto leaseDto = null;
		Lease lease=null;
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
			String content=new String(leaseContent.getBytes());
			leaseDto = mapper.readValue(content, LeaseDto.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		logger.info(leaseDto.getLeaseId()
				+ " data comes into TransactionController saveTransaction() for processing");
		try {
			leaseDto.setFile(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		String filePath = LeaseConstants.FILEPATH;
		String fileName = file.getOriginalFilename();
		String absolutePath=filePath+"\\"+fileName;
		try {

			File localFile = new File(absolutePath);
			file.transferTo(localFile);
			lease = new Lease();
			lease.setLeaseId(leaseDto.getLeaseId());
			lease.setOwnerFirstName(leaseDto.getOwnerFirstName());
			lease.setOwnerLastName(leaseDto.getOwnerLastName());
			lease.setTenantId(leaseDto.getTenantId());
			lease.setDeposit(leaseDto.getDeposit());
			lease.setPropertyId(leaseDto.getPropertyId());
			lease.setRent(leaseDto.getRent());
			lease.setMoveInDate(leaseDto.getMoveInDate());
			lease.setLeaseStatus(leaseDto.getLeaseStatus());
			lease.setPropertyId(leaseDto.getPropertyId());
			
			lease.setLeaseStartDate(leaseDto.getLeaseStartDate());
			lease.setLeaseEndDate(leaseDto.getLeaseEndDate());
			lease.setTenure(leaseDto.getTenure());
			lease.setManagerId(leaseDto.getManagerId());
			
			lease.setFilePath(absolutePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ValidError validError = leaseService.validateNewLeaseData(lease);
		if (validError == null) {
			leaseService.createLease(lease);
			validError = new ValidError(ErrorConstants.ERROR109, ErrorConstants.ERRROR109_MESSAGE);
		}
		return validError;
		
		/*if (error == null) {
			leaseService.createLease(lease);
			error = new ValidError(ErrorConstants.ERROR109, ErrorConstants.ERRROR109_MESSAGE);
		}
		return error;
*/
	}

	// ------------------- save RentalDetail
	// --------------------------------------------------------
	@RequestMapping(value = "/saveRentalDetail", method = RequestMethod.PUT)
	public void saveRentalDetail(@RequestBody RentalDetail rentalDetail, @RequestParam("pId") String propertyId) {

		ValidError validError = leaseService.validateNewRentalData(rentalDetail);

	}

	@RequestMapping(value = "getAllRentalDetails/{propertyId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RentalDetail> getAllRentalDetails(@PathVariable("propertyId") Integer propertyId) {
		return leaseService.getAllRentalDetails(propertyId);
	}

	@RequestMapping(value = "getRentalDetail/{propertyId}/{effectiveDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public RentalDetail getRentalDetail(@PathVariable("propertyId") Integer propertyId,
			@PathVariable("effectiveDate") String inputDate) {

		return leaseService.getRentalDetail(propertyId, inputDate);
	}

	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resource> downloadFilebyID(@PathVariable("id")String leaseId, HttpServletResponse response)throws IOException{

		String fileName = leaseService.getFileNameById(leaseId);
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
