package br.com.locacar.action.clientepj;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.view.clientepj.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela alteração dos clientes pessoa jurídica no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionAlterarClientePJ implements ActionListener {
	private JRootPane rootPaneFrmCliPj;
	
	public void alterar(JTable jTabDadosCliente, JRootPane rootPane) {
		int linha = jTabDadosCliente.getSelectedRow();
		if (linha != -1) {
			String codigo = String.valueOf(jTabDadosCliente.getValueAt(jTabDadosCliente.getSelectedRow(), 0));
			FrmClientePJ frmClientes = new FrmClientePJ();
			rootPaneFrmCliPj = frmClientes.getRootPane();
			frmClientes.setTitle("LOCACAR - Alterar cliente pessoa física");
			frmClientes.btnSalvar.setText("Alterar");
			frmClientes.btnSalvar.setIcon(PathFilesUtil.getImg("imgAlter.png"));
			frmClientes.btnSalvar.setRolloverIcon(PathFilesUtil.getImg("imgAlterOver.png"));
			frmClientes.btnSalvar.setToolTipText("Alterar");
			FrmClientePJ.lblAux.setText("ID DO CLIENTE: ");
			FrmClientePJ.lblCodigo.setText(codigo);
			FrmClientePJ.txtNome.requestFocus();
			frmClientes.setVisible(true);
			ActionConsultarClientePJ.consultarPorCpfCnpj(FrmConsultarClientesPJ.rdbCpf, FrmConsultarClientesPJ.rdbCnpj, FrmConsultarClientesPJ.txtCpfCnpj, FrmConsultarClientesPJ.jTabDadosCliente, FrmConsultarClientesPJ.btnAlterar, FrmConsultarClientesPJ.btnExcluir, rootPaneFrmCliPj);
		} else {
			Modal.mensagem(new MensagensModel(Bundle.getString("consGridAlt"), nivelMensagens.ERRO), rootPane);
			FrmConsultarClientesPJ.txtCpfCnpj.requestFocus();
		}
	}
}