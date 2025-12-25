package pojo;

import java.util.List;

import model.MenuItem;
import model.Restaurant;

public class RestaurantDTO {
	private int restID;
	private String restName;
	private int currentOrder;
	private int maxNoOfOrder;
	private double rating;
	private List<MenuItem> menuItems;
	
	public RestaurantDTO(Restaurant rest, List<MenuItem> menuItems) {
		this.restID = rest.getRestID();
		this.restName = rest.getRestName();
		this.currentOrder = rest.getCurrentOrder();
		this.maxNoOfOrder = rest.getMaxNoOfOrder();
		this.rating = rest.getRating();
		this.menuItems = menuItems;
	}
	public int getRestID() {
		return restID;
	}
	public void setRestID(int restID) {
		this.restID = restID;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public int getMaxNoOfOrder() {
		return maxNoOfOrder;
	}
	public void setMaxNoOfOrder(int maxNoOfOrder) {
		this.maxNoOfOrder = maxNoOfOrder;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public List<MenuItem> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}
	public int getCurrentOrder() {
		return currentOrder;
	}
	public void setCurrentOrder(int currentOrder) {
		this.currentOrder = currentOrder;
	}
}
