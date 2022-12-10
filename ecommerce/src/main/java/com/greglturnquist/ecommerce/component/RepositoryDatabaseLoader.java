package com.greglturnquist.ecommerce.component;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.greglturnquist.ecommerce.domain.Item;
import com.greglturnquist.ecommerce.repository.BlockingItemRepository;

@Component
public class RepositoryDatabaseLoader {

	@Bean
	CommandLineRunner initialize(BlockingItemRepository repository){
		return args -> {
			repository.save(new Item("Alf alarm clock", 19.99));
			repository.save(new Item("Smurf TV tray", 24.99));
		};
	}
}
