package com.selsoft.trackme.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.core.env.Environment;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

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
		MongoCredential mongoCredential = MongoCredential.createCredential(env.getRequiredProperty("mongodb.username"), 
												env.getRequiredProperty("mongodb.authenticationDatabase"), 
												env.getRequiredProperty("mongodb.password").toCharArray());
		ServerAddress mongoServerAddress = new ServerAddress(env.getRequiredProperty("mongodb.url"));
		List<MongoCredential> mongoCredentialList = new ArrayList<MongoCredential>();
		mongoCredentialList.add(mongoCredential);
		return new MongoClient(mongoServerAddress, mongoCredentialList);
	}
	
	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), env.getRequiredProperty("mongodb.db"));
	}
}
