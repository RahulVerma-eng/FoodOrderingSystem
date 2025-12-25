package service.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import constant.Constant.OrderSelectionMode;
import constant.Constant.OrderState;
import model.MenuItem;
import model.Order;
import model.Restaurant;
import pojo.OrderAssignmentResultDTO;
import pojo.RestaurantDTO;
import repository.OrderRepo;
import service.OrderService;
import service.RestaurantService;

public class OrderServiceImpl implements OrderService{

	final private OrderRepo orderRepo = OrderRepo.getInstance();
	final private RestaurantService restService = new RestaurantServiceImpl();
	
	@Override
	public String placeOrder(String custName, Map<String, Integer> items, OrderSelectionMode strategy) {
		try {
			Order order = orderRepo.addOrder(custName, items, strategy);
			List<RestaurantDTO> restDto = restService.getAllRestaurants();
	//		List<Integer> restList = restDto.stream().filter(r -> r.getMenuItems().stream().filter())
			Set<String> itemnames = items.keySet();
	//		List<RestaurantDTO> rt = restDto.parallelStream().filter(r -> r.getMenuItems().parallelStream()
	//																	.filter(i -> itemnames.contains(i.getItemName()))
	//																	.count()>0
	//														).toList();
			List<RestaurantDTO> rtemp = new ArrayList<>();
			for(RestaurantDTO r: restDto) {
				boolean ispresent = false;
				for(String i: itemnames) {
					ispresent = r.getMenuItems().stream().filter(item-> item.getItemName().equals(i)).findFirst().isPresent();
					if(!ispresent) {
						break;
					}
				}
				if(ispresent)
					rtemp.add(r);
			}
			if(rtemp.isEmpty()) {
				return "No such restaurant exist with given orders";
			}
			OrderAssignmentResultDTO selected = null;
			if(strategy.equals(OrderSelectionMode.HIGHEST_RATING)) {
				selected = highestRatingRestaurantSelectionStrategy(items, rtemp);
			}
			else {
				selected = LowestPriceOrderRestaurantSelectionStrategy(items, rtemp);
			}
			order.setRestId(selected.getRestDTO().getRestID());
			return "Order is placed at "+ selected.getRestDTO().getRestName() + ". Your Total bill amount is " + selected.getPrice();
		} catch(Exception e) {
			e.printStackTrace();
			return "Unable to Place Order";
		}
	}

	private OrderAssignmentResultDTO LowestPriceOrderRestaurantSelectionStrategy(Map<String, Integer> items, List<RestaurantDTO> rtemp) throws Exception {
		double maxRating=0;
		double totalprice=Double.MAX_VALUE;
		RestaurantDTO selecteRest = null;
		for(RestaurantDTO r: rtemp) {
			if(r.getMaxNoOfOrder()>r.getCurrentOrder()) {
				double price = 0;
				for(Map.Entry<String, Integer> i: items.entrySet()) {
					MenuItem gotItem  = r.getMenuItems().stream().filter(item-> item.getItemName().equals(i.getKey())).findFirst().get();
					price += gotItem.getItemPrice()*i.getValue();
				}
				if(price < totalprice) {
					selecteRest = r;
					totalprice = price;
					maxRating = r.getRating();
				}
				else if(price == totalprice) {
					if(maxRating < r.getRating()) {
						selecteRest = r;
						totalprice = price;
						maxRating = r.getRating();
					}
				}
			}
		}
		// updaing selected current order in restaurant
//		selecteRest.setCurrentOrder(selecteRest.getCurrentOrder()+1);
		restService.increementCurrentOrder(selecteRest.getRestID());
		return new OrderAssignmentResultDTO(selecteRest, totalprice);
	}

	// select 
	private  OrderAssignmentResultDTO highestRatingRestaurantSelectionStrategy(Map<String, Integer> items, List<RestaurantDTO> rtemp) throws Exception {
		double maxRating=0;
		double totalprice=0;
		RestaurantDTO selecteRest = null;
		for(RestaurantDTO r: rtemp) {
			if(r.getMaxNoOfOrder()>r.getCurrentOrder()) {
				if(maxRating < r.getRating()) {
					maxRating = r.getRating();
					for(Map.Entry<String, Integer> i: items.entrySet()) {
						MenuItem gotItem  = r.getMenuItems().stream().filter(item-> item.getItemName().equals(i.getKey())).findFirst().get();
						totalprice += gotItem.getItemPrice()*i.getValue();
					}
					selecteRest = r;
				}
				else if(maxRating == r.getRating()) {
					double price = 0;
					for(Map.Entry<String, Integer> i: items.entrySet()) {
						MenuItem gotItem  = r.getMenuItems().stream().filter(item-> item.getItemName().equals(i.getKey())).findFirst().get();
						price += gotItem.getItemPrice()*i.getValue();
					}
					if(price<totalprice) {
						totalprice = price;
						selecteRest = r;
					}
				}
			}
		}
		// updaing selected current order in restaurant
		selecteRest.setCurrentOrder(selecteRest.getCurrentOrder()+1);
		restService.increementCurrentOrder(selecteRest.getRestID());
		return new OrderAssignmentResultDTO(selecteRest, totalprice);
	}

	@Override
	public List<Order> listOrders() {
		List<Order> orders = orderRepo.getOrders();
		return orders;
	}

	@Override
	public void updateOrder(int orderId, OrderState accepted) {
		Order order = orderRepo.getOrders().stream().filter(o-> o.getOrderId()==orderId).findFirst().orElse(null);
		if(order!=null) {
			order.setOrderStatus(accepted.name());
//			System.out.println(order.getRestId());
			Restaurant restaurant = restService.getRestaurantByRestaurantId(order.getRestId());
			if(restaurant !=null && restaurant.getCurrentOrder()>0)
				restaurant.setCurrentOrder(restaurant.getCurrentOrder()-1);
		}
	}

}
