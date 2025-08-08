package com.fooddeliverysystem.services;
import java.util.Map;

import com.fooddeliverysystem.entities.Customer;
import com.fooddeliverysystem.entities.DeliveryPerson;
import com.fooddeliverysystem.entities.Order;
public class OrderService {

    private Map<Integer, Order> orders;
    private Map<Integer, Customer> customers;
    private Map<Integer, DeliveryPerson> deliveryPersons;
    private int orderCounter = 1;

    public OrderService(Map<Integer, Order> orders, Map<Integer, Customer> customers, Map<Integer, DeliveryPerson> deliveryPersons) {
        this.orders = orders;
        this.customers = customers;
        this.deliveryPersons = deliveryPersons;
    }

    public void placeOrder(int customerId) {
        Customer customer = customers.get(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        if (customer.getCart().getItems().isEmpty()) {
            System.out.println("Cart is empty. Cannot place order.");
            return;
        }

        Order order = new Order(orderCounter++, customer);
        orders.put(order.getOrderId(), order);
        customer.getCart().getItems().clear();
        System.out.println("Order placed successfully! Your order ID is: " + order.getOrderId());
    }

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

    public void viewAllOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        for (Order o : orders.values()) {
            System.out.println(o);
        }
    }

    public void assignDeliveryPerson(int orderId, int deliveryPersonId) {
        Order order = orders.get(orderId);
        DeliveryPerson dp = deliveryPersons.get(deliveryPersonId);

        if (order == null) {
            System.out.println("Order not found!");
            return;
        }

        if (dp == null) {
            System.out.println("Delivery person not found!");
            return;
        }

        order.setDeliveryperson(dp);
        System.out.println("Delivery person assigned successfully!");
    }

    public void addDeliveryPerson(int id, String name, long contactNo) {
        if (deliveryPersons.containsKey(id)) {
            System.out.println("Delivery person already exists!");
        } else {
            deliveryPersons.put(id, new DeliveryPerson(id, name, contactNo));
            System.out.println("Delivery person added successfully!");
        }
    }
}
