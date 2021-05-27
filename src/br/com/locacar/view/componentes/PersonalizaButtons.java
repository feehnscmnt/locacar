package br.com.locacar.view.componentes;

import br.com.locacar.util.*;
import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável pela personalização dos botões!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class PersonalizaButtons extends JButton {
	private String img, imgOver, tooltipText, label;
	private static final int TOOLTIP_WIDTH = 500;
	
	public PersonalizaButtons(String img, String imgOver, String tooltipText, String label) {
		this.img = img;
		this.imgOver = imgOver;
		this.tooltipText = tooltipText;
		this.label = label;
		initComponents();
	}
	
	private void initComponents() {
		if (tooltipText != null) {
			int width = SwingUtilities.computeStringWidth(getFontMetrics(getFont()), tooltipText);
			if (width > TOOLTIP_WIDTH) {
				width = TOOLTIP_WIDTH;
			}
			setToolTipText(String.format("<html><p width='%d'>%s</p></html>", width, tooltipText));
		}
		
		if (label != null) {
			setText(label);
			setHorizontalTextPosition(SwingConstants.CENTER);
			setVerticalTextPosition(SwingConstants.BOTTOM);
		}
		
		setIcon(PathFilesUtil.getImg(img));
		setMargin(new Insets(0, 0, 0, 0));
		setBorder(null);
		setBackground(new Color(255, 255, 255, 0));
		setOpaque(false);
		setFont(new Font("Lucida Console", Font.BOLD, 9));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setRolloverIcon(PathFilesUtil.getImg(imgOver));
		setContentAreaFilled(false);
		setBorderPainted(false);
		setDoubleBuffered(true);
		setFocusPainted(false);
	}
}