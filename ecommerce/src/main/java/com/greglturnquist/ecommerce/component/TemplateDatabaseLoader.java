package com.greglturnquist.ecommerce.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.greglturnquist.ecommerce.domain.Item;

@Component
public class TemplateDatabaseLoader {

	@Bean
	CommandLineRunner initialize(MongoOperations mongo){
		return args -> {
			mongo.save(new Item("Alf alarm clock", 19.99));
			mongo.save(new Item("Smurf TV tray", 24.99));
		};
	}
}
