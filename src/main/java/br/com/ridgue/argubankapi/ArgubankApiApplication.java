package br.com.ridgue.argubankapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ArgubankApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArgubankApiApplication.class, args);
	}

}
