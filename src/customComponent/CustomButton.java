package customComponent;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

import program.Main;

@SuppressWarnings("serial")
public class CustomButton extends JButton{

	public CustomButton(String s) {
		super(s);
		setBackground(Color.GREEN);
		setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
}

}
