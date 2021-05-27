package br.com.locacar.view.componentes;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class BlockViews extends JPanel {
	
	public BlockViews() {
		initComponents();
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(0, 0, 0, 90));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	private void initComponents() {
		setLayout(new GridBagLayout());
		setOpaque(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				e.consume();
				Toolkit.getDefaultToolkit().beep();
			}
		});
	}
}