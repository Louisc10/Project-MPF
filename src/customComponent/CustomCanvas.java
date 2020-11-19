package customComponent;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class CustomCanvas extends Canvas {
	String path;
	int width = 175;
	int height = 126;

	public CustomCanvas(String path) {
		this.path = path;
		repaint();
	}

	public CustomCanvas(String path, int width, int height) {
		super();
		this.path = path;
		this.width = width;
		this.height = height;
	}



	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		ImageIcon img = new ImageIcon("res/" + path);
		Image image = img.getImage();
		
		g2d.drawImage(image, 0, 0, width, height, null);
	}
}