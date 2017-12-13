package com.selsoft.tenant.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages="com.selsoft.tenant")
public class TrackMeTenantStarter extends SpringApplication {
	
	
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TrackMeTenantStarter.class);
    }
	
	public static void main(String[] args) {
		
		SpringApplication.run(TrackMeTenantStarter.class);
	}
	
}
