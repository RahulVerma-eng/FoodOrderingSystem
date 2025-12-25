package pojo;

public class OrderAssignmentResultDTO {
	private RestaurantDTO restDTO;
	private double price;
	public OrderAssignmentResultDTO(RestaurantDTO restDTO, double price) {
		super();
		this.restDTO = restDTO;
		this.price = price;
	}
	public RestaurantDTO getRestDTO() {
		return restDTO;
	}
	public void setRestDTO(RestaurantDTO restDTO) {
		this.restDTO = restDTO;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
