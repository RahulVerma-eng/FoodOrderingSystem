package model;

public class MenuItem {
	private int itemId;
	private int restId;
	private String itemName;
	private double itemPrice;
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}
	public MenuItem(int restId, String itemName, double itemPrice) {
		super();
		this.restId = restId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	
	

}
