package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import constant.Constant.OrderSelectionMode;
import constant.Constant.OrderState;
import model.Order;

public class OrderRepo {
	private static OrderRepo orderRepo=null;
	private int seq = 1;

	private List<Order> orders;
	
	private OrderRepo() {
		if(orders==null) {
			orders = new ArrayList<>();
		}
	}
	
	// Singleton implementation 
	public static synchronized OrderRepo getInstance() {
		if(orderRepo==null) {
			orderRepo = new OrderRepo();
		}
		return orderRepo;
	}

	public Order addOrder(String custName, Map<String, Integer> items, OrderSelectionMode strategy) {
		Order order = new Order(seq++, custName, items, strategy.name(), OrderState.PENDING.name());
		orders.add(order);
		return order;
	}

	public List<Order> getOrders() {
		return orders;
	}
	
	
}
