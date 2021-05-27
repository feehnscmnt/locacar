package br.com.locacar.action.usuario;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.view.usuario.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela alteração dos usuários no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionAlterarUsuario implements ActionListener {
	private JRootPane rootPaneFrmUs;
	
	public void alterar(JTable jTabDadosUsuario, JRootPane rootPane) {
		int linha = jTabDadosUsuario.getSelectedRow();
		if (linha != -1) {
			String codigo = String.valueOf(jTabDadosUsuario.getValueAt(jTabDadosUsuario.getSelectedRow(), 0));
			FrmUsuarios frmUsuarios = new FrmUsuarios();
			rootPaneFrmUs = frmUsuarios.getRootPane();
			frmUsuarios.setTitle("LOCACAR - Alterar Usuário");
			frmUsuarios.btnSalvar.setText("Alterar");
			frmUsuarios.btnSalvar.setIcon(PathFilesUtil.getImg("imgAlter.png"));
			frmUsuarios.btnSalvar.setRolloverIcon(PathFilesUtil.getImg("imgAlterOver.png"));
			frmUsuarios.btnSalvar.setToolTipText("Alterar");
			FrmUsuarios.lblAux.setText("ID DO USUÁRIO: ");
			FrmUsuarios.lblCodigo.setText(codigo);
			FrmUsuarios.txtUsuario.requestFocus();
			frmUsuarios.setVisible(true);
			ActionConsultarUsuario.consultar(FrmConsultarUsuarios.txtUsuario, jTabDadosUsuario, FrmConsultarUsuarios.btnAlterar, FrmConsultarUsuarios.btnExcluir, rootPaneFrmUs);
		} else {
			Modal.mensagem(new MensagensModel(Bundle.getString("consGridAlt"), nivelMensagens.ERRO), rootPane);
			FrmConsultarUsuarios.txtUsuario.requestFocus();
		}
	}
}