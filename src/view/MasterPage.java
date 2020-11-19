package view;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import customComponent.CustomButton;
import customComponent.CustomCanvas;
import customComponent.CustomLabel;
import customComponent.CustomSpinner;
import customComponent.CustomTextArea;
import customComponent.CustomTextField;
import model.Bike;
import program.Main;

@SuppressWarnings("serial")
public class MasterPage extends JFrame implements ActionListener {
	
	//Graphic 2D Dengan Canvas

	Vector<JTextField> vItemName = new Vector<>();
	Vector<JSpinner> vPrice = new Vector<>();
	Vector<JSpinner> vStock = new Vector<>();
	Vector<JTextArea> vDescription = new Vector<>();
	Vector<JButton> vViewImageButton = new Vector<>();
	Vector<JButton> vUpdateItemButton = new Vector<>();

	JButton logoutBtn, exitBtn;

	public MasterPage() {
		initializeAllPanel();
		initializeFrame();
	}

	private void initializeAllPanel() {
		JPanel master = new JPanel(new BorderLayout());
		JPanel title = initializeTitle();
		JPanel content = initializeContent();
		JPanel bottom = initializeBottom();

		master.add(title, BorderLayout.NORTH);
		master.add(content, BorderLayout.CENTER);
		master.add(bottom, BorderLayout.SOUTH);

		add(master);
	}

	private JPanel initializeTitle() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#EBF5FB"));
		JLabel title = new CustomLabel("Welcome, Admin");
		title.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_TITLE));
		panel.add(title);
		return panel;
	}

	private JPanel initializeContent() {
		JPanel panel = new JPanel(new GridLayout(Main.getvBike().size(), 1));
		for (int i = 0; i < Main.getvBike().size(); i++) {
			Bike bike = Main.getvBike().get(i);

			JPanel all = new JPanel(new GridBagLayout());
			all.setBorder(new LineBorder(Color.BLACK));
			all.setBackground(Color.decode("#A9CCE3"));
			all.add(initializeContentLeft(bike));

			JPanel detail = new JPanel(new GridLayout(3, 1, 10, 5));
			detail.setPreferredSize(new Dimension(175 * 6, 180));
			detail.setBorder(new EmptyBorder(20, 0, 20, 0));
			detail.setBackground(Color.decode("#A9CCE3"));
			JPanel p1 = initializeContentTop(bike);
			detail.add(p1);
			JPanel p2 = initializeContentCenter(bike);
			detail.add(p2);
			JPanel p3 = initializeContentBottom();
			detail.add(p3);
			all.add(detail);

			panel.add(all);
		}
		return panel;
	}

	private JPanel initializeContentLeft(Bike bike) {
		JPanel left = new JPanel(new BorderLayout());
		left.setSize(175, 150);
		left.setPreferredSize(new Dimension(175, 150));
		left.setBackground(Color.decode("#A9CCE3"));
		
		Canvas canva = new CustomCanvas(bike.getImage());
		left.add(canva, BorderLayout.CENTER);
		
		return left;
	}

	private JPanel initializeContentTop(Bike bike) {
		JPanel pnl = new JPanel(new GridLayout(1, 6, 10, 0));
		pnl.setSize(175 * 6, 126 / 3);
		pnl.setBackground(Color.decode("#A9CCE3"));

		JLabel itemName, itemPrice, itemStock;
		itemName = new CustomLabel("Item Name:");
		itemPrice = new CustomLabel("Price:");
		itemStock = new CustomLabel("Stock:");

		JTextField tfItemName = new CustomTextField(bike.getName());
		tfItemName.setSize(175, 126 / 3);
		vItemName.add(tfItemName);

		JSpinner spPrice, spStock;
		spPrice = new CustomSpinner(bike.getPrice(), 100000000, 1000000000, 1000000);
		spPrice.setSize(175, 126 / 3);
		vPrice.add(spPrice);

		spStock = new CustomSpinner(bike.getStock(), 0, 20, 1);
		spStock.setSize(175, 126 / 3);
		vStock.add(spStock);

		JPanel top0 = new JPanel(new BorderLayout());
		top0.setSize(175, 126 / 3);
		top0.setBackground(Color.decode("#A9CCE3"));
		top0.add(itemName, BorderLayout.WEST);

		JPanel top1 = new JPanel(new BorderLayout());
		top1.setSize(175, 126 / 3);
		top1.setBackground(Color.decode("#A9CCE3"));
		top1.add(tfItemName, BorderLayout.CENTER);

		JPanel top2 = new JPanel(new BorderLayout());
		top2.setSize(175, 126 / 3);
		top2.setBackground(Color.decode("#A9CCE3"));
		top2.add(itemPrice, BorderLayout.WEST);

		JPanel top3 = new JPanel(new BorderLayout());
		top3.setSize(175, 126 / 3);
		top3.setBackground(Color.decode("#A9CCE3"));
		top3.add(spPrice, BorderLayout.CENTER);

		JPanel top4 = new JPanel(new BorderLayout());
		top4.setSize(175, 126 / 3);
		top4.setBackground(Color.decode("#A9CCE3"));
		top4.add(itemStock, BorderLayout.WEST);

		JPanel top5 = new JPanel(new BorderLayout());
		top5.setSize(175, 126 / 3);
		top5.setBackground(Color.decode("#A9CCE3"));
		top5.add(spStock, BorderLayout.CENTER);

		pnl.add(top0);
		pnl.add(top1);
		pnl.add(top2);
		pnl.add(top3);
		pnl.add(top4);
		pnl.add(top5);

		return pnl;
	}

	private JPanel initializeContentCenter(Bike bike) {
		JPanel center = new JPanel(new BorderLayout());
		center.setBackground(Color.decode("#A9CCE3"));
		center.setSize((175 * 6), 126 / 3);

		JLabel itemDescription;
		itemDescription = new CustomLabel("Desciption:");
		itemDescription.setSize(new Dimension(175, 126 / 3));

		JTextArea desc = new CustomTextArea(bike.getDescription());
		desc.setSize(new Dimension((175 * 5), 126 / 3));
		vDescription.add(desc);

		center.add(itemDescription, BorderLayout.WEST);
		center.add(desc, BorderLayout.EAST);
		return center;
	}

	private JPanel initializeContentBottom() {
		JPanel pnl = new JPanel(new BorderLayout());
		pnl.setSize(175 * 6, 126 / 3);
		pnl.setBackground(Color.decode("#A9CCE3"));

		JButton viewImageButton = new CustomButton("View Image");
		viewImageButton.addActionListener(this);
		vViewImageButton.add(viewImageButton);
		pnl.add(viewImageButton, BorderLayout.WEST);

		JButton updateItemButton = new CustomButton("Update Item");
		updateItemButton.addActionListener(this);
		vUpdateItemButton.add(updateItemButton);
		pnl.add(updateItemButton, BorderLayout.EAST);
		return pnl;
	}

	private JPanel initializeBottom() {
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

	private void initializeFrame() {
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Master Window");
		setExtendedState(MAXIMIZED_BOTH);
		
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	public void movePageLogin(){
		new LoginPage();
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logoutBtn) {
			movePageLogin();
		} else if (e.getSource() == exitBtn) {
			this.dispose();
		} else {
			for (int i = 0; i < vViewImageButton.size(); i++) {
				if (e.getSource() == vViewImageButton.get(i)) {
					new ViewImagePage(i);
					return;
				}
			}
			for (int i = 0; i < vUpdateItemButton.size(); i++) {
				if (e.getSource() == vUpdateItemButton.get(i)) {
					String name = vItemName.get(i).getText();

					NumberFormat format = new DecimalFormat("####");
					long price = -1;
					try {
						price = (long) format.parse(vPrice.get(i).getValue().toString());
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					int stock = 0;
					try {
						stock = (int) Double.parseDouble(vStock.get(i).getValue().toString());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					String desc = vDescription.get(i).getText();
					Bike bike = Main.getvBike().get(i);
					if (name.equals("")) {
						JOptionPane.showMessageDialog(this, "Item’s name must be filled", "Warning",
								JOptionPane.WARNING_MESSAGE);
					} else if (desc.equals("")) {
						JOptionPane.showMessageDialog(this, "Item’s description must be filled", "Warning",
								JOptionPane.WARNING_MESSAGE);
					} else if (bike.getName().equals(name) && bike.getPrice() == price && bike.getStock() == stock
							&& bike.getDescription().equals(desc)) {
						JOptionPane.showMessageDialog(this, "Nothing to change.", "Warning",
								JOptionPane.WARNING_MESSAGE);
					} else {

						bike.setName(name);
						bike.setPrice(price);
						bike.setStock(stock);
						bike.setDescription(desc);

						Main.setBike(bike, i);

						JOptionPane.showMessageDialog(this, "Success.", "Success", JOptionPane.INFORMATION_MESSAGE);
					}

					return;
				}
			}
		}

	}

}
