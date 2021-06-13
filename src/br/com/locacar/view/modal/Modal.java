package br.com.locacar.view.modal;

import br.com.locacar.model.modal.*;
import br.com.locacar.enums.*;
import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;

/**
 * Classe que apresenta aos usuários os Modal's!
 * @author Felipe Nascimento
 */
public class Modal {
	private static Timer timer;
	
	public static void mensagem(MensagensModel msgns, JRootPane rootPane) {
		JPanel glassPane = (JPanel) rootPane.getGlassPane();
		final BoxModalMensagens boxMsgn = new BoxModalMensagens(msgns);
		if (!Arrays.asList(glassPane.getComponents()).contains(boxMsgn)) {
			glassPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));
			glassPane.add(boxMsgn);
			glassPane.setVisible(true);
			glassPane.repaint();
			glassPane.revalidate();
			timer = new Timer(4000, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object obj = boxMsgn.getParent().getParent();
					if (obj instanceof JRootPane) {
						JRootPane rootPane = (JRootPane) obj;
						JPanel glassPane = (JPanel) rootPane.getGlassPane();
						glassPane.removeAll();
						glassPane.repaint();
						glassPane.revalidate();
						if (glassPane.getComponentCount() == 0) {
							glassPane.setVisible(false);
						}
					}
					timer.stop();
				}
			});
			timer.start();
		}
	}
	
	public static void trocaFinaliza(MensagensModel texto, JFrame frm, JRootPane rootPane) {
		JPanel glassPane = (JPanel) rootPane.getGlassPane();
		final BoxModalTrocaFinaliza boxMsgn = new BoxModalTrocaFinaliza(frm, texto);
		if (!Arrays.asList(glassPane.getComponents()).contains(boxMsgn)) {
			glassPane.setLayout(new FlowLayout(FlowLayout.CENTER, 320, 309));
			glassPane.add(boxMsgn);
			glassPane.setVisible(true);
			glassPane.repaint();
			glassPane.revalidate();
		}
	}
	
	public static void selecionaView(MensagensModel texto, JFrame frm, Opcoes opcao, JRootPane rootPane) {
		JPanel glassPane = (JPanel) rootPane.getGlassPane();
		final BoxModalSelecionaView boxMsgn = new BoxModalSelecionaView(frm, opcao, texto);
		if (!Arrays.asList(glassPane.getComponents()).contains(boxMsgn)) {
			glassPane.setLayout(new FlowLayout(FlowLayout.CENTER, 320, 309));
			glassPane.add(boxMsgn);
			glassPane.setVisible(true);
			glassPane.repaint();
			glassPane.revalidate();
		}
	}
}