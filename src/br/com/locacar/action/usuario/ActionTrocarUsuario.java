package br.com.locacar.action.usuario;

import br.com.locacar.view.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe respons�vel pela apresenta��o do view de autentica��o para troca de usu�rio!
 * @author Felipe Nascimento
 */
public abstract class ActionTrocarUsuario implements ActionListener {
	public void trocar(JFrame frm) {
		frm.dispose();
		new FrmLogin().setVisible(true);
	}
}