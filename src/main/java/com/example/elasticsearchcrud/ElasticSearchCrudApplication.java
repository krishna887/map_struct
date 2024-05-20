package com.example.elasticsearchcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication

//@ComponentScan("com.example.elasticsearchcrud")
public class ElasticSearchCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchCrudApplication.class, args);
	}



}
