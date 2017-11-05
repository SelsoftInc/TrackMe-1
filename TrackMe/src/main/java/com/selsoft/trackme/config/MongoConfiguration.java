package com.selsoft.trackme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */

@Configuration
@ComponentScan(basePackages = { "com.selsoft.trackme.*" })
@PropertySource("classpath:MongoConfig.properties")
public class MongoConfiguration {
	@Autowired
	private Environment env;

	@Bean
	public Mongo mongo() throws Exception {
		return new MongoClient(env.getRequiredProperty("mongodb.url"));
	}

	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), env.getRequiredProperty("mongodb.db"));
	}
}
