package com.selsoft.quartz;

import java.util.Date;

import org.quartz.JobBuilder;

import org.quartz.JobDetail;

import org.quartz.Scheduler;

import org.quartz.SimpleScheduleBuilder;

import org.quartz.Trigger;

import org.quartz.TriggerBuilder;

import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerExample {

	public static void main(String[] args) throws Exception {

		JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("jobScheduling", "selsoftInc").build();
		Trigger trigger = TriggerBuilder

				.newTrigger().withIdentity("jobTrigger", "selsoftInc")

				.withSchedule(SimpleScheduleBuilder.simpleSchedule()

						.withIntervalInSeconds(5).repeatForever())

				.build();

		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();

		scheduler.start();
		scheduler.scheduleJob(job, trigger);

	}

}
