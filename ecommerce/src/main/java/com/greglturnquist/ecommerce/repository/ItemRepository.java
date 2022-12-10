package com.greglturnquist.ecommerce.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.greglturnquist.ecommerce.domain.Item;

import reactor.core.publisher.Flux;

public interface ItemRepository extends ReactiveCrudRepository<Item, String>, ReactiveQueryByExampleExecutor<Item> {

	Flux<Item> findByNameContaining(String partialName);

	@Query("{'name' :  ?0, 'age' :  ?1}")
	Flux<Item> findItemsForCustomerMontlyReport(String name, int age);

	@Query(sort = "{ 'age' :  -1}")
	Flux<Item> findSortedStuffForWeeklyReport();

}
