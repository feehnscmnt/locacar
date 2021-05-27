package br.com.locacar.action.clientepf;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.view.clientepf.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela alteração dos clientes pessoa física no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionAlterarClientePF implements ActionListener {
	private JRootPane rootPaneFrmCliPf;
	
	public void alterar(JTable jTabDadosCliente, JRootPane rootPane) {
		int linha = jTabDadosCliente.getSelectedRow();
		if (linha != -1) {
			String codigo = String.valueOf(jTabDadosCliente.getValueAt(jTabDadosCliente.getSelectedRow(), 0));
			FrmClientePF frmClientes = new FrmClientePF();
			rootPaneFrmCliPf = frmClientes.getRootPane();
			frmClientes.setTitle("LOCACAR - Alterar cliente pessoa física");
			frmClientes.btnSalvar.setText("Alterar");
			frmClientes.btnSalvar.setIcon(PathFilesUtil.getImg("imgAlter.png"));
			frmClientes.btnSalvar.setRolloverIcon(PathFilesUtil.getImg("imgAlterOver.png"));
			frmClientes.btnSalvar.setToolTipText("Alterar");
			FrmClientePF.lblAux.setText("ID DO CLIENTE: ");
			FrmClientePF.lblCodigo.setText(codigo);
			FrmClientePF.txtNome.requestFocus();
			frmClientes.setVisible(true);
			ActionConsultarClientePF.consultarPorCpf(FrmConsultarClientesPF.txtCpf, FrmConsultarClientesPF.jTabDadosCliente, FrmConsultarClientesPF.btnAlterar, FrmConsultarClientesPF.btnExcluir, rootPaneFrmCliPf);
		} else {
			Modal.mensagem(new MensagensModel(Bundle.getString("consGridAlt"), nivelMensagens.ERRO), rootPane);
			FrmConsultarClientesPF.txtCpf.requestFocus();
		}
	}
}