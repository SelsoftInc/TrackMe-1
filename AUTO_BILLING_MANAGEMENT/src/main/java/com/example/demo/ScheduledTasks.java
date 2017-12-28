package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
    
    @Scheduled(fixedRate = 10000)
	public void performTask() {

    	log.info("Regular task performed at "
				+ dateFormat.format(new Date()));

	}

	@Scheduled(initialDelay = 1000, fixedRate = 10000)
	public void performDelayedTask() {

		log.info("Delayed Regular task performed at "
				+ dateFormat.format(new Date()));

	}

    //@Scheduled(cron = "*/5 * * * * *")
    @Scheduled(cron = "0 0 0 20  ? ")
	public void performTaskUsingCron() {

		log.info("Regular task performed using Cron at "
				+ dateFormat.format(new Date()));

	}
}