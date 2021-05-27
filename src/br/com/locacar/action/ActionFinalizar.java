package br.com.locacar.action;

import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela apresentação do Modal encerramento do software!
 * @author Felipe Nascimento
 */
public abstract class ActionFinalizar implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionFinalizar.class.getName());
	
	public void finalizar(JTextField txtUsuario) {
		int fechar = JOptionPane.showConfirmDialog(null, new MensagensModel(Bundle.getString("finalizar")).getText(), "FINALIZAR", JOptionPane.YES_NO_OPTION);
		if (fechar == JOptionPane.YES_OPTION) {
			LOG.info("Sistema encerrado!");
            System.exit(0);
		} else {
			txtUsuario.requestFocus();
		}
	}
	
	public void finalizar() {
		int fechar = JOptionPane.showConfirmDialog(null, new MensagensModel(Bundle.getString("finalizar")).getText(), "FINALIZAR", JOptionPane.YES_NO_OPTION);
		if (fechar == JOptionPane.YES_OPTION) {
			LOG.info("Sistema encerrado!");
            System.exit(0);
		}
	}
}