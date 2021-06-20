package br.com.locacar.view;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável pela apresentação do splash de aguardo!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class Splash extends JWindow {
	private int width = 250, height = 100;
	private String label;
	
	public Splash(String label) {
		this.label = label;
		initComponents();
	}
	
	private void initComponents() {
		JPanel content = (JPanel) getContentPane();
		content.setBackground(new Color(175, 238, 238));
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		setBounds(x, y, width, height);
		JLabel lblGifProgress = new JLabel(new ImageIcon("resource/images/imgProgressBar.gif"));
		JLabel lblLabel = new JLabel(label, JLabel.CENTER);
		lblLabel.setFont(new Font("Consolas", Font.BOLD, 16));
		content.add(lblGifProgress, BorderLayout.CENTER);
		content.add(lblLabel, BorderLayout.SOUTH);
		content.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	}
}