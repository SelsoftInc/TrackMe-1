package com.selsoft.lease.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
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

	@RequestMapping(value = "/createLease", method = RequestMethod.POST)
	public ValidError createLease(@RequestBody Lease lease,@RequestParam("file") MultipartFile file) {

		ValidError error = leaseService.validateNewLeaseData(lease);
		

		LeaseDto leaseDto = null;
		//Lease lease = null;

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
		/*try {
			//String content=new String(txnContent.getBytes());
			leaseDto = mapper.readValue(content, LeaseDto.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	   logger.info(LeaseDto.getLeaseId()
				+ " data comes into TransactionController saveTransaction() for processing");
		try {
			LeaseDto.setFile(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		String filePath = "C:\\output2";
		String fileName = file.getOriginalFilename();
		String absolutePath=filePath+"\\"+fileName;
		try {
			
			File localFile=new File(absolutePath);
			file.transferTo(localFile);
			lease=new Lease();
			lease.setLeaseId(leaseDto.getLeaseId());
			lease.setPropertyName(leaseDto.getPropertyName());
			lease.setOwnerId(leaseDto.getOwnerId());
			lease.setOwnerFirstName(leaseDto.getOwnerFirstName());
			lease.setOwnerLastName(leaseDto.getOwnerLastName());
			lease.setTenantId(leaseDto.getTenantId());
			lease.setTenantFirstName(leaseDto.getTenantFirstName());
			lease.setTenantLastName(leaseDto.getTenantLastName());
			lease.setAdditionalTenant(leaseDto.getAdditionalTenant());
			lease.setRentalId(leaseDto.getRentalId());
			lease.setPropertyId(leaseDto.getPropertyId());
			lease.setLeaseType(leaseDto.getLeaseType());
			lease.setLeaseStartDate(leaseDto.getLeaseStartDate());
			lease.setLeaseEndDate(leaseDto.getLeaseEndDate());
			lease.setTenure(leaseDto.getTenure());
			lease.setPropertyManagerId(leaseDto.getPropertyManagerId());
			lease.setRentalDetail(leaseDto.getRentalDetail());
			lease.setFilePath(absolutePath);
		} catch (IOException e) {

			e.printStackTrace();

		}

     	logger.info(lease.getPropertyId() + " data comes into LeaseControllercreateLease() for processing");
		if (error == null) {
			leaseService.createLease(lease);
			error = new ValidError(ErrorConstants.ERROR109, ErrorConstants.ERRROR109_MESSAGE);
		}
		return error;

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

	@RequestMapping(value = "/upload/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resource> uploadFilebyID(@PathParam("id") String leaseId, HttpServletResponse response)
			throws IOException {

		String fileName = leaseService.getFileNameById(leaseId);

		File uploadFile = new File("");
		String filename = uploadFile.getName();
		String fileExt = FilenameUtils.getExtension(filename);
		String mimeType = "";
		InputStream uploadedInputStream=null;
		try {
			FileOutputStream out = new FileOutputStream(new File(fileExt));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(fileExt));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String output = "File successfully uploaded to : " + fileExt;
		
		InputStreamResource resource = new InputStreamResource(new FileInputStream(fileExt));
			return ResponseEntity.ok().contentLength(output.length())
				.contentType(MediaType.parseMediaType(mimeType)).body(resource);
	}

	

	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Resource> downloadFilebyID(@PathParam("id") String leaseId, HttpServletResponse response)
			throws IOException {
		String fileName = leaseService.getFileNameById(leaseId);

		File file = new File("C:\\downloadfile\\2.pdf");
		FileInputStream fileIn = new FileInputStream(file);
		ServletOutputStream out = response.getOutputStream();

		byte[] outputByte = new byte[4096];
		// copy binary contect to output stream
		while (fileIn.read(outputByte, 0, 4096) != -1) {
			out.write(outputByte, 0, 4096);
		}
		fileIn.close();
		out.flush();
		out.close();

		return null;
	}
}
