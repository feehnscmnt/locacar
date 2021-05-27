package br.com.locacar.view.modal;

import com.jgoodies.forms.factories.*;
import br.com.locacar.model.modal.*;
import com.jgoodies.forms.layout.*;
import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;

/**
 * Modal de apresentação das mensagens aos usuários! 
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class BoxModalMensagens extends JPanel {
	private MensagensModel msgns;
	private JTextPane txtPane;
	
	public enum nivelMensagens {
		INFO, AVISO, ERRO
	}
	
	public BoxModalMensagens(MensagensModel msgns) {
		this.msgns = msgns;
		initComponents();
	}
	
	private void initComponents() {
		setOpaque(false);
		setLayout(new FormLayout("default:grow", "top:default, center:default:grow"));
		setPreferredSize(new Dimension(300, 130));
		txtPane = new JTextPane();
		txtPane.setEditable(false);
		txtPane.setBorder(null);
		txtPane.setBackground(null);
		txtPane.setOpaque(false);
		txtPane.setFont(new Font("Lucida Console", Font.BOLD, 14));
		txtPane.setText(msgns.getText().trim());
		SimpleAttributeSet sas = new SimpleAttributeSet();
		StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
		txtPane.setParagraphAttributes(sas, false);
		
		JPanel painelMsgns = new JPanel();
		painelMsgns.setLayout(new BoxLayout(painelMsgns, BoxLayout.LINE_AXIS));
		painelMsgns.setOpaque(false);
		painelMsgns.add(txtPane);
		
		add(painelMsgns, CC.xywh(1, 1, 1, 2, CC.CENTER, CC.CENTER));
	}
	
	protected void paintComponent(Graphics g) {
		int x = 1, y = 1, w = getWidth() - 4, h = getHeight() - 4, arc = 40;
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (msgns.getNivelMsgns().equals(nivelMensagens.ERRO)) {
			g2.setColor(new Color(255, 0, 0, 190));
			txtPane.setForeground(Color.WHITE);
		} else if(msgns.getNivelMsgns().equals(nivelMensagens.INFO)) {
			g2.setColor(new Color(0, 105, 190, 190));
			txtPane.setForeground(Color.WHITE);
		} else {
			g2.setColor(new Color(255, 111, 15, 190));
			txtPane.setForeground(Color.BLACK);
		}
		g2.fillRoundRect(x, y, w, h, arc, arc);
		g2.setStroke(new BasicStroke(3f));
		g2.setColor(Color.LIGHT_GRAY);
		g2.drawRoundRect(x, y, w, h, arc, arc);
		g2.dispose();
	}
}