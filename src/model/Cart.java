package model;

public class Cart {

	private User user;
	private Bike bike;

	public Cart(User user, Bike bike) {
		super();
		this.user = user;
		this.bike = bike;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

}
