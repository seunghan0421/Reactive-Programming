package com.greglturnquist.ecommerce.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.greglturnquist.ecommerce.domain.Item;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
}
