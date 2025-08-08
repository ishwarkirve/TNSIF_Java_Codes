package com.fooddeliverysystem.entities;
import java.util.Map;

public class Order {
	private int orderId;
	private Customer customer;
	private Map<FoodItem,Integer> items;
	private String status;
	private DeliveryPerson deliveryperson;
	private String deliveryAddress;
	
	public Order(int orderId, Customer customer) {
		super();
		this.orderId = orderId;
		this.customer = customer;
	}
	
	public int getOrderId() {
		return orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Map<FoodItem, Integer> getItems() {
		return items;
	}

	public String getStatus() {
		return status;
	}

	public DeliveryPerson getDeliveryperson() {
		return deliveryperson;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setItems(Map<FoodItem, Integer> items) {
		this.items = items;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setDeliveryperson(DeliveryPerson deliveryperson) {
		this.deliveryperson = deliveryperson;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customer=" + customer + ", items=" + items + ", status=" + status
				+ ", deliveryperson=" + (deliveryperson != null ? deliveryperson.getName() : "Not Assigned") + ", deliveryAddress=" + deliveryAddress + "]";
	}
	
	public void addItem(FoodItem foodItem,int quantity) {
		items.put(foodItem, quantity);
	}
}
