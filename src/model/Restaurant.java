package model;

public class Restaurant {
	private int restID;
	
	private String restName;
	private int currentOrder = 0;
	private int maxNoOfOrder;
	private double rating;
	
	public Restaurant(String restName, int maxNoOfOrder, double rating) {
		super();
		this.restName = restName;
		this.maxNoOfOrder = maxNoOfOrder;
		this.rating = rating;
		this.currentOrder =0;
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

	public int getCurrentOrder() {
		return currentOrder;
	}

	public void setCurrentOrder(int currentOrder) {
		this.currentOrder = currentOrder;
	}

	@Override
	public String toString() {
		return "Restaurant [restID=" + restID + ", restName=" + restName + ", maxNoOfOrder=" + maxNoOfOrder
				+ ", rating=" + rating + "]";
	}

}
