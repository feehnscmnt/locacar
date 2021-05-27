package br.com.locacar.action;

import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe respons�vel pela apresenta��o do Modal para trocar usu�rio ou finalizar o software!
 * @author Felipe Nascimento
 */
public abstract class ActionLogoff implements ActionListener {
	public void logoff(JFrame frm, JRootPane rootPaneFrame) {
		Modal.trocaFinaliza(new MensagensModel(Bundle.getString("trocarFinalizar")), frm, rootPaneFrame);
	}
}