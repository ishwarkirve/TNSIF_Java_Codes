package com.fooddeliverysystem.services;
import java.util.*;

import com.fooddeliverysystem.entities.Customer;
import com.fooddeliverysystem.entities.DeliveryPerson;
import com.fooddeliverysystem.entities.FoodItem;
import com.fooddeliverysystem.entities.Order;
import com.fooddeliverysystem.entities.Restaurant;

public class FoodService {
    private Map<Integer, Restaurant> restaurants = new HashMap<>();
    private Map<Integer, Customer> customers = new HashMap<>();
    private Map<Integer, DeliveryPerson> deliveryPersons = new HashMap<>();
    private Map<Integer, Order> orders = new HashMap<>();
    private int orderCounter = 1;

    // - Add Restaurant
    public void addRestaurant(int id,String name) {
    	if(restaurants.containsKey(id)) {
    		System.out.println("Restaurant Already Exists");
    	}else {
    		restaurants.put(id, new Restaurant(id,name));
    		System.out.println("Restaurant Added Successfully.");
    	}
    }
    // - Add Food Item to Restaurant
    public void addFoodItemToRestaurant(int restaurantId,int itemId,String name,double price) {
    	Restaurant restaurant = restaurants.get(restaurantId);
    	if(restaurant != null) {
    		restaurant.addFoodItem(new FoodItem(itemId,name,price));
    		System.out.println("Food Item Added Successfully");
    	}else {
    		System.out.println("Restaurant Not Found");
    	}
    }
    // - Create Customer
    public void addCustomer(int id,String name,long contactNo) {
    	if(!customers.containsKey(id)) {
    		customers.put(id,new Customer(id,name,contactNo));
    		System.out.println("Customer Added Successfully");
    	}else {
    		System.out.println("Customer Already Exists");
    	}
    }
    // - View Menu
    public void viewRestaurantMenus() {
    	if(!restaurants.isEmpty()) {
    		for(Restaurant r : restaurants.values()) {
    			System.out.println(r);
    		}
    	}else {
    		System.out.println("Restaurants Not Available");
    	}
    }
    // - Add to Cart
    public void addToCart(int customerId,int restaurantId,int foodItemId,int quantity) {
    	Customer customer = customers.get(customerId);
    	if(customer == null) {
    		System.out.println("Customer Not Found");
    		return;
    	}
    	
    	Restaurant restaurant = restaurants.get(restaurantId);
    	if(restaurant == null) {
    		System.out.println("Restaurant Not Found");
    		return;
    	}
    	
    	for(FoodItem item : restaurant.getMenu()) {
    		if(item.getId() == foodItemId) {
    			customer.getCart().addItem(item, quantity);
    			System.out.println("Food Item Added To Cart");
    			return;
    		}
    	}
    	
    	System.out.println("Food Item Not Found In Restaurant Menu");
    }
    
    // View Cart
    public void viewCart(int customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            System.out.println(customer.getCart());
        } else {
            System.out.println("Customer not found!");
        }
    }
    
    // - Place Order
    public void placeOrder(int customerId) {
        Customer customer = customers.get(customerId);
        if (customer != null) {
            if (customer.getCart().getItems().isEmpty()) {
                System.out.println("Cart is empty. Add items before placing an order.");
                return;
            }
            Order order = new Order(orderCounter++, customer);
            orders.put(order.getOrderId(), order);
            customer.getCart().getItems().clear(); // clear cart after placing order
            System.out.println("Order placed successfully! Your order ID is: " + order.getOrderId());
        } else {
            System.out.println("Customer not found!");
        }
    }
    // - View Orders
    public void viewCustomerOrders(int customerId) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        boolean found = false;
        for (Order o : orders.values()) {
            if (o.toString().contains(customer.getUsername())) {
                System.out.println(o);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No orders found for this customer.");
        }
    }
    
    // Add Delivery Person
    public void addDeliveryPerson(int id, String name, long contactNo) {
        if (deliveryPersons.containsKey(id)) {
            System.out.println("Delivery Person ID already exists!");
        } else {
            deliveryPersons.put(id, new DeliveryPerson(id, name, contactNo));
            System.out.println("Delivery person added successfully!");
        }
    }
    
    // - Assign Delivery Person
    public void assignDeliveryPersonToOrder(int orderId, int deliveryPersonId) {
        Order order = orders.get(orderId);
        DeliveryPerson dp = deliveryPersons.get(deliveryPersonId);

        if (order == null) {
            System.out.println("Order not found!");
        } else if (dp == null) {
            System.out.println("Delivery person not found!");
        } else {
            order.setDeliveryperson(dp);
            System.out.println("Delivery person assigned to order successfully!");
        }
    }
    
    // View All Orders (Admin)
    public void viewAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders placed yet.");
        } else {
            for (Order o : orders.values()) {
                System.out.println(o);
            }
        }
    }
}
