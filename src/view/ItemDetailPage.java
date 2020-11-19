package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import customComponent.CustomButton;
import customComponent.CustomTextArea;
import program.Main;

@SuppressWarnings("serial")
public class ItemDetailPage extends JFrame implements ActionListener, MouseListener {
	BufferedImage img = null;
	JPanel panels;
	AffineTransform at = new AffineTransform();
	JButton RotateLeft, RotateRight, ZoomIn, ZoomOut, Close;
	JTextArea desc;
	final String IMG_PATH = "res/";

	double xScale = 0.2, yScale = 0.2;
	double xPos = 175, yPos = 100;
	double degree = 0;

	public ItemDetailPage(int index) {
		initializePanel();

		getImage(index);

		JPanel panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(275, 275));
		panel2.setBackground(Color.decode("#dcdcff"));
		panels.add(panel2, BorderLayout.NORTH);

		desc = new CustomTextArea(Main.getvBike().get(index).getDescription());
		desc.setEditable(false);
		desc.setBackground(Color.decode("#dcdcff"));
		panels.add(desc, BorderLayout.CENTER);

		JPanel bot = initializeButton();
		panels.add(bot, BorderLayout.SOUTH);

		addlistener();
		initializeFrame();
	}

	private void initializePanel() {
		panels = new JPanel(new BorderLayout());
		panels.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
		panels.setBackground(Color.decode("#dcdcff"));
	}

	private void getImage(int index) {
		String imageName = Main.getvBike().get(index).getImage();
		String path = IMG_PATH + imageName;
		try {
			Image image = ImageIO.read(new File(path));
			img = (BufferedImage) image;
		} catch (Exception e) {
			e.printStackTrace();
		}
		at.translate(xPos, yPos);
		at.scale(xScale, yScale);
		repaint();
	}

	private JPanel initializeButton() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
		panel.setBackground(Color.decode("#dcdcff"));

		RotateLeft = new CustomButton("Rotate Left");
		RotateRight = new CustomButton("Rotate Right");
		ZoomIn = new CustomButton("Zoom In");
		ZoomOut = new CustomButton("Zoom Out");
		Close = new CustomButton("Close");

		JPanel top = new JPanel(new GridLayout(1, 3, 5, 5));
		top.setBackground(Color.decode("#dcdcff"));
		top.add(RotateLeft);
		top.add(new JLabel());
		top.add(RotateRight);
		panel.add(top);

		JPanel mid = new JPanel(new GridLayout(1, 3, 5, 5));
		mid.setBackground(Color.decode("#dcdcff"));
		mid.add(ZoomIn);
		mid.add(new JLabel());
		mid.add(ZoomOut);
		panel.add(mid);

		JPanel bot = new JPanel(new GridLayout(1, 3, 5, 5));
		bot.setBackground(Color.decode("#dcdcff"));
		bot.add(new JLabel());
		bot.add(Close);
		bot.add(new JLabel());
		panel.add(bot);

		return panel;
	}

	private void addlistener() {
		ZoomIn.addActionListener(this);
		ZoomOut.addActionListener(this);

		RotateLeft.addMouseListener(this);
		RotateRight.addMouseListener(this);

		Close.addActionListener(this);
	}

	private void initializeFrame() {
		add(panels);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(600, 550);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		at.rotate(Math.toRadians(degree), img.getWidth() / 2, img.getHeight() / 2);
		g2d.drawImage(img, at, null);

		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ZoomIn) {
			at.scale(2.5, 2.5);
		} else if (e.getSource() == ZoomOut) {
			at.scale(1 - 0.2, 1 - 0.2);
		} else if (e.getSource() == Close) {
			this.dispose();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == RotateLeft) {
			degree = -10;
		} else if (e.getSource() == RotateRight) {
			degree = 10;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		degree = 0;
	}

}
