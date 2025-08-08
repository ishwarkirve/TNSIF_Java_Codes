package com.fooddeliverysystem.services;
import java.util.Map;

import com.fooddeliverysystem.entities.Customer;
import com.fooddeliverysystem.entities.FoodItem;
import com.fooddeliverysystem.entities.Restaurant;
public class CustomerService {

    private Map<Integer, Customer> customers;
    private Map<Integer, Restaurant> restaurants;

    public CustomerService(Map<Integer, Customer> customers, Map<Integer, Restaurant> restaurants) {
        this.customers = customers;
        this.restaurants = restaurants;
    }

    public void addCustomer(int id, String name, long contactNo) {
        if (customers.containsKey(id)) {
            System.out.println("Customer ID already exists!");
        } else {
            customers.put(id, new Customer(id, name, contactNo));
            System.out.println("Customer created successfully!");
        }
    }

    public void viewRestaurantsAndMenus() {
        if (restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
            return;
        }
        for (Restaurant r : restaurants.values()) {
            System.out.println(r);
        }
    }

    public void addToCart(int customerId, int restaurantId, int foodItemId, int quantity) {
        Customer customer = customers.get(customerId);
        Restaurant restaurant = restaurants.get(restaurantId);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        if (restaurant == null) {
            System.out.println("Restaurant not found!");
            return;
        }

        for (FoodItem item : restaurant.getMenu()) {
            if (item.getId() == foodItemId) {
                customer.getCart().addItem(item, quantity);
                System.out.println("Food item added to cart!");
                return;
            }
        }

        System.out.println("Food item not found in menu.");
    }

    public void viewCart(int customerId) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
        } else {
            System.out.println(customer.getCart());
        }
    }
}
