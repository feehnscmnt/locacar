package br.com.locacar.action.clientepj;

import br.com.locacar.model.clientepj.*;
import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Classe responsável por carregar do banco de dados as informações dos clientes pessoa jurídica para alterações!
 * @author Felipe Nascimento
 */
public abstract class ActionCarregarDadosClientesPJ extends WindowAdapter {
	private static final Logger LOG = LogManager.getLogger(ActionCarregarDadosClientesPJ.class.getName());
	private String info, cpf, cnpj;
	private ResultSet rs;
	
	public void carregarDadosClientes(JLabel codigo, JTextField txtNome, JTextField txtRazaoSocial, JRadioButton rdbCpf, JRadioButton rdbCnpj, JFormattedTextField txtCpfCnpj, JFormattedTextField txtContato, JFormattedTextField txtInscricaoEstadual, JTextField txtEndereco, JFormattedTextField txtCep, JTextField txtCidade, JComboBox<Object> jcbUf, JTextField txtBairro, JTextField txtComplemento, JTextField txtEmail, JTextField txtNomeMotorista, JTextField txtNumeroCnh, JComboBox<Object> jcbCategCnh) {
		LOG.info("Carregando os dados dos clientes pj para alterações...");
		if (codigo.getText() != "") {
			ClientesPJ clientes = new DAOClientesPJ();
			rs = clientes.buscarParaAlteracao(codigo.getText());
			try {
				if (rs.next()) {
					txtNome.setText(rs.getString("NOME"));
					txtRazaoSocial.setText(rs.getString("RAZAOSOCIAL"));
					getCpfCnpj(rdbCpf, rdbCnpj, txtCpfCnpj);
					txtContato.setText(rs.getString("CONTATO"));
					txtInscricaoEstadual.setText(rs.getString("INSCRICAOESTADUAL"));
					txtEndereco.setText(rs.getString("ENDERECO"));
					txtCep.setText(rs.getString("CEP"));
					txtCidade.setText(rs.getString("CIDADE"));
					jcbUf.setSelectedItem(rs.getString("UF"));
					txtBairro.setText(rs.getString("BAIRRO"));
					txtComplemento.setText(rs.getString("COMPLEMENTO"));
					txtEmail.setText(rs.getString("EMAIL"));
					txtNomeMotorista.setText(rs.getString("NOMEMOTORISTA"));
					txtNumeroCnh.setText(rs.getString("NUMEROCNH"));
					jcbCategCnh.setSelectedItem(rs.getString("CATEGCNH"));
					txtNome.requestFocus();
				}
			} catch(SQLException e) {
				LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
			}
		}
		LOG.info("Dados carregados com sucesso!");
	}
	
	private void getCpfCnpj(JRadioButton rdbCpf, JRadioButton rdbCnpj, JFormattedTextField txtCpfCnpj) throws SQLException {
		info = rs.getString("CPFCNPJ");
		if (info.length() == 14) {
			cpf = info;
			rdbCpf.setSelected(true);
			txtCpfCnpj.setText(cpf);
		} else if (info.length() == 18) {
			cnpj = info;
			rdbCnpj.setSelected(true);
			txtCpfCnpj.setText(cnpj);
		}
	}
}