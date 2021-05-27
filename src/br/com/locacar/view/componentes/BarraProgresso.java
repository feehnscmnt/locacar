package br.com.locacar.view.componentes;

import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável pela personalização do JProgressBar!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class BarraProgresso extends JProgressBar {
	
	public BarraProgresso() {
		initComponents();
	}
	
	private void initComponents() {
		setFont(new Font("Segoe UI", Font.BOLD, 12));
		setBackground(new Color(255, 255, 255));
		setForeground(Color.BLACK);
		setStringPainted(true);
		setValue(100);
	}
}