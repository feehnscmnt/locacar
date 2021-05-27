package br.com.locacar.action.usuario;

import br.com.locacar.view.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe responsável pela apresentação do view de autenticação para troca de usuário!
 * @author Felipe Nascimento
 */
public abstract class ActionTrocarUsuario implements ActionListener {
	public void trocar(JFrame frm) {
		frm.dispose();
		new FrmLogin().setVisible(true);
	}
}