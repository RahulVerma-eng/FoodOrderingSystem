package model;

import java.util.Map;

public class Order {
	
	private int orderId;
	private String custName;
	private int restId;
	private Map<String, Integer> itemQty;
	private String orderStrategy;
	private String orderStatus;
	
	public Order(int orderId, String custName, Map<String, Integer> itemQty, String orderStrategy, String orderStatus) {
		this.orderId = orderId;
		this.custName = custName;
		this.itemQty = itemQty;
		this.orderStrategy = orderStrategy;
		this.orderStatus = orderStatus;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public Map<String, Integer> getItemQty() {
		return itemQty;
	}
	public void setItemQty(Map<String, Integer> itemQty) {
		this.itemQty = itemQty;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", custName=" + custName + ", restId=" + restId + ", itemQty=" + itemQty
				+ ", orderStrategy=" + orderStrategy + ", orderStatus=" + orderStatus + "]";
	}
	
}
