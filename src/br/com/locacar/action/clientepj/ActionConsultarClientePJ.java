package br.com.locacar.action.clientepj;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.clientepj.*;
import br.com.locacar.view.clientepj.*;
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
 * Classe action responsável pela consulta de clientes pessoa jurídica no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionConsultarClientePJ implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionConsultarClientePJ.class.getName());
	
	public boolean validaCpf(JRadioButton rdbCpf, JRadioButton rdbCnpj, String cpfCnpj, JRootPane rootPane) {
		if (cpfCnpj.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCpfCnpj"), nivelMensagens.ERRO), rootPane);
			FrmConsultarClientesPJ.txtCpfCnpj.requestFocus();
			return false;
		} else if (rdbCpf.isSelected()) {
			if (!ValidationsUtil.validaCpf(cpfCnpj.replaceAll("\\D", ""))) {
				Modal.mensagem(new MensagensModel(Bundle.getString("cpfInvalido"), nivelMensagens.ERRO), rootPane);
				FrmConsultarClientesPJ.txtCpfCnpj.requestFocus();
				return false;
			}
		} else if (rdbCnpj.isSelected()) {
			if (!ValidationsUtil.validaCnpj(cpfCnpj.replaceAll("\\D", ""))) {
				Modal.mensagem(new MensagensModel(Bundle.getString("cnpjInvalido"), nivelMensagens.ERRO), rootPane);
				FrmConsultarClientesPJ.txtCpfCnpj.requestFocus();
				return false;
			}
		}
		return true;
	}
	
	public static void consultarPorCpfCnpj(JRadioButton rdbCpf, JRadioButton rdbCnpj, JFormattedTextField txtCpfCnpj, JTable jTabDadosCliente, JButton btnAlterar, JButton btnExcluir, JRootPane rootPane) {
		txtCpfCnpj.requestFocus();
		ClientesPJ clientes = new DAOClientesPJ();
		ResultSet rs = clientes.buscarPorCpfCnpj(txtCpfCnpj.getText());
		DefaultTableModel dtm = (DefaultTableModel) jTabDadosCliente.getModel();
		dtm.setNumRows(0);
		try {
			while (rs.next()) {
				dtm.addRow(new Object[] { rs.getString("CODIGO"), rs.getString("NOME"), rs.getString("RAZAOSOCIAL"), rs.getString("CPFCNPJ"), rs.getString("CONTATO"), rs.getString("INSCRICAOESTADUAL"), rs.getString("ENDERECO"), rs.getString("CEP"), rs.getString("CIDADE"), rs.getString("UF"), rs.getString("BAIRRO"), rs.getString("COMPLEMENTO"), rs.getString("EMAIL"), rs.getString("NOMEMOTORISTA"), rs.getString("NUMEROCNH"), rs.getString("CATEGCNH") } );
			}
			if (dtm.getRowCount() == 0) {
				btnAlterar.setEnabled(false);
				btnExcluir.setEnabled(false);
				if (rdbCpf.isSelected()) {
					LOG.info("O CPF {} não foi encontrado!", txtCpfCnpj.getText());
					Modal.mensagem(new MensagensModel(Bundle.getString("cpfNaoEncontrado", txtCpfCnpj.getText()), nivelMensagens.ERRO), rootPane);
				} else if (rdbCnpj.isSelected()) {
					LOG.info("O CNPJ {} não foi encontrado!", txtCpfCnpj.getText());
					Modal.mensagem(new MensagensModel(Bundle.getString("cnpjNaoEncontrado", txtCpfCnpj.getText()), nivelMensagens.ERRO), rootPane);
				}
				txtCpfCnpj.requestFocus();
			} else {
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);
			}
		} catch(SQLException e) {
			LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void consultarTodos(JFormattedTextField txtCpfCnpj, JTable jTabDadosCliente, JButton btnAlterar, JButton btnExcluir, JRootPane rootPane) {
		txtCpfCnpj.requestFocus();
		ClientesPJ clientes = new DAOClientesPJ();
		ResultSet rs = clientes.buscarTodos();
		DefaultTableModel dtm = (DefaultTableModel) jTabDadosCliente.getModel();
		dtm.setNumRows(0);
		try {
			while (rs.next()) {
				dtm.addRow(new Object[] { rs.getString("CODIGO"), rs.getString("NOME"), rs.getString("RAZAOSOCIAL"), rs.getString("CPFCNPJ"),
				rs.getString("CONTATO"), rs.getString("INSCRICAOESTADUAL"), rs.getString("ENDERECO"), rs.getString("CEP"), rs.getString("CIDADE"),
				rs.getString("UF"), rs.getString("BAIRRO"), rs.getString("COMPLEMENTO"), rs.getString("EMAIL"), rs.getString("NOMEMOTORISTA"),
				rs.getString("NUMEROCNH"), rs.getString("CATEGCNH") } );
			}
			if (dtm.getRowCount() == 0) {
				btnAlterar.setEnabled(false);
				btnExcluir.setEnabled(false);
				if (txtCpfCnpj.getText().equals("")) {
					LOG.info("Nenhum cliente foi encontrado!");
					Modal.mensagem(new MensagensModel(Bundle.getString("clienteNaoEncontrado"), nivelMensagens.ERRO), rootPane);
				}
				txtCpfCnpj.requestFocus();
			} else {
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);
			}
		} catch(SQLException e) {
			LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
}