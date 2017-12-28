package com.selsoft.quartz;

import org.quartz.CronScheduleBuilder;

import org.quartz.JobBuilder;

import org.quartz.JobDetail;

import org.quartz.Scheduler;

import org.quartz.Trigger;

import org.quartz.TriggerBuilder;

import org.quartz.impl.StdSchedulerFactory;

import com.selsoft.quartz.HelloJob;

public class CronTriggerExample {

	public static void main(String[] args) throws Exception {

			JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("jobScheduling", "selsoftInc").build();

			Trigger trigger = TriggerBuilder.newTrigger()

				.withIdentity("jobTrigger", "selsoftInc")

				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 20  ? "))

				.build();

		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();

		scheduler.start();

		scheduler.scheduleJob(job, trigger);

	}

}
