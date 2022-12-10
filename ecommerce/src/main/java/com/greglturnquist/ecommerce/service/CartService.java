package com.greglturnquist.ecommerce.service;

import org.springframework.stereotype.Service;

import com.greglturnquist.ecommerce.domain.Cart;
import com.greglturnquist.ecommerce.domain.CartItem;
import com.greglturnquist.ecommerce.repository.CartRepository;
import com.greglturnquist.ecommerce.repository.ItemRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class CartService {

	private final ItemRepository itemRepository;
	private final CartRepository cartRepository;

	public Mono<Cart> addToCart(String cartId, String id) {
		return this.cartRepository.findById(cartId)
			.defaultIfEmpty(new Cart(cartId))
			.flatMap(cart -> cart.getCartItems().stream()
				.filter(cartItem -> cartItem.getItem()
					.getId().equals(id))
				.findAny()
				.map(cartItem -> {
					cartItem.increment();
					return Mono.just(cart);
				})
				.orElseGet(() ->
					this.itemRepository.findById(id)
						.map(CartItem::new)
						.map(cartItem -> cart.getCartItems().add(cartItem))// add를 map으로도 할 수 있구나
						.map(cartItem -> cart)))
			.flatMap(this.cartRepository::save);
	}
}
