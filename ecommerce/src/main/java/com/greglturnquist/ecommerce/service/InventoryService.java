package com.greglturnquist.ecommerce.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import com.greglturnquist.ecommerce.domain.Item;
import com.greglturnquist.ecommerce.repository.ItemRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service
public class InventoryService {

	private final ItemRepository itemRepository;

	public Flux<Item> searchByExample(String name, String description, boolean useAnd) {
		Item item = new Item(name, description, 0.0);

		ExampleMatcher matcher = (useAnd
								  ? ExampleMatcher.matchingAll()
								  : ExampleMatcher.matchingAny())
			.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
			.withIgnoreCase()
			.withIgnorePaths("price");

		Example<Item> probe = Example.of(item, matcher);

		return itemRepository.findAll(probe);
	}
}
