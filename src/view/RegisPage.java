package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.User;
import program.Main;

@SuppressWarnings("serial")
public class RegisPage extends JFrame implements ActionListener {

	JTextField tfEmail;
	JPasswordField pfPass, pfCpass;

	Vector<JTextField> vEmail = new Vector<>();
	Vector<JPasswordField> vPass = new Vector<>();
	Vector<JPasswordField> vCpass = new Vector<>();

	JButton regisBtn, resetBtn, loginBtn;

	public RegisPage() {
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setTitle("Register MotoDealer");
		setSize(450, 350);

		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		initializeAll();
	}

	private void initializeAll() {
		JPanel regis = new JPanel(new BorderLayout());
		JPanel title = initializeTitle();
		JPanel content = initializeContent();

		content.setBackground(Color.decode("#dcdcff"));

		regis.add(title, BorderLayout.NORTH);
		regis.add(content, BorderLayout.CENTER);

		add(regis);

	}

	private JPanel initializeTitle() {
		JPanel panel = new JPanel();

		panel.setBackground(Color.decode("#dcdcff"));

		JLabel title = new JLabel("MotoDealer Register");
		title.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_TITLE));

		panel.add(title);
		return panel;
	}

	private JPanel initializeContent() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(initializeComponents(), BorderLayout.CENTER);
		panel.add(initializeBtn(), BorderLayout.SOUTH);

		return panel;
	}

	private JPanel initializeComponents() {

		JLabel email, pass, cPass;
		email = new JLabel("Email: ");
		pass = new JLabel("Password: ");
		cPass = new JLabel("Confirm Password");

		email.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
		pass.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
		cPass.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));

		tfEmail = new JTextField();
		vEmail.add(tfEmail);

		pfPass = new JPasswordField();
		vPass.add(pfPass);

		pfCpass = new JPasswordField();
		vCpass.add(pfCpass);

		JPanel pnl = new JPanel(new GridLayout(3, 2, 15, 25));
		pnl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pnl.setBackground(Color.decode("#dcdcff"));
		pnl.setBackground(Color.decode("#dcdcff"));

		pnl.add(email);
		pnl.add(tfEmail);
		pnl.add(pass);
		pnl.add(pfPass);
		pnl.add(cPass);
		pnl.add(pfCpass);

		return pnl;
	}

	private JPanel initializeBtn() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));

		regisBtn = new JButton("Register");
		resetBtn = new JButton("Reset");
		loginBtn = new JButton("Login");

		loginBtn.setBackground(Color.decode("#c7fbaa"));
		resetBtn.setBackground(Color.decode("#c7fbaa"));
		regisBtn.setBackground(Color.decode("#c7fbaa"));

		loginBtn.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
		resetBtn.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
		regisBtn.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));

		loginBtn.setPreferredSize(new Dimension(100, 30));

		loginBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		regisBtn.addActionListener(this);

		JPanel p1 = new JPanel(new GridLayout(1, 2, 5, 3));
		p1.setBorder(BorderFactory.createEmptyBorder(10, 120, 0, 120));
		p1.add(regisBtn);
		p1.add(resetBtn);

		JPanel p2 = new JPanel(new FlowLayout());

		p2.add(loginBtn);

		panel.setBackground(Color.decode("#dcdcff"));
		p1.setBackground(Color.decode("#dcdcff"));
		p2.setBackground(Color.decode("#dcdcff"));

		panel.add(p1);
		panel.add(p2);

		return panel;
	}

	public void moveToPageLogin() {
		new LoginPage();
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == resetBtn) {
			tfEmail.setText("");
			pfPass.setText("");
			pfCpass.setText("");
		} else if (e.getSource() == loginBtn) {
			moveToPageLogin();
			return;
		} else if (e.getSource() == regisBtn) {
			String fEmail = tfEmail.getText();
			@SuppressWarnings("deprecation")
			String fPass = pfPass.getText();
			@SuppressWarnings("deprecation")
			String fCpass = pfCpass.getText();

			if (fEmail.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Email must be filled", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (fPass.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Password must be filled");
				return;
			} else if (fEmail.contains(" ")) {
				JOptionPane.showMessageDialog(null, "Email can't contain space", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else if (!fEmail.contains("@") || fEmail.indexOf("@") != fEmail.lastIndexOf("@")) {
				JOptionPane.showMessageDialog(null, "Email must have one '@'", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			} else if (fEmail.startsWith("@")) {
				JOptionPane.showMessageDialog(null, "Email can't start with '@'", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else if (!fEmail.endsWith(".com")) {
				JOptionPane.showMessageDialog(null, "Email must be ended with '.com'", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			} else if (fPass.contains(" ")) {
				JOptionPane.showMessageDialog(null, "Password can't contain space");
				return;
			} else if (!fCpass.contentEquals(fPass)) {
				JOptionPane.showMessageDialog(null, "Confirm Password must be matched with password");
				return;
			} else {

				Vector<User> vUser = Main.getvUser();
				for (int i = 0; i < vUser.size(); i++) {
					User user = (User) vUser.get(i);

					if (fEmail.equals(user.getEmail())) {
						JOptionPane.showMessageDialog(this, "Email has been registered", "Warning",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				Main.addUser(new User(fEmail, fPass));
				
				moveToPageLogin();
			}

		}

	}

}
