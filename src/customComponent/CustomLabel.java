package customComponent;

import java.awt.Font;

import javax.swing.JLabel;

import program.Main;

@SuppressWarnings("serial")
public class CustomLabel extends JLabel{

	public CustomLabel(String s) {
		super(s);
		setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
	}

}
