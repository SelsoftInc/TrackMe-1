package com.selsoft.auto.config;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.selsoft.auto.job.AutoBillingJob;

@SpringBootApplication(scanBasePackages="com.selsoft")
public class JobStarter {
	private static final Logger logger = Logger.getLogger(JobStarter.class);

	public static void main(String[] args) throws SchedulerException {
		
		SpringApplication.run(JobStarter.class, args);
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		String currentDate = df.format(calendar.getTime());

		logger.info("\n ------::::AUTOBILLING:::::-------"
				+ "\n:::::::::::::::::::::::::::::::::::"
				+ "\n::::::: CURRENT DATE  :::::::::::::"
				+ "\n:::     "+currentDate+"   :::::"
				+ "\n -------------:::::::::------------");
		
		JobDetail job = JobBuilder.newJob(AutoBillingJob.class).withIdentity("autoJob", "selsoftInc").build();

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("jobTrigger", "selsoftInc")
//				.withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 20 * ?")).build();
				.withSchedule(CronScheduleBuilder.cronSchedule("0 41 5 29 * ?")).build();


		// schedule it
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		scheduler.scheduleJob(job, trigger);

	}
}