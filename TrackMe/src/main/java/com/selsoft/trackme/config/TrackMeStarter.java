package com.selsoft.trackme.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaServer
@EnableDiscoveryClient
@ComponentScan(basePackages="com.selsoft")
public class TrackMeStarter extends SpringApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(TrackMeStarter.class);
	}

}
