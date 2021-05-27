package br.com.locacar.action.clientepf;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.clientepf.*;
import br.com.locacar.view.clientepf.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela exclusão dos clientes pessoa física no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionExcluirClientePF implements ActionListener {
	private JRootPane rootPaneFrmCliPf;
	
	public void excluir(JTable jTabDadosCliente, JRootPane rootPane) {
		int linha = jTabDadosCliente.getSelectedRow();
		if (linha != -1) {
			String nome = String.valueOf(jTabDadosCliente.getValueAt(jTabDadosCliente.getSelectedRow(), 1));
			int exc = JOptionPane.showConfirmDialog(null, new MensagensModel(Bundle.getString("userExcluir", nome)).getText(), "EXCLUIR", JOptionPane.YES_NO_OPTION);
			if (exc == JOptionPane.YES_OPTION) {
				FrmClientePF frmClientes = new FrmClientePF();
				ClientesPF clientes = new DAOClientesPF();
				rootPaneFrmCliPf = frmClientes.getRootPane();
				ClientesPFModel cliente = new ClientesPFModel();
				cliente.setNome(nome);
				clientes.excluir(cliente);
				Modal.mensagem(new MensagensModel(Bundle.getString("clienteExcluido"), nivelMensagens.INFO), rootPane);
				ActionConsultarClientePF.consultarPorCpf(FrmConsultarClientesPF.txtCpf, FrmConsultarClientesPF.jTabDadosCliente, FrmConsultarClientesPF.btnAlterar, FrmConsultarClientesPF.btnExcluir, rootPaneFrmCliPf);
			} else {
				Modal.mensagem(new MensagensModel(Bundle.getString("exclusaoCancelada"), nivelMensagens.INFO), rootPane);
				FrmConsultarClientesPF.txtCpf.requestFocus();
			}
		} else {
			Modal.mensagem(new MensagensModel(Bundle.getString("consGridExc"), nivelMensagens.ERRO), rootPane);
			FrmConsultarClientesPF.txtCpf.requestFocus();
		}
	}
}