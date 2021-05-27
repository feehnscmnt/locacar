package br.com.locacar.action.usuario;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.usuario.*;
import br.com.locacar.view.usuario.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela exclusão dos usuários no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionExcluirUsuario implements ActionListener {
	private JRootPane rootPaneFrmUs;
	
	public void excluir(JTable jTabDadosUsuario, JRootPane rootPane) {
		int linha = jTabDadosUsuario.getSelectedRow();
		if (linha != -1) {
			String nome = String.valueOf(jTabDadosUsuario.getValueAt(jTabDadosUsuario.getSelectedRow(), 1));
			int exc = JOptionPane.showConfirmDialog(null, new MensagensModel(Bundle.getString("userExcluir", nome)).getText(), "EXCLUIR", JOptionPane.YES_NO_OPTION);
			if (exc == JOptionPane.YES_OPTION) {
				Usuarios usuarios = new DAOUsuarios();
				FrmUsuarios frmUsuarios = new FrmUsuarios();
				rootPaneFrmUs = frmUsuarios.getRootPane();
				UsuariosModel login = new UsuariosModel();
				login.setNome(nome);
				usuarios.excluir(login);
				Modal.mensagem(new MensagensModel(Bundle.getString("userExcluido"), nivelMensagens.INFO), rootPane);
				ActionConsultarUsuario.consultar(FrmConsultarUsuarios.txtUsuario, jTabDadosUsuario, FrmConsultarUsuarios.btnAlterar, FrmConsultarUsuarios.btnExcluir, rootPaneFrmUs);
			} else {
				Modal.mensagem(new MensagensModel(Bundle.getString("exclusaoCancelada"), nivelMensagens.INFO), rootPane);
				FrmConsultarUsuarios.txtUsuario.requestFocus();
			}
		} else {
			Modal.mensagem(new MensagensModel(Bundle.getString("consGridExc"), nivelMensagens.ERRO), rootPane);
			FrmConsultarUsuarios.txtUsuario.requestFocus();
		}
	}
}