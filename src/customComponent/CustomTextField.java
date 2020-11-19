package customComponent;

import java.awt.Font;

import javax.swing.JTextField;

import program.Main;

@SuppressWarnings("serial")
public class CustomTextField extends JTextField{

	public CustomTextField(String s) {
		super(s);
		setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
	}

}
