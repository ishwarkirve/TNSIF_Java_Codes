package com.fooddeliverysystem.entities;
import java.util.HashMap;

public class Cart {
	private HashMap<FoodItem,Integer> items = new HashMap<>();

	public Cart() {
		super();
	}
	
	public void addItem(FoodItem foodItem,int quantity) {
		items.put(foodItem, quantity);
	}
	public void removeItem(FoodItem foodItem) {
		items.remove(foodItem);
	}
	public HashMap<FoodItem,Integer> getItems(){
		return items;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Cart :\n");
		double totalCost = 0;
		for(FoodItem item : items.keySet()) {
			double cost = item.getPrice() * items.get(item);
			sb.append("Food Item : ").append(item.getName())
			.append(",Quantity : ").append(items.get(item))
			.append(",Cost : ").append(cost).append("\n");
			totalCost += cost;
		}
		sb.append("Total Cost Rs. ").append(totalCost);
		return sb.toString();
	}
	
	
}
