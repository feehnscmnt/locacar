package br.com.locacar.view.componentes;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável pelo bloqueio dos frames!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class BlockViews extends JPanel {
	
	public BlockViews() {
		initComponents();
	}
	
	private void initComponents() {
		setOpaque(false);
		setLayout(new GridBagLayout());
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				e.consume();
				Toolkit.getDefaultToolkit().beep();
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(0, 0, 0, 140));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}