package br.com.locacar.view.componentes;

import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * Classe de personalização dos campos de texto dos view's!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class JTextFieldHint extends JTextField implements FocusListener {
	private JTextField txt;
    private Icon icone;
    private String dica;
    private Insets insercFict;
    
    public JTextFieldHint(JTextField txt, String icone, String dica) {
    	this.txt = txt;
        ImageIcon img = new ImageIcon("resource/images/".concat(icone));
        setIcon(img);
        this.dica = dica;
        Border border = UIManager.getBorder("TextField.border");
        JTextField dummy = new JTextField();
        this.insercFict = border.getBorderInsets(dummy);
        this.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
        addFocusListener(this);
    }
    
    public void setIcon(ImageIcon imageIcon) {
        this.icone = imageIcon;
    }
    
    public void setIcon(Icon newIcon) {
        this.icone = newIcon;
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int textX = 2;
        if (this.icone != null) {
            int iconWidth = icone.getIconWidth();
            int iconHeight = icone.getIconHeight();
            int x = insercFict.left + 5;
            textX = x + iconWidth + 2;
            int y = (this.getHeight() - iconHeight) / 2;
            icone.paintIcon(this, g, x, y);
        }
        setMargin(new Insets(2, textX, 2, 2));
        if (this.getText().equals("")) {
            int height = this.getHeight();
            Font prev = g.getFont();
            Font bold = prev.deriveFont(Font.BOLD);
            Color prevColor = g.getColor();
            g.setFont(bold);
            g.setColor(UIManager.getColor("textInactiveText"));
            int h = g.getFontMetrics().getHeight();
            int textBottom = (height - h) / 2 + h - 4;
            int x = this.getInsets().left;
            Graphics2D g2d = (Graphics2D) g;
            RenderingHints hints = g2d.getRenderingHints();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.drawString(dica, x, textBottom);
            g2d.setRenderingHints(hints);
            g.setFont(prev);
            g.setColor(prevColor);
        }
    }
    
    public JTextField getTxt() {
    	return txt;
    }
    
    public void setJTextField(JTextField txt) {
    	this.txt = txt;
    }
	
	public void focusGained(FocusEvent e) {
		this.repaint();
	}
	
	public void focusLost(FocusEvent e) {
		this.repaint();
	}
}