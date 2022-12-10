package com.greglturnquist.ecommerce.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
public class Cart {

	private @Id String id;
	private List<CartItem> cartItems;

	public Cart(String id) {
		this(id, new ArrayList<>());
	}
}
