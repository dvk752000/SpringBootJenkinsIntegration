package com.springboot.SpringBootJenkinsIntegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootJenkinsIntegrationApplication implements CommandLineRunner{
	
	@Autowired
    private DataSourceConfiguration dataSourceConfiguration;
	@Autowired ApplicationConfiguration applicationConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJenkinsIntegrationApplication.class, args);
	}
	
	@Override
	public void run(String...args){
        System.out.println("Application.properties has these values for spring.datasource: " + dataSourceConfiguration);
        System.out.println();
    	System.out.println(applicationConfiguration);
    }

}
