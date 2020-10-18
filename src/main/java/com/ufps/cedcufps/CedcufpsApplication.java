package com.ufps.cedcufps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CedcufpsApplication {

		
	public static void main(String[] args) {
		SpringApplication.run(CedcufpsApplication.class, args);
		
	}
	
	

}
