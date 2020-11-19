package model;

public class Bike {

	private String name;
	private String image;
	private long price;
	private long stock;
	private String description;

	public Bike(String name, String image, long price, String description) {
		super();
		this.name = name;
		this.image = image;
		this.price = price;
		this.description = description;
		this.stock = 5;
	}

	public Bike() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public void decreaseStock() {
		this.stock--;
	}

}
