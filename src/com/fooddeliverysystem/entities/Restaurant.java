package com.fooddeliverysystem.entities;
import java.util.ArrayList;

public class Restaurant {
	private int id;
	private String name;
	private ArrayList<FoodItem> menu = new ArrayList<>();
	
	public Restaurant(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<FoodItem> getMenu() {
		return menu;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("Restaurant Id : "+id+" Restaurant Name : "+name+"\n");
		for(FoodItem item : menu) {
			sb.append(" - ").append(item).append("\n");
		}
		return sb.toString();
	}
	
	public void addFoodItem(FoodItem foodItem) {
		menu.add(foodItem);
	}
	
	public void removeFoodItem(int idx) {
		menu.remove(idx);
	}
}
