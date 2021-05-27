package br.com.locacar.view.modal;

import br.com.locacar.view.componentes.*;
import br.com.locacar.view.clientepf.*;
import br.com.locacar.view.clientepj.*;
import com.jgoodies.forms.factories.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.opcao.*;
import com.jgoodies.forms.layout.*;
import javax.swing.text.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * Modal de para selecionar o view de clientes PF ou de clientes PJ!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class BoxModalSelecionaView extends JPanel {
	private JButton btnViewClientePF, btnViewClientePJ;
	private JPanel buttonsContent;
	private MensagensModel text;
	private JTextPane txtPane;
	private Opcoes opcao;
	public JFrame frm;
	
	public BoxModalSelecionaView(JFrame frm, Opcoes opcao, MensagensModel text) {
		this.frm = frm;
		this.opcao = opcao;
		this.text = text;
		initComponents();
	}
	
	private void initComponents() {
		setOpaque(false);
		setLayout(new FormLayout("default:grow", "top:default, center:default:grow"));
		setPreferredSize(new Dimension(300, 120));
		txtPane = new JTextPane();
		txtPane.setBorder(null);
		txtPane.setBackground(null);
		txtPane.setOpaque(false);
		txtPane.setFont(new Font("Lucida Console", Font.BOLD, 14));
		txtPane.setText(text.getText().trim());
		SimpleAttributeSet sas = new SimpleAttributeSet();
		StyleConstants.setAlignment(sas, StyleConstants.ALIGN_CENTER);
		txtPane.setParagraphAttributes(sas, false);
		
		JPanel painelText = new JPanel();
		painelText.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 11));
		painelText.setOpaque(false);
		painelText.add(txtPane);
		
		buttonsContent = new JPanel();
		buttonsContent.setOpaque(false);
		buttonsContent.setBackground(new Color(255, 255, 255, 0));
		buttonsContent.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 50));
		
		btnViewClientePF = new PersonalizaButtons("imgClientePF.png", "imgClientePFOver.png", "", "Pessoa Física");
		btnViewClientePF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
				if (opcao.equals(Opcoes.VIEW_CADASTRO))
					new FrmClientePF().setVisible(true);
				else if (opcao.equals(Opcoes.VIEW_CONSULTA))
					new FrmConsultarClientesPF().setVisible(true);
			}
		});
		
		btnViewClientePJ = new PersonalizaButtons("imgClientePJ.png", "imgClientePJOver.png", "", "Pessoa Jurídica");
		btnViewClientePJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fechar();
				if (opcao.equals(Opcoes.VIEW_CADASTRO))
					new FrmClientePJ().setVisible(true);
				else if (opcao.equals(Opcoes.VIEW_CONSULTA))
					new FrmConsultarClientesPJ().setVisible(true);
			}
		});
		
		buttonsContent.add(btnViewClientePF);
		buttonsContent.add(btnViewClientePJ);
		add(buttonsContent, CC.xy(1, 1));
		add(painelText, CC.xywh(1, 1, 1, 2, CC.CENTER, CC.TOP));
	}
	
	private void fechar() {
		Object obj = BoxModalSelecionaView.this.getParent().getParent();
		if (obj instanceof JRootPane) {
			JRootPane rootPane = (JRootPane) obj;
			JPanel glassPane = (JPanel) rootPane.getGlassPane();
			glassPane.removeAll();
			glassPane.repaint();
			glassPane.revalidate();
			if (glassPane.getComponentCount() == 0)
				glassPane.setVisible(false);
		}
	}
	
	protected void paintComponent(Graphics g) {
		int x = 1, y = 1, w = getWidth() - 4, h = getHeight() - 4, arc = 10;
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		txtPane.setForeground(Color.BLACK);
		g2.setColor(new Color(255, 0, 0, 200));
		g2.fillRoundRect(x, y, w, h, arc, arc);
		g2.setStroke(new BasicStroke(3f));
		g2.setColor(Color.BLACK);
		g2.drawRoundRect(x, y, w, h, arc, arc);
		g2.dispose();
	}
}