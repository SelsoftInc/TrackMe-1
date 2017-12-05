package com.selsoft.trackme.controller;

import java.io.IOException;

import java.util.List;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.selsoft.trackme.model.Tenant;
import com.selsoft.trackme.model.Transaction;
import com.selsoft.trackme.model.ValidError;
import com.selsoft.trackme.service.TransactionService;

@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {

	private static final Logger logger = Logger.getLogger(TransactionController.class);
	// private static TransactionType transactionType = new TransactionType();

	@Autowired
	private TransactionService transactionService;

	@RequestMapping(value = "/saveTransaction", method = RequestMethod.PUT)
	public void saveTransaction(@RequestBody Transaction transaction) {

		logger.info(transaction.getTransactionId()
				+ " data comes into TransactionController saveTransaction() for processing");

		ValidError validError=  transactionService.validateTransactionData(transaction);
		if(validError==null) {
			transactionService.saveTransaction(transaction);
		}

	}
	
	 @GetMapping("/")
     public String index() {
         return "upload";
     }


    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
    	
    	String UPLOADED_FOLDER = "D://temp//";

             if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

	
	@RequestMapping(value = "/ getTransaction", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction>   getTransaction(long  transactionId) {
		return transactionService. getTransaction(transactionId);
	}

}
