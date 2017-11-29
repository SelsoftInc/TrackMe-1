package com.selsoft.commonutility.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="com.selsoft")
public class TrackMeStarter extends SpringApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(TrackMeStarter.class);
	}

}
