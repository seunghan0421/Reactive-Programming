package com.greglturnquist.ecommerce.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Item {

	private @Id String id;
	private String name;
	private double price;

	private Item(){}

	public Item(final String name, final double price) {
		this.name = name;
		this.price = price;
	}
}
