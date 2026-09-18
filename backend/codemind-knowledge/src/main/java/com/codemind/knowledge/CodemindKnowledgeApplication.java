package com.codemind.knowledge;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(
		scanBasePackages = "com.codemind"
)
public class CodemindKnowledgeApplication {


	public static void main(String[] args) {

		SpringApplication.run(
				CodemindKnowledgeApplication.class,
				args
		);

	}

}