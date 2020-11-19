package customComponent;

import java.awt.Font;

import javax.swing.JTextArea;

import program.Main;

@SuppressWarnings("serial")
public class CustomTextArea extends JTextArea{

	public CustomTextArea(String s) {
		super(s);
		setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
		setLineWrap(true);
	}

}
