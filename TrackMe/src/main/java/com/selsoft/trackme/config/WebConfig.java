package com.selsoft.trackme.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 
 * @author Sudhansu Sekhar
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.selsoft.trackme")
public class WebConfig {
	@Autowired
	private MongoDbFactory mongoDbFactory;
	@Autowired
	private MongoConverter mongoConverter;
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory, mongoConverter);
	}

}
