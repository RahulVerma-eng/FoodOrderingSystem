package service.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import model.MenuItem;
import model.Restaurant;
import pojo.RestaurantDTO;
import repository.MenuRepo;
import repository.RestaurantRepo;
import service.RestaurantService;

public class RestaurantServiceImpl implements RestaurantService{
	
	final private RestaurantRepo restRepo = RestaurantRepo.getInstance();
	final private MenuRepo menuRepo = MenuRepo.getInstance();

	@Override
	public void addRestaurant(String restName, int orderCapacity, double rating) {
		Restaurant rest = new Restaurant(restName, orderCapacity,rating);
		restRepo.addRestaurant(rest);
	}

	@Override
	public void removeRestaurant(int restId) {
		Restaurant rest = restRepo.getRestaurantByRestaurantId(restId);
		menuRepo.removeMenuItemsByRestaurantId(restId);
		restRepo.removeRestaurant(rest);
	}

	@Override
	public void addMenuItem(int restId, String itemName, double itemPrice) {
		MenuItem item = new MenuItem(restId, itemName, itemPrice);
		menuRepo.addMenuItem(item);
	}

	@Override
	public void removeMenuItem(int itemId, int restId) {
		MenuItem item = menuRepo.getMenuByMenuIdandResturantId(itemId, restId);
		menuRepo.removeMenuItem(item);
	}

	@Override
	public boolean updateMenuItem(int restId, int itemId, String itemName, double itemPrice) {

		MenuItem item = menuRepo.getMenuByMenuIdandResturantId(itemId, restId);
		if(item==null) {
			return false;
		}
		item.setRestId(restId);
		item.setItemName(itemName);
		item.setItemPrice(itemPrice);
		return true;
	}

	@Override
	public Restaurant getRestaurantByName(String name) {
		Restaurant rest = restRepo.getRestaurantByRestaurantName(name);
		return rest;
	}

	@Override
	public List<RestaurantDTO> getAllRestaurants() {
		List<RestaurantDTO> restDtoList = new ArrayList<>();
		
		List<Restaurant> restList = restRepo.getAllRestaurants();
		for(Restaurant r: restList) {
			List<MenuItem> menuList = menuRepo.getMenuByRestaurantId(r.getRestID());
			RestaurantDTO restDto = new RestaurantDTO(r, menuList);
			restDtoList.add(restDto);
		}
		return restDtoList;
	}

	@Override
	public RestaurantDTO getRestaurantDTOById(int restId) {
		Restaurant rest = restRepo.getRestaurantByRestaurantId(restId);
		List<MenuItem> menuList = menuRepo.getMenuByRestaurantId(restId);
		RestaurantDTO restDto = new RestaurantDTO(rest, menuList);
		return restDto;
	}

	@Override
	public boolean ifExistResturant(int restId) {
		return restRepo.isPresentRestaurant(restId);
	}

	@Override
	public List<MenuItem> getMenuItemsByRestaurantId(int restId) {
		return menuRepo.getMenuByRestaurantId(restId);
	}

	@Override
	public boolean ifExistMenuItem(int restId, int itemId) {
		return menuRepo.isPresentMenuItem(restId, itemId);
	}

	@Override
	public void increementCurrentOrder(int restId) {
		Restaurant rest = restRepo.getRestaurantByRestaurantId(restId);
		rest.setCurrentOrder(rest.getCurrentOrder()+1);
	}

	@Override
	public Restaurant getRestaurantByRestaurantId(int restId) {
		Restaurant rest = restRepo.getRestaurantByRestaurantId(restId);
		return rest;
	}
}
