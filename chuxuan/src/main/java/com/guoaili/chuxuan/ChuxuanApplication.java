package com.guoaili.chuxuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ChuxuanApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ChuxuanApplication.class, args);
	}

}