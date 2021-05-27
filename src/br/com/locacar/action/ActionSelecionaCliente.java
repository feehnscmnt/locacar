package br.com.locacar.action;

import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.view.opcao.*;
import br.com.locacar.domain.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe responsável pela apresentação do Modal para selecionar o view de clientes PF ou PJ!
 * @author Felipe Nascimento
 */
public abstract class ActionSelecionaCliente implements ActionListener {
	public void selecionar(JFrame frm, Opcoes opcao, JRootPane rootPaneFrame) {
		Modal.selecionaView(new MensagensModel(Bundle.getString("selecionarView")), frm, opcao, rootPaneFrame);
	}
}