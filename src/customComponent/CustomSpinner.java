package customComponent;

import java.awt.Font;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import program.Main;

@SuppressWarnings("serial")
public class CustomSpinner extends JSpinner{

	public CustomSpinner(long now, long min, long max, long step) {
		super(new SpinnerNumberModel(now, min, max, step));
		setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
	}

}
