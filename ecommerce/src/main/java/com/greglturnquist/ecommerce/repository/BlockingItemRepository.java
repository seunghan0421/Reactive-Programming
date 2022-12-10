package com.greglturnquist.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.greglturnquist.ecommerce.domain.Item;

public interface BlockingItemRepository extends CrudRepository<Item, String> {
}
