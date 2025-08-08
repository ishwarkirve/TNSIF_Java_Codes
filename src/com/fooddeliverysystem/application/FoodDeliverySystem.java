package com.fooddeliverysystem.application;
import java.util.*;

import com.fooddeliverysystem.entities.Customer;
import com.fooddeliverysystem.entities.DeliveryPerson;
import com.fooddeliverysystem.entities.FoodItem;
import com.fooddeliverysystem.entities.Order;
import com.fooddeliverysystem.entities.Restaurant;
import com.fooddeliverysystem.services.CustomerService;
import com.fooddeliverysystem.services.OrderService;

public class FoodDeliverySystem {
	public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Shared Data Storage
        Map<Integer, Restaurant> restaurants = new HashMap<>();
        Map<Integer, Customer> customers = new HashMap<>();
        Map<Integer, DeliveryPerson> deliveryPersons = new HashMap<>();
        Map<Integer, Order> orders = new HashMap<>();

        // Services
        CustomerService customerService = new CustomerService(customers, restaurants);
        OrderService orderService = new OrderService(orders, customers, deliveryPersons);

        while (true) {
            System.out.println("\n1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int mainChoice = sc.nextInt();

            if (mainChoice == 1) {
                boolean adminRunning = true;
                while (adminRunning) {
                    System.out.println("\nAdmin Menu:");
                    System.out.println("1. Add Restaurant");
                    System.out.println("2. Add Food Item to Restaurant");
                    System.out.println("3. Remove Food Item from Restaurant");
                    System.out.println("4. View Restaurants and Menus");
                    System.out.println("5. View Orders");
                    System.out.println("6. Add Delivery Person");
                    System.out.println("7. Assign Delivery Person to Order");
                    System.out.println("8. Exit");

                    System.out.print("Choose an option: ");
                    int adminChoice = sc.nextInt();

                    switch (adminChoice) {
                        case 1:
                            System.out.print("Enter Restaurant ID: ");
                            int restId = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Restaurant Name: ");
                            String restName = sc.nextLine();
                            restaurants.put(restId, new Restaurant(restId, restName));
                            System.out.println("Restaurant added successfully!");
                            break;

                        case 2:
                            System.out.print("Enter Restaurant ID: ");
                            int restId2 = sc.nextInt();
                            System.out.print("Enter Food Item ID: ");
                            int foodId = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Food Item Name: ");
                            String foodName = sc.nextLine();
                            System.out.print("Enter Food Item Price: ");
                            double price = sc.nextDouble();
                            Restaurant r = restaurants.get(restId2);
                            if (r != null) {
                                r.addFoodItem(new FoodItem(foodId, foodName, price));
                                System.out.println("Food item added successfully!");
                            } else {
                                System.out.println("Restaurant not found.");
                            }
                            break;

                        case 3:
                            System.out.print("Enter Restaurant ID: ");
                            int rId = sc.nextInt();
                            System.out.print("Enter Food Item ID to remove: ");
                            int removeId = sc.nextInt();
                            Restaurant r3 = restaurants.get(rId);
                            if (r3 != null) {
                                r3.removeFoodItem(removeId);
                                System.out.println("Food item removed.");
                            } else {
                                System.out.println("Restaurant not found.");
                            }
                            break;

                        case 4:
                            customerService.viewRestaurantsAndMenus();
                            break;

                        case 5:
                            orderService.viewAllOrders();
                            break;

                        case 6:
                            System.out.print("Enter Delivery Person ID: ");
                            int dpId = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Delivery Person Name: ");
                            String dpName = sc.nextLine();
                            System.out.print("Enter Contact No.: ");
                            long dpContact = sc.nextLong();
                            orderService.addDeliveryPerson(dpId, dpName, dpContact);
                            break;

                        case 7:
                            System.out.print("Enter Order ID: ");
                            int ordId = sc.nextInt();
                            System.out.print("Enter Delivery Person ID: ");
                            int assignDpId = sc.nextInt();
                            orderService.assignDeliveryPerson(ordId, assignDpId);
                            break;

                        case 8:
                            adminRunning = false;
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }
                }
            } else if (mainChoice == 2) {
                boolean customerRunning = true;
                while (customerRunning) {
                    System.out.println("\nCustomer Menu:");
                    System.out.println("1. Add Customer");
                    System.out.println("2. View Food Items");
                    System.out.println("3. Add Food to Cart");
                    System.out.println("4. View Cart");
                    System.out.println("5. Place Order");
                    System.out.println("6. View Orders");
                    System.out.println("7. Exit");

                    System.out.print("Choose an option: ");
                    int customerChoice = sc.nextInt();

                    switch (customerChoice) {
                        case 1:
                            System.out.print("Enter User ID: ");
                            int custId = sc.nextInt();
                            sc.nextLine();
                            System.out.print("Enter Username: ");
                            String custName = sc.nextLine();
                            System.out.print("Enter Contact No.: ");
                            long contact = sc.nextLong();
                            customerService.addCustomer(custId, custName, contact);
                            break;

                        case 2:
                            customerService.viewRestaurantsAndMenus();
                            break;

                        case 3:
                            System.out.print("Enter Customer ID: ");
                            int custId3 = sc.nextInt();
                            System.out.print("Enter Restaurant ID: ");
                            int restId3 = sc.nextInt();
                            System.out.print("Enter Food Item ID: ");
                            int foodId3 = sc.nextInt();
                            System.out.print("Enter Quantity: ");
                            int qty = sc.nextInt();
                            customerService.addToCart(custId3, restId3, foodId3, qty);
                            break;

                        case 4:
                            System.out.print("Enter Customer ID: ");
                            int custId4 = sc.nextInt();
                            customerService.viewCart(custId4);
                            break;

                        case 5:
                            System.out.print("Enter Customer ID: ");
                            int custId5 = sc.nextInt();
                            orderService.placeOrder(custId5);
                            break;

                        case 6:
                            System.out.print("Enter Customer ID: ");
                            int custId6 = sc.nextInt();
                            orderService.viewCustomerOrders(custId6);
                            break;

                        case 7:
                            customerRunning = false;
                            break;

                        default:
                            System.out.println("Invalid option.");
                    }
                }
            } else if (mainChoice == 3) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid option.");
            }
        }

        sc.close();
    }
}
