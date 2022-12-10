package com.greglturnquist.hackingspringbootch1reactive;

public class Dish {

	private String description;
	private boolean delivered = false;

	public static Dish deliver(Dish dish) {
		Dish deliveredDish = new Dish(dish.description);
		deliveredDish.delivered = true;
		return deliveredDish;
	}

	public Dish(final String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public boolean isDelivered() {
		return delivered;
	}

	@Override
	public String toString() {
		return "Dish{" +
			"description='" + description + '\'' +
			", delivered=" + delivered +
			'}';
	}
}
