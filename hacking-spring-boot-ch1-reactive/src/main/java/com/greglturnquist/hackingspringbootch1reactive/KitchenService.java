package com.greglturnquist.hackingspringbootch1reactive;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class KitchenService {

	private Random picker = new Random();

	private List<Dish> menu = Arrays.asList(
		new Dish("Sesame chicken"),
		new Dish("Lo mein noodles, plain"),
		new Dish("Sweet & sour beef")
	);

	/**
	 * 요리 스트림 생성
	 */
	public Flux<Dish> getDishes() {
		return Flux.<Dish>generate(sink -> sink.next(randomDish()))
			.delayElements(Duration.ofMillis(250));
	}

	/**
	 * 요리 무작위 선택
	 */
	private Dish randomDish() {
		return menu.get(picker.nextInt(menu.size()));
	}

}
