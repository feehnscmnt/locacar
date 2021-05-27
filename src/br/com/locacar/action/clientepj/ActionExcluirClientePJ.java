package br.com.locacar.action.clientepj;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.clientepj.*;
import br.com.locacar.view.clientepj.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela exclusão dos clientes pessoa jurídica no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionExcluirClientePJ implements ActionListener {
	private JRootPane rootPaneFrmCliPj;
	
	public void excluir(JTable jTabDadosCliente, JRootPane rootPane) {
		int linha = jTabDadosCliente.getSelectedRow();
		if (linha != -1) {
			String nome = String.valueOf(jTabDadosCliente.getValueAt(jTabDadosCliente.getSelectedRow(), 1));
			int exc = JOptionPane.showConfirmDialog(null, new MensagensModel(Bundle.getString("userExcluir", nome)).getText(), "EXCLUIR", JOptionPane.YES_NO_OPTION);
			if (exc == JOptionPane.YES_OPTION) {
				FrmClientePJ frmClientes = new FrmClientePJ();
				ClientesPJ clientes = new DAOClientesPJ();
				rootPaneFrmCliPj = frmClientes.getRootPane();
				ClientesPJModel cliente = new ClientesPJModel();
				cliente.setNome(nome);
				clientes.excluir(cliente);
				Modal.mensagem(new MensagensModel(Bundle.getString("clienteExcluido"), nivelMensagens.INFO), rootPane);
				ActionConsultarClientePJ.consultarPorCpfCnpj(FrmConsultarClientesPJ.rdbCpf, FrmConsultarClientesPJ.rdbCnpj, FrmConsultarClientesPJ.txtCpfCnpj, FrmConsultarClientesPJ.jTabDadosCliente, FrmConsultarClientesPJ.btnAlterar, FrmConsultarClientesPJ.btnExcluir, rootPaneFrmCliPj);
			} else {
				Modal.mensagem(new MensagensModel(Bundle.getString("exclusaoCancelada"), nivelMensagens.INFO), rootPane);
				FrmConsultarClientesPJ.txtCpfCnpj.requestFocus();
			}
		} else {
			Modal.mensagem(new MensagensModel(Bundle.getString("consGridExc"), nivelMensagens.ERRO), rootPane);
			FrmConsultarClientesPJ.txtCpfCnpj.requestFocus();
		}
	}
}