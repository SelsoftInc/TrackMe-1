package com.selsoft.auto.job;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.selsoft.auto.service.AutoBillingService;

public class AutoBillingJob implements Job{
	@Autowired
	private AutoBillingService autoBillingService;

	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		final Logger logger = LoggerFactory.getLogger(AutoBillingJob.class);
		logger.info(">>>>>> AutoBilling Started at - >>>>>> "+ Calendar.getInstance().getTime());
		autoBillingService.manageBilling();
	}
}


