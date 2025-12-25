package service;

import java.util.List;
import java.util.Map;

import constant.Constant.OrderSelectionMode;
import constant.Constant.OrderState;
import model.Order;

public interface OrderService {

	String placeOrder(String custName, Map<String, Integer> items, OrderSelectionMode strategy);

	List<Order> listOrders();

	void updateOrder(int orderId, OrderState accepted);

}
