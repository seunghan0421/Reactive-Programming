package com.greglturnquist.hackingspringbootch1reactive.restaurant;

import com.greglturnquist.hackingspringbootch1reactive.Dish;
import com.greglturnquist.hackingspringbootch1reactive.KitchenService;

import reactor.core.publisher.Flux;

public class PoliteServer {

	private final KitchenService kitchen;

	public PoliteServer(final KitchenService kitchen) {
		this.kitchen = kitchen;
	}

	Flux<Dish> doingMyJob() {
		return kitchen.getDishes()
			.doOnNext(dish -> System.out.println("Thank you for " + dish + "!"))
			.doOnError(error -> System.out.println("So sorry about " + error.getMessage()))
			.doOnComplete(() -> System.out.println("Thanks for all your hard work!"))
			.map(Dish::deliver);
	}
}
