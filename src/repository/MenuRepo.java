package repository;

import java.util.ArrayList;
import java.util.List;

import model.MenuItem;

public class MenuRepo {
	
	private static MenuRepo menuRepo=null;
	private int seq = 1;

	private List<MenuItem> menuItems;
	
	private MenuRepo() {
		if(menuItems==null) {
			menuItems = new ArrayList<>();
		}
	}
	
	// Singleton implementation 
	public static synchronized MenuRepo getInstance() {
		if(menuRepo==null) {
			menuRepo = new MenuRepo();
		}
		return menuRepo;
	}
	
	public void addMenuItem(MenuItem menuItem) {
		menuItem.setItemId(seq++);
		this.menuItems.add(menuItem);
	}
	public void removeMenuItem(MenuItem menuItem) {
		if(menuItem !=null)
			this.menuItems.remove(menuItem);
	}
	public void removeMenuItemsByRestaurantId(int restId) {
		this.menuItems.removeIf(item -> item.getRestId() == restId);
	}
	public List<MenuItem> getMenuByRestaurantId(int restId) {
		return this.menuItems.parallelStream().filter(item ->
			        item.getRestId() == restId
				).toList();
	}
	public MenuItem getMenuByMenuId(int itemId) {
		return this.menuItems.parallelStream().filter(item ->
			        item.getItemId() == itemId
				).findAny().orElse(null);
	}
	public MenuItem getMenuByMenuIdandResturantId(int itemId, int restId) {
		return this.menuItems.parallelStream().filter(item ->
			        item.getItemId() == itemId && item.getRestId() == restId
				).findAny().orElse(null);
	}
	public boolean isPresentMenuItem(int restId, int itemId) {
		return this.menuItems.parallelStream().filter(item -> 
					item.getRestId() == restId && item.getItemId() == itemId
				).count() > 0;
	}
	
}
