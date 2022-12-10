package com.greglturnquist.ecommerce.domain;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
public class Cart {

	private @Id String id;
	private List<CartItem> cartItems;

	public Cart(String id, List<CartItem> cartItems) {
		this.id = id;
		this.cartItems = cartItems;
	}
}
