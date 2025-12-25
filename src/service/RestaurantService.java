package service;

import java.util.List;

import model.MenuItem;
import model.Restaurant;
import pojo.RestaurantDTO;

public interface RestaurantService {

	public void addRestaurant(String restName, int orderCapacity, double rating);
	public void removeRestaurant(int restId);
	
	public void addMenuItem(int restId, String itemName, double itemPrice);
	public void removeMenuItem(int itemId, int restId);
	public boolean updateMenuItem(int restId, int itemId, String itemName, double itemPrice);
	
	public Restaurant getRestaurantByName(String name);
	public List<RestaurantDTO> getAllRestaurants();
	public RestaurantDTO getRestaurantDTOById(int restId);
	public boolean ifExistResturant(int restId);
	public List<MenuItem> getMenuItemsByRestaurantId(int restId);
	public boolean ifExistMenuItem(int restId, int itemId);
	public void increementCurrentOrder(int restId);
	public Restaurant getRestaurantByRestaurantId(int restId);
	
	
	
}
