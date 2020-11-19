package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import customComponent.CustomButton;
import customComponent.CustomLabel;
import model.Bike;
import model.Cart;
import model.User;
import program.Main;

@SuppressWarnings("serial")
public class BuyPage extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

	Vector<JTextField> vUserCart = new Vector<>();
	Vector<Integer> vTotalPrice = new Vector<>();
	Vector<BufferedImage> vImage = new Vector<>();

	private User user;

	JButton logoutBtn, exitBtn, deleteBtn, buyBtn;
	JTextArea field;
	JLabel totalPrice;
	JPanel master;

	BufferedImage imgCart = null;

	int selectedItem = -1;
	int pressed = -1;
	int xCursor, yCursor;

	NumberFormat cf1 = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

	AudioInputStream song;
	Vector<Clip> clips = new Vector<Clip>();
	Clip currentClip;

	public BuyPage(User user) {
		this.user = user;

		getImage();

		createPanels();
		createFrame();

		getAudio();
	}

	private void getImage() {
		try {
			Image image = ImageIO.read(new File("res/shopping-cart.png"));
			imgCart = (BufferedImage) image;
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Bike bike : Main.getvBike()) {
			String path = "res/" + bike.getImage();
			Image image;
			try {
				image = ImageIO.read(new File(path));
				BufferedImage x = (BufferedImage) image;
				vImage.add(x);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void getAudio() {
		String soundName = "res/bike_revv.wav";
		try {
			song = AudioSystem.getAudioInputStream(new File(soundName));
			Clip clip = AudioSystem.getClip();
			clip.open(song);
			clips.add(clip);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		currentClip = clips.get(0);
	}

	public void playClip() {
		currentClip.setMicrosecondPosition(0);
		currentClip.start();
	}

	private void createFrame() {
		setTitle("Buy Window");
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);

		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	private void createPanels() {
		master = new JPanel(new BorderLayout());
		JPanel title = createTitle();
		JPanel content = createContent();
		JPanel rightside = createRight();
		JPanel bottom = createBottom();

		master.add(title, BorderLayout.NORTH);
		master.add(content, BorderLayout.CENTER);
		master.add(rightside, BorderLayout.EAST);
		master.add(bottom, BorderLayout.SOUTH);

		master.addMouseListener(this);
		master.addMouseMotionListener(this);

		add(master);
	}

	private JPanel createTitle() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#EBF5FB"));
		JLabel title = new CustomLabel("Welcome to MotoDealer");
		title.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_TITLE));
		panel.add(title);
		return panel;
	}

	private JPanel createContent() {
		JPanel panel = new JPanel(new GridLayout(2, 2));
		panel.setBackground(Color.decode("#EBF5FB"));
		panel.setPreferredSize(new Dimension(400, 400));

		for (int i = 0; i < Main.getvBike().size(); i++) {
			Bike bike = Main.getvBike().get(i);

			JPanel panel1 = new JPanel();
			panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
			panel1.setBackground(Color.decode("#EBF5FB"));
			panel1.setBorder(new EmptyBorder(0, 50, 50, 20));
			panel1.setPreferredSize(new Dimension(200, 200));

			JPanel x = new JPanel();
			x.setBackground(Color.decode("#EBF5FB"));
			x.setPreferredSize(new Dimension(200, 170));
			x.setSize(new Dimension(200, 170));

			JLabel name = new CustomLabel(bike.getName());
			name.setAlignmentX(CENTER_ALIGNMENT);

			JLabel price = new JLabel(cf1.format(bike.getPrice()));
			price.setAlignmentX(CENTER_ALIGNMENT);

			panel1.add(x);
			panel1.add(name);
			panel1.add(price);

			panel.add(panel1);
		}

		return panel;
	}

	private JPanel createRight() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.decode("#EBF5FB"));
		panel.setBorder(new EmptyBorder(0, 50, 50, 20));
		panel.setPreferredSize(new Dimension(400, 800));

		JPanel all = new JPanel();
		all.setBackground(Color.decode("#EBF5FB"));

		field = new JTextArea();
		totalPrice = new JLabel();

		JPanel p1 = createCartLabel();
		panel.add(p1);
		JPanel p2 = createCart();
		panel.add(p2);
		JPanel p3 = createTotalPrice();
		panel.add(p3);

		deleteBtn = new CustomButton("Delete");
		deleteBtn.addActionListener(this);
		buyBtn = new CustomButton("Buy");
		buyBtn.addActionListener(this);

		JPanel button = new JPanel();
		button.setLayout(new GridLayout(2, 1, 10, 10));
		button.setBackground(Color.decode("#EBF5FB"));
		button.add(deleteBtn);
		button.add(buyBtn);

		panel.add(all);
		panel.add(button);
		return panel;
	}

	private JPanel createCartLabel() {
		JPanel title = new JPanel();
		title.setBackground(Color.decode("#EBF5FB"));
		JLabel cart = new CustomLabel("My Cart");
		cart.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
		title.add(cart);

		return title;
	}

	private JPanel createCart() {
		JPanel cart = new JPanel(new BorderLayout());

		recheckTheCart();

		cart.setPreferredSize(new Dimension(100, 300));
		cart.add(field);

		return cart;
	}

	private JPanel createTotalPrice() {
		JPanel price = new JPanel();

		price.setBackground(Color.decode("#EBF5FB"));
		totalPrice.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
		price.add(totalPrice);

		return price;
	}

	private JPanel createBottom() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.decode("#EBF5FB"));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		logoutBtn = new CustomButton("Logout");
		logoutBtn.addActionListener(this);
		exitBtn = new CustomButton("Exit");
		exitBtn.addActionListener(this);

		JPanel button = new JPanel();
		button.setLayout(new GridLayout(2, 1, 10, 10));
		button.add(logoutBtn);
		button.add(exitBtn);

		panel.add(button, BorderLayout.EAST);
		return panel;
	}

	public void moveLoginPage() {
		LoginPage loginPage = new LoginPage();
		loginPage.setVisible(true);

		this.dispose();
	}

	private void recheckTheCart() {
		String text = "";
		long totalPrices = 0;

		for (Cart c : Main.getvCart()) {
			if (c.getUser().equals(user)) {
				text += c.getBike().getName() + "\n";
				totalPrices += c.getBike().getPrice();
			}
		}

		field.setText(text);
		totalPrice.setText("Total Price : " + cf1.format(totalPrices));
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(imgCart, ((getWidth() / 2) - (50 / 2)), (getHeight() - 50 - 30), 50, 50, null);

		for (int i = 0; i < vImage.size(); i++) {
			BufferedImage x = vImage.get(i);
			g2d.drawImage(x, (750 * (i % 2)) + 200, (450 * (i / 2)) + 100, 400, 300, null);
		}

		if (pressed != -1) {
			g2d.drawImage(vImage.get(pressed), xCursor - 200, yCursor - 150, 400, 300, null);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) {
			moveLoginPage();
		} else if (e.getSource() == exitBtn) {
			this.dispose();
		} else if (e.getSource() == deleteBtn) {
			System.out.println(selectedItem);
			if (selectedItem == -1) {
				JOptionPane.showMessageDialog(null, "No item selected", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				boolean flag = false;
				Bike bike = Main.getvBike().get(selectedItem);
				for (int i = 0; i < Main.getvCart().size(); i++) {
					Cart c = Main.getvCart().get(i);
					System.out.println(i + " >> " + c.getBike().getName() + " <> " + c.getUser().getEmail());
					if (c.getUser().equals(user)) {
						if (c.getBike().getImage().equals(bike.getImage())) {
							Main.removeCart(i);
							flag = true;
							break;
						}
					}
				}
				if (!flag) {
					JOptionPane.showMessageDialog(null, "No such item in cart", "Warning", JOptionPane.WARNING_MESSAGE);
				}

				recheckTheCart();
			}
			selectedItem = -1;
		} else if (e.getSource() == buyBtn) {
			boolean flag = false;

			for (Cart c : Main.getvCart()) {
				if (c.getUser().equals(user)) {
					flag = true;
					break;
				}
			}

			if (flag == false) {
				JOptionPane.showMessageDialog(null, "No Bike to Buy!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				System.out.println("IS " + Main.getvCart().size());
				for (int i = 0; i < Main.getvCart().size();) {
					Cart c = Main.getvCart().get(i);
					if (c.getUser().equals(user)) {
						for (int k = 0; k < Main.getvBike().size(); k++) {
							Bike bike = Main.getvBike().get(k);
							if (c.getBike().getImage().equals(bike.getImage())) {
								Main.getvBike().get(k).decreaseStock();
								break;
							}
						}
						System.out.println("Delete");
						Main.removeCart(i);
						;
						continue;
					}
					i++;
				}
				recheckTheCart();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		for (int i = 0; i < vImage.size(); i++) {
			int x = e.getX();
			int y = e.getY();
			int x1 = 200 + (750 * (i % 2));
			int x2 = 200 + 400 + (750 * (i % 2));
			int y1 = 100 + (450 * (i / 2));
			int y2 = 100 + 300 + (450 * (i / 2));

			if (x1 <= x && x <= x2 && y1 <= y && y <= y2) {
				new ItemDetailPage(i);
				selectedItem = i;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		if (pressed != -1) {
			if (((getWidth() / 2) - (50 / 2)) <= xCursor && xCursor <= ((getWidth() / 2) + (50 / 2))
					&& (getHeight() - 100 - 30) <= yCursor && yCursor <= (getHeight() - 50 - 30)) {
				Bike bike= Main.getvBike().get(pressed);
				long stock = bike.getStock();
				for(int i = 0; i<Main.getvCart().size();i++){
					if(Main.getvCart().get(i).getBike().getImage().equals(bike.getImage())){
						stock--;
					}
				}
				
				if(stock > 0){					
					Main.getvCart().add(new Cart(user, Main.getvBike().get(pressed)));
					recheckTheCart();
					playClip();
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Not Enough Stock!", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		pressed = -1;
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		xCursor = e.getX();
		yCursor = e.getY();

		if (pressed == -1) {
			for (int i = 0; i < vImage.size(); i++) {
				int x1 = 200 + (750 * (i % 2));
				int x2 = 200 + 400 + (750 * (i % 2));
				int y1 = 100 + (450 * (i / 2));
				int y2 = 100 + 300 + (450 * (i / 2));

				if (x1 <= xCursor && xCursor <= x2 && y1 <= yCursor && yCursor <= y2) {
					pressed = i;
				}
			}
		}
		if (pressed != -1)
			repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}