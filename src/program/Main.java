package program;

import java.awt.Font;
import java.util.Vector;

import model.*;
import view.*;

public class Main {

	private static Vector<User> vUser;
	private static Vector<Bike> vBike;
	private static Vector<Cart> vCart;
	public static final String FONT_NAME = "Futura Md BT";
	public static final int FONT_STYLE = Font.ROMAN_BASELINE;
	public static final int FONT_SIZE_CONTENT = 16;
	public static final int FONT_SIZE_TITLE = 20;

	private static void initializeCart() {
		vCart = new Vector<>();
	}

	public static Vector<Cart> getvCart() {
		return vCart;
	}

	public static void addCart(Cart cart) {
		vCart.add(cart);
	}
	
	public static void removeCart(int idx) {
		vCart.remove(idx);
	}

	private static void initializeUser() {
		vUser = new Vector<>();
	}

	public static Vector<User> getvUser() {
		return vUser;
	}

	
	public static void addUser(User user) {
		vUser.add(user);
	}

	private static void initializeBike() {
		vBike = new Vector<>();
		vBike.add(new Bike("BMW S1000rr", "bmw_s1000rr.png", 714000000,
				"BMW S1000RR is a sport bike initially made by BMW Motorrad to compete in the 2009 Superbike World Championship."));
		vBike.add(new Bike("Ducati Panigale", "ducati_panigale.png", 812000000,
				"Panigale V4 boosts performance even further and takes track riding to the next level for amateurs and pros alike."));
		vBike.add(new Bike("Kawasaki H2R", "kawasaki_h2r.png", 978000000,
				"Kawasaki Ninja H2R is a supercharged supersport class motorcycle in the Ninja sportbike series."));
		vBike.add(new Bike("Yamaha R1", "yamaha_r1.png", 689000000,
				"The R1 is underpinned by a diamond design aluminum frame and comes with an inline-four, 998cc petrol engine."));
	}
	
	public static Vector<Bike> getvBike() {
		return vBike;
	}

	public static void setBike(Bike bike, int index) {
		vBike.set(index, bike);
	}

	public static void main(String[] args) {
		initializeUser();
		initializeBike();
		initializeCart();
		
		new LoginPage();
		
	}

}
