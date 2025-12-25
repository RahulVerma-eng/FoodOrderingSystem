package repository;

import java.util.ArrayList;
import java.util.List;

import model.Restaurant;

public class RestaurantRepo {
	
	private static RestaurantRepo restRepo=null;

	private List<Restaurant> restaurants;
	private static int seq = 0;
	
	private RestaurantRepo() {
		if(restaurants==null) {
			restaurants = new ArrayList<>();
//			seq=1;
		}
	}
	
	// Singleton implementation 
	public static synchronized RestaurantRepo getInstance() {
		if(restRepo==null) {
			restRepo = new RestaurantRepo();
		}
		return restRepo;
	}
	
	public void addRestaurant(Restaurant rest) {
		rest.setRestID(++seq);
//		System.out.println("addrest - "+rest.getRestID());
		this.restaurants.add(rest);
//		this.restaurants.stream().forEach(System.out::println);
	}
	public void removeRestaurant(Restaurant rest) {
		this.restaurants.remove(rest);
	}
	
	public List<Restaurant> getAllRestaurants(){
		return restaurants;
	}

	public Restaurant getRestaurantByRestaurantId(int restId) {
		Restaurant rest = this.restaurants.parallelStream().filter(r ->
			        r.getRestID() == restId
				).findFirst().orElse(null);
		return rest;
	}
	public Restaurant getRestaurantByRestaurantName(String restName) {
		Restaurant rest = this.restaurants.parallelStream().filter(r ->
			        r.getRestName().equalsIgnoreCase(restName)
				).findAny().orElse(null);
		return rest;
	}

	public boolean isPresentRestaurant(int restId) {
		return restaurants.parallelStream().filter(r->
					r.getRestID() == restId
				).count() > 0;
	}
	
}
