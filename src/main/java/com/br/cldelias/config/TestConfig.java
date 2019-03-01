package com.br.cldelias.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.cldelias.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws Exception {
		this.dbService.instatiateTestDatabase();
		return true;
	}
	
}
