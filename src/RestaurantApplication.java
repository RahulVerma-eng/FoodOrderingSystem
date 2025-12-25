import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import constant.Constant.OrderSelectionMode;
import constant.Constant.OrderState;
import model.MenuItem;
import model.Order;
import model.Restaurant;
import pojo.RestaurantDTO;
import service.OrderService;
import service.RestaurantService;
import service.serviceimpl.OrderServiceImpl;
import service.serviceimpl.RestaurantServiceImpl;

public class RestaurantApplication {

	static RestaurantService restService = new RestaurantServiceImpl();
	static OrderService orderService = new OrderServiceImpl();
	static final Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		defaultCase();
		
		// for menu system uncoment below method and comment above...
		
//        menuOption();
		
	}

	private static void menuOption() {
		while (true) {
            printMenuOption();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                	addRestaurant();
                    break;

                case 2:
                	deleteRestaurant();
                    break;

                case 3:
                	addMenu();
                    break;

                case 4:
					udpateMenu();
                    break;

                case 5:
					deleteMenuItem();
                    break;

                case 6:
                	defaultPrintMenu();
                    break;

                case 7: 
                	goToOrderSection();
                	break;

                case 8: 
                	changeOrderStatus();
                	break;

                case 9: 
                	listOrders();
                	break;

                case 0:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
	}

	private static void defaultCase() {
		defaultAddOperations();
		
		defaultUpdateOperation();
		
		defaultOrderPlacing();
		
		defaultUpdateOrderStatus();
		
	}

	private static void defaultAddOperations() {

        // R1
        restService.addRestaurant("R1", 5, 4.5);
        Restaurant r1 = restService.getRestaurantByName("R1");
        restService.addMenuItem(r1.getRestID(), "Veg Biryani", 100);
        restService.addMenuItem(r1.getRestID(), "Chicken Biryani", 150);
//        System.out.println("r1:"+r1.getRestID());

        // R2
        restService.addRestaurant("R2", 5, 4.0);
        Restaurant r2 = restService.getRestaurantByName("R2");
        restService.addMenuItem(r2.getRestID(), "Idli", 10);
        restService.addMenuItem(r2.getRestID(), "Dosa", 50);
        restService.addMenuItem(r2.getRestID(), "Veg Biryani", 80);
        restService.addMenuItem(r2.getRestID(), "Chicken Biryani", 175);
//        System.out.println("r2:"+r2.getRestID());

        // R3
        restService.addRestaurant("R3", 1, 4.9);
        Restaurant r3 = restService.getRestaurantByName("R3");
        restService.addMenuItem(r3.getRestID(), "Idli", 15);
        restService.addMenuItem(r3.getRestID(), "Dosa", 30);
        restService.addMenuItem(r3.getRestID(), "Gobi Manchurian", 150);
        restService.addMenuItem(r3.getRestID(), "Chicken Biryani", 175);
//        System.out.println("r3:"+r3.getRestID());
        
        
        defaultPrintMenu();
		
	}

	private static void defaultPrintMenu() {
        for (RestaurantDTO rest : restService.getAllRestaurants()) {
            defaultPrintRestaturantDTO(rest);
        }
    }

	private static void defaultPrintSingle(int restId) {
		RestaurantDTO rest = restService.getRestaurantDTOById(restId);
		defaultPrintRestaturantDTO(rest);
	}
	
	private static void defaultPrintRestaturantDTO(RestaurantDTO rest) {
		System.out.println("Name: " + rest.getRestName());
        System.out.println("Max_no_of_orders: " + rest.getMaxNoOfOrder());
        System.out.println("Menu:");

        for (MenuItem item : rest.getMenuItems()) {
            System.out.println(item.getItemId()+ " " + item.getItemName() + " : INR " + item.getItemPrice());
        }

        System.out.println("Rating: " + rest.getRating());
        System.out.println();
	}
		
	private static void defaultUpdateOperation() {
		System.out.println("\nUpdate Restaurant Menu");
		System.out.println("Operation Add");
//		List<MenuItem> items = restService.getMenuItemsByRestaurantId(1);
		restService.addMenuItem(1, "Chicken65", 250);
		
		defaultPrintSingle(1);
		
		System.out.println("\nOperation Update");
		restService.updateMenuItem(2, 6, "Chicken Biryani", 150);
		
		defaultPrintSingle(2);
	}

	private static void defaultOrderPlacing() {
		String custName = "Ashwin";
		Map<String, Integer> items = new HashMap<>();
		items.put("Idli", 3);
		items.put("Dosa", 1);
		OrderSelectionMode strategy = OrderSelectionMode.LOWEST_BILL_COST;
		String output = orderService.placeOrder(custName, items, strategy);
		
		System.out.println(output);

		/*
		User: Ashwin
		Items:
		Idli : 3
		Dosa : 1
		Selection: Lowest Cost
		Output:
		Order assigned to R3
		*/
		
		custName = "Harish";
		items = new HashMap<>();
		items.put("Idli", 3);
		items.put("Dosa", 1);
		strategy = OrderSelectionMode.LOWEST_BILL_COST;
		output = orderService.placeOrder(custName, items, strategy);

		System.out.println(output);
		

		custName = "Shruthi";
		items = new HashMap<>();
		items.put("Veg Biryani", 3);
		strategy = OrderSelectionMode.HIGHEST_RATING;
		output = orderService.placeOrder(custName, items, strategy);

		System.out.println(output);

	}

	private static void defaultUpdateOrderStatus() {
		List<Order> orders= orderService.listOrders();

		orderService.updateOrder(1, OrderState.COMPLETED);
		
		orders.forEach(System.out::println);

		String custName = "Harish";
		Map<String, Integer> items = new HashMap<>();
		items.put("Idli", 3);
		items.put("Dosa", 1);
		OrderSelectionMode strategy = OrderSelectionMode.LOWEST_BILL_COST;
		String output = orderService.placeOrder(custName, items, strategy);
		System.out.println();
		System.out.println(output);
		
		System.out.println();
		custName = "Diya";
		items = new HashMap<>();
		items.put("Idli", 3);
		items.put("Paneer Tikka", 1);
		strategy = OrderSelectionMode.LOWEST_BILL_COST;
		output = orderService.placeOrder(custName, items, strategy);
		System.out.println(output);
	}


	private static void deleteMenuItem() {
		System.out.print("Delete Menu Items");
		System.out.print("Restaurant ID: ");
		int restId = sc.nextInt();
		sc.nextLine();
		
		if(!restService.ifExistResturant(restId)) {
			System.out.println("Restaurant not found");
			return;
		}

		System.out.print("Menu Item List for restid "+restId+": ");
		List<MenuItem> menuitems = restService.getMenuItemsByRestaurantId(restId);

		for (MenuItem item : menuitems) {
            System.out.println(item.getItemId()+" " + item.getItemName() + " : INR " + item.getItemPrice());
        }

		System.out.print("Menu Item to delete");
		int itemId = sc.nextInt();

		restService.removeMenuItem(itemId, restId);
		System.out.println("Menu item removed");
	}

	private static void udpateMenu() {
		System.out.print("Restaurant ID: ");
		int restId = sc.nextInt();
		sc.nextLine();
		
		if(!restService.ifExistResturant(restId)) {
			System.out.println("Restaurant not found");
			return;
		}

		System.out.print("Menu Item List for restid "+restId+": ");
		List<MenuItem> menuitems = restService.getMenuItemsByRestaurantId(restId);

		for (MenuItem item : menuitems) {
            System.out.println(item.getItemId()+" " + item.getItemName() + " : INR " + item.getItemPrice());
        }
		
		System.out.print("Menu Item ID: ");
		int itemId = sc.nextInt();
		sc.nextLine();
		
		if(!restService.ifExistMenuItem(restId, itemId)) {
			System.out.println("Menu Item does not exist");
		}

		System.out.print("New Item Name: ");
		String itemName = sc.nextLine();

		System.out.print("New Price: ");
		double newPrice = sc.nextDouble();

		restService.updateMenuItem(restId, itemId, itemName, newPrice);
		System.out.println("Menu item updated");
	}

	private static void addMenu() {
		System.out.print("Restaurant ID: ");
		int restId = sc.nextInt();
		sc.nextLine();
		
		if(!restService.ifExistResturant(restId)) {
			System.out.println("Restaurant not found");
			return;
		}

		System.out.print("Item Name: ");
		String itemName = sc.nextLine();

		System.out.print("Item Price: ");
		double price = sc.nextDouble();

		restService.addMenuItem(restId, itemName, price);

		System.out.println("Menu item added");
	}

	private static void deleteRestaurant() {
		System.out.print("Enter Restaurant ID to delete: ");
		int restId = sc.nextInt();

		if(!restService.ifExistResturant(restId)) {
			System.out.println("Restaurant not found");
			return;
		}
		restService.removeRestaurant(restId);

		System.out.println("Restaurant and its menu removed");
	}

	private static void addRestaurant() {
		System.out.print("Restaurant Name: ");
		String restName = sc.nextLine();

		System.out.print("Max No Of Orders: ");
		int maxOrders = sc.nextInt();

		System.out.print("Rating: ");
		double rating = sc.nextDouble();

		restService.addRestaurant(restName, maxOrders, rating);

		System.out.println("Restaurant added successfully");
	}

	private static void printMenuOption() {
		System.out.println("\n***Food Ordering System***");
		System.out.println("1. Add Restaurant");
		System.out.println("2. Delete Restaurant");
		System.out.println("3. Add Menu Item");
		System.out.println("4. Update Menu Item");
		System.out.println("5. Delete Menu Item");
		System.out.println("6. View Restaurants");
		System.out.println("7. Place Order");
		System.out.println("8. Update Order Status");
		System.out.println("9. List Order");
		System.out.println("0. Exit");
		System.out.print("Choose option: ");
	}
	
	private static void goToOrderSection() {
		System.out.println(".....Welcome to Order Section.....");
		
		System.out.print("\nCustomer Name:");
		String custName = sc.nextLine();

		Map<String, Integer> items = new HashMap<>();
		System.out.print("Enter number of items: ");
		int itemCount = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < itemCount; i++) {
			System.out.print("Item name: ");
			String itemName = sc.nextLine();

			System.out.print("Quantity: ");
			int qty = sc.nextInt();
			sc.nextLine();

			items.put(itemName, qty);
		}

		System.out.print("Select Strategy:");
		System.out.print("\t1. Lowest Cost");
		System.out.println("\t2. Highest Rating");
		int strategyChoice = sc.nextInt();
		sc.nextLine();

		OrderSelectionMode strategy = strategyChoice == 1 ? 
				OrderSelectionMode.LOWEST_BILL_COST : OrderSelectionMode.HIGHEST_RATING;

		String output = orderService.placeOrder(custName, items, strategy);
		System.out.println(output);
	}

	private static void changeOrderStatus() {
		System.out.print("Enter Order Id: ");
        int orderId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter Order State from PENDING / COMPLETED / CANCELED: ");
        String s = sc.nextLine();
        try {
            OrderState state = OrderState.valueOf(s);
            orderService.updateOrder(orderId, state);
        } catch(Exception e) {
        	System.out.println("Invalid order state entered");
        }
	}
	private static void listOrders() {
		List<Order> orders = orderService.listOrders();
		System.out.println();
		orders.stream().forEach(System.out::println);
	}


}

/*
Onboard Restaurant
Name: R1
Max_no_of_orders: 5
Menu:
Veg Biryani : INR 100
Chicken Biryani : INR 150
Rating: 4.5
Name: R2
Max_no_of_orders: 5
Menu:
Idli : INR 10
Dosa : INR 50
Veg Biryani : INR 80
Chicken Biryani : INR 175
Rating: 4
Name: R3
Max_no_of_orders: 1
Menu:
Idli : INR 15
Dosa : INR 30
Gobi Manchurian : INR 150
Chicken Biryani : INR 175
Rating: 4.9

*/