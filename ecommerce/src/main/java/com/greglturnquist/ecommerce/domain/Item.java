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
	private String description;
	private double price;

	private Item(){}

	public Item(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
}
