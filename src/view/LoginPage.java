package view;

import java.awt.BorderLayout;
import java.awt.Canvas;
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

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

import model.User;
import program.Main;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

@SuppressWarnings("serial")
public class LoginPage extends JFrame implements ActionListener {

	final String VIDEO_PATH = "res";
	final String VIDEO_NAME = "video.mp4";

	MediaPlayerFactory mpf;

	EmbeddedMediaPlayer emp;

	JTextField tfEmail;
	JPasswordField pfPass;

	JButton loginBtn, resetBtn, regisBtn;

	public LoginPage() {
		setSize(450, 500);

		initializeAll();

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Login MotoDealer");

		setResizable(false);
		setLocationRelativeTo(null);
		emp.start();
	}

	private void initializeAll() {
		JPanel login = new JPanel(new BorderLayout());
		JPanel title = initializeTitle();
		JPanel video = setVideoPlayer();
		JPanel content = initializeContent();

		content.setBackground(Color.decode("#dcdcff"));

		login.add(title, BorderLayout.NORTH);
		login.add(video, BorderLayout.CENTER);
		login.add(content, BorderLayout.SOUTH);

		add(login);

	}

	private JPanel initializeTitle() {

		JPanel panel = new JPanel();

		panel.setBackground(Color.decode("#dcdcff"));

		JLabel title = new JLabel("MotoDealer Login");
		title.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_TITLE));

		panel.add(title);

		return panel;
	}

	public JPanel setVideoPlayer() {

		JPanel panel = new JPanel(new BorderLayout());
		Canvas canva = new Canvas();
		canva.setBackground(Color.BLACK);
		canva.setSize(350, 200);

		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);

		mpf = new MediaPlayerFactory();
		emp = mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(this));

		emp.setVideoSurface(mpf.newVideoSurface(canva));
		emp.prepareMedia(VIDEO_PATH + '\\' + VIDEO_NAME);
		emp.setRepeat(true);

		panel.add(canva, BorderLayout.CENTER);

		return panel;
	}

	private JPanel initializeContent() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(initializeComponents(), BorderLayout.CENTER);
		panel.add(initializeBtn(), BorderLayout.SOUTH);

		return panel;
	}

	private JPanel initializeComponents() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		panel.setBackground(Color.decode("#dcdcff"));

		JLabel email, pass;
		email = new JLabel("Email: ");
		pass = new JLabel("Password: ");

		email.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));
		pass.setFont(new Font(Main.FONT_NAME, Main.FONT_STYLE, Main.FONT_SIZE_CONTENT));

		tfEmail = new JTextField();
		pfPass = new JPasswordField();

		JPanel pnl = new JPanel(new GridLayout(2, 2, 5, 5));
		pnl.setBackground(Color.decode("#dcdcff"));

		pnl.add(email);
		pnl.add(tfEmail);
		pnl.add(pass);
		pnl.add(pfPass);

		panel.add(pnl, BorderLayout.CENTER);

		return panel;

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

	public void moveToPageBuy(User user) {
		new BuyPage(user);
		emp.stop();
		this.dispose();
	}

	public void moveToPageMaster() {
		new MasterPage();
		emp.stop();
		this.dispose();
	}

	public void moveToPageRegister() {
		new RegisPage();
		emp.stop();
		this.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == resetBtn) {
			tfEmail.setText("");
			pfPass.setText("");

		} else if (e.getSource() == regisBtn) {
			moveToPageRegister();
		} else if (e.getSource() == loginBtn) {

			String email = tfEmail.getText();
			@SuppressWarnings("deprecation")
			String password = pfPass.getText();

			// ADMIN - Master page
			if (email.equals("admin") && password.equals("admin")) {
				moveToPageMaster();
				return;
			}

			// BUYER - Buy page
			if (Main.getvUser().isEmpty()) {
				JOptionPane.showMessageDialog(null, "No user. Please register", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			} else {
				Vector<User> vUser = Main.getvUser();
				for (int i = 0; i < vUser.size(); i++) {
					User user = (User) vUser.get(i);

					if (email.equals(user.getEmail())) {
						if (password.equals(user.getPassword())) {
							moveToPageBuy(user);
							return;
						} else {
							JOptionPane.showMessageDialog(this, "Password is invalid", "Warning",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
				}

				JOptionPane.showMessageDialog(this, "User not found, please register", "Warning",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

		}
	}

}
