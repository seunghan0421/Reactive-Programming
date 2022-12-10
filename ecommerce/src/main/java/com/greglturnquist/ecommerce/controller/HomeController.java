package com.greglturnquist.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.result.view.Rendering;

import com.greglturnquist.ecommerce.domain.Cart;
import com.greglturnquist.ecommerce.domain.CartItem;
import com.greglturnquist.ecommerce.repository.CartRepository;
import com.greglturnquist.ecommerce.repository.ItemRepository;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class HomeController {

	private final ItemRepository itemRepository;
	private final CartRepository cartRepository;

	@GetMapping
	Mono<Rendering> home() {
		return Mono.just(Rendering.view("home.html")
			.modelAttribute("items", this.itemRepository.findAll())
			.modelAttribute("cart",
				this.cartRepository.findById("My Cart").defaultIfEmpty(new Cart("My Cart")))
			.build()
		);
	}

	@PostMapping("/add/{id}")
	Mono<String> addToCart(@PathVariable String id){
		return this.cartRepository.findById("My Cart")
			.defaultIfEmpty(new Cart("My Cart"))
			.flatMap(cart -> cart.getCartItems().stream()
				.filter(cartItem -> cartItem.getItem()
					.getId().equals(id))
				.findAny()
				.map(cartItem -> {
					cartItem.increment();
					return Mono.just(cart);
				})
				.orElseGet(() -> {
					return this.itemRepository.findById(id)
						.map(item -> new CartItem(item))
						.map(cartItem -> {
							cart.getCartItems().add(cartItem);
							return cart;
						});
				}))
			.flatMap(cart -> this.cartRepository.save(cart))
			.thenReturn("redirect:/");
	}

}
