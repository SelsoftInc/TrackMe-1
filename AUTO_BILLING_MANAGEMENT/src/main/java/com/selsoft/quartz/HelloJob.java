package com.selsoft.quartz;
import org.slf4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.selsoft.demo.service.AutoBillingService;
public class HelloJob implements Job {
	@Autowired
	private AutoBillingService autoBillingService;
	
		 @Override
	        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
			 final Logger logger = LoggerFactory.getLogger(SimpleTriggerExample.class);
			    logger.info("Hello Quartz!");	
			    autoBillingService.manageBilling();
	         		 }	
		}


