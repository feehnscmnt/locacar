package br.com.locacar.action.locacao;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.clientepf.*;
import br.com.locacar.model.clientepj.*;
import br.com.locacar.view.locacao.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Classe action responsável pela busca de clientes no banco de dados para realização da locação!
 * @author Felipe Nascimento
 */
public abstract class ActionBuscarCliente implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionBuscarCliente.class.getName());
	
	public boolean validaCpfCnpj(JRadioButton rdbCpf, JRadioButton rdbCnpj, String cpfCnpj, JRootPane rootPane) {
		if (cpfCnpj.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCpfCnpj"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.txtCpfCnpj.requestFocus();
			return false;
		} else if (rdbCpf.isSelected()) {
			if (!ValidationsUtil.validaCpf(cpfCnpj.replaceAll("\\D", ""))) {
				Modal.mensagem(new MensagensModel(Bundle.getString("cpfInvalido"), nivelMensagens.ERRO), rootPane);
				FrmLocacao.txtCpfCnpj.requestFocus();
				return false;
			}
		} else if (rdbCnpj.isSelected()) {
			if (!ValidationsUtil.validaCnpj(cpfCnpj.replaceAll("\\D", ""))) {
				Modal.mensagem(new MensagensModel(Bundle.getString("cnpjInvalido"), nivelMensagens.ERRO), rootPane);
				FrmLocacao.txtCpfCnpj.requestFocus();
				return false;
			}
		}
		return true;
	}
	
	public static void buscarCliente(String cpfCnpj, JTextField txtNome, JTextField txtEndereco, JTextField txtCidade, JTextField txtBairro, JTextField txtEmail, JRadioButton rdbClientePf, JRadioButton rdbClientePj, JRootPane rootPane) {
		if (rdbClientePf.isSelected()) {
			ClientesPF clientes = new DAOClientesPF();
			ResultSet rs = clientes.buscarParaLocacao(cpfCnpj);
			try {
				if (rs.next()) {
					txtNome.setText(rs.getString("NOME"));
					txtEndereco.setText(rs.getString("ENDERECO"));
					txtCidade.setText(rs.getString("CIDADE"));
					txtBairro.setText(rs.getString("BAIRRO"));
					txtEmail.setText(rs.getString("EMAIL"));
					FrmLocacao.txtPlaca.requestFocus();
				} else {
					LOG.info("O CPF {} não foi encontrado!", cpfCnpj);
					Modal.mensagem(new MensagensModel(Bundle.getString("cpfNaoEncontrado", cpfCnpj), nivelMensagens.ERRO), rootPane);
					txtNome.setText("");
					txtEndereco.setText("");
					txtCidade.setText("");
					txtBairro.setText("");
					txtEmail.setText("");
					FrmLocacao.txtCpfCnpj.requestFocus();
				}
			} catch(SQLException e) {
				LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
			}
		} else if (rdbClientePj.isSelected()) {
			ClientesPJ clientes = new DAOClientesPJ();
			ResultSet rs = clientes.buscarParaLocacao(cpfCnpj);
			try {
				if (rs.next()) {
					txtNome.setText(rs.getString("NOME"));
					txtEndereco.setText(rs.getString("ENDERECO"));
					txtCidade.setText(rs.getString("CIDADE"));
					txtBairro.setText(rs.getString("BAIRRO"));
					txtEmail.setText(rs.getString("EMAIL"));
					FrmLocacao.txtPlaca.requestFocus();
				} else {
					LOG.info("O CNPJ {} não foi encontrado!", cpfCnpj);
					Modal.mensagem(new MensagensModel(Bundle.getString("cnpjNaoEncontrado", cpfCnpj), nivelMensagens.ERRO), rootPane);
					txtNome.setText("");
					txtEndereco.setText("");
					txtCidade.setText("");
					txtBairro.setText("");
					txtEmail.setText("");
					FrmLocacao.txtCpfCnpj.requestFocus();
				}
			} catch(SQLException e) {
				LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}