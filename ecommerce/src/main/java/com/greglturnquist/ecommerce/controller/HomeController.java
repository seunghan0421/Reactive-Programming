package com.greglturnquist.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.Rendering;

import com.greglturnquist.ecommerce.domain.Cart;
import com.greglturnquist.ecommerce.domain.CartItem;
import com.greglturnquist.ecommerce.repository.CartRepository;
import com.greglturnquist.ecommerce.repository.ItemRepository;
import com.greglturnquist.ecommerce.service.CartService;
import com.greglturnquist.ecommerce.service.InventoryService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@Controller
@AllArgsConstructor
public class HomeController {

	private final ItemRepository itemRepository;
	private final CartRepository cartRepository;
	private final CartService cartService;
	private final InventoryService inventoryService;

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
	Mono<String> addToCart(@PathVariable String id) {
		return this.cartService.addToCart("My Cart", id)
			.thenReturn("redirect:/");
	}

	@GetMapping("/search")
	Mono<Rendering> search(
		@RequestParam(required = false) String name,
		@RequestParam(required = false) String description,
		@RequestParam boolean useAnd) {
		return Mono.just(Rendering.view("home.html")
			.modelAttribute("items", inventoryService.searchByExample(name, description, useAnd))
			.modelAttribute("cart", this.cartRepository.findById("My Cart")
				.defaultIfEmpty(new Cart("My Cart")))
			.build());
	}

}
