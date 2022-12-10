package com.greglturnquist.ecommerce.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.greglturnquist.ecommerce.domain.Cart;

public interface CartRepository extends ReactiveCrudRepository<Cart, String> {
}
