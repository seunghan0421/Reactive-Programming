package com.greglturnquist.ecommerce.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@EqualsAndHashCode
public class CartItem {

	private Item item;
	private int quantity;

	public CartItem(Item item) {
		this.item = item;
		this.quantity = 1;
	}
}
