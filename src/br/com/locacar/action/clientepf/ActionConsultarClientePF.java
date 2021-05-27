package br.com.locacar.action.clientepf;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.clientepf.*;
import br.com.locacar.view.clientepf.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import br.com.locacar.dao.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Classe action responsável pela consulta de clientes pessoa física no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionConsultarClientePF implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionConsultarClientePF.class.getName());
	
	public boolean validaCpf(String cpf, JRootPane rootPane) {
		if (cpf.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCpf"), nivelMensagens.ERRO), rootPane);
			FrmConsultarClientesPF.txtCpf.requestFocus();
			return false;
		} else if (!ValidationsUtil.validaCpf(cpf.replaceAll("\\D", ""))) {
			Modal.mensagem(new MensagensModel(Bundle.getString("cpfInvalido"), nivelMensagens.ERRO), rootPane);
			FrmConsultarClientesPF.txtCpf.requestFocus();
			return false;
		}
		return true;
	}
	
	public static void consultarPorCpf(JFormattedTextField txtCpf, JTable jTabDadosCliente, JButton btnAlterar, JButton btnExcluir, JRootPane rootPane) {
		txtCpf.requestFocus();
		ClientesPF clientes = new DAOClientesPF();
		ResultSet rs = clientes.buscarPorCpf(txtCpf.getText());
		DefaultTableModel dtm = (DefaultTableModel) jTabDadosCliente.getModel();
		dtm.setNumRows(0);
		try {
			while (rs.next()) {
				dtm.addRow(new Object[] { rs.getString("CODIGO"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("RG"), rs.getString("CONTATO"), rs.getString("DATANASCIMENTO"), rs.getString("CATEGCNH"), rs.getString("NUMEROCNH"), rs.getString("EMAIL"), rs.getString("ENDERECO"), rs.getString("CEP"), rs.getString("CIDADE"), rs.getString("UF"), rs.getString("BAIRRO"), rs.getString("COMPLEMENTO") } );
			}
			if (dtm.getRowCount() == 0) {
				btnAlterar.setEnabled(false);
				btnExcluir.setEnabled(false);
				if (txtCpf.getText().equals("")) {
					LOG.info("Nenhum cliente foi encontrado!");
					Modal.mensagem(new MensagensModel(Bundle.getString("clienteNaoEncontrado"), nivelMensagens.ERRO), rootPane);
				} else {
					LOG.info("O CPF {} não foi encontrado!", txtCpf.getText());
					Modal.mensagem(new MensagensModel(Bundle.getString("cpfNaoEncontrado", txtCpf.getText()), nivelMensagens.ERRO), rootPane);
				}
				txtCpf.requestFocus();
			} else {
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);
			}
		} catch(SQLException e) {
			LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void consultarTodos(JFormattedTextField txtCpf, JTable jTabDadosCliente, JButton btnAlterar, JButton btnExcluir, JRootPane rootPane) {
		txtCpf.requestFocus();
		ClientesPF clientes = new DAOClientesPF();
		ResultSet rs = clientes.buscarTodos();
		DefaultTableModel dtm = (DefaultTableModel) jTabDadosCliente.getModel();
		dtm.setNumRows(0);
		try {
			while (rs.next()) {
				dtm.addRow(new Object[] { rs.getString("CODIGO"), rs.getString("NOME"), rs.getString("CPF"), rs.getString("RG"), rs.getString("CONTATO"), rs.getString("DATANASCIMENTO"), rs.getString("CATEGCNH"), rs.getString("NUMEROCNH"), rs.getString("EMAIL"), rs.getString("ENDERECO"), rs.getString("CEP"), rs.getString("CIDADE"), rs.getString("UF"), rs.getString("BAIRRO"), rs.getString("COMPLEMENTO") } );
			}
			if (dtm.getRowCount() == 0) {
				btnAlterar.setEnabled(false);
				btnExcluir.setEnabled(false);
				if (txtCpf.getText().equals("")) {
					LOG.info("Nenhum cliente foi encontrado!");
					Modal.mensagem(new MensagensModel(Bundle.getString("clienteNaoEncontrado"), nivelMensagens.ERRO), rootPane);
				}
				txtCpf.requestFocus();
			} else {
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);
			}
		} catch(SQLException e) {
			LOG.info("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
}