package br.com.locacar.action.clientepj;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.clientepj.*;
import br.com.locacar.view.clientepj.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.opcao.*;
import br.com.locacar.view.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Classe action responsável pela validação dos campos e pela inclusão do novo cliente pessoa jurídica no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionSalvarAlterarClientePJ implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionSalvarAlterarClientePJ.class.getName());
	
	public boolean validarCampos(String nome, String razaoSocial, JRadioButton rdbCpf, JRadioButton rdbCnpj, String cpfCnpj, String contato, String inscricaoEstadual, String endereco, String cep, String cidade, String uf, String bairro, String complemento, String email, String nomeMotorista, String numeroCnh, String categCnh, JRootPane rootPane) {
		if (nome.equals("") & razaoSocial.equals("") & cpfCnpj.replaceAll("\\D", "").equals("") & contato.replaceAll("\\D", "").equals("") & inscricaoEstadual.replaceAll("\\D", "").equals("") & endereco.equals("") &
			cep.replaceAll("\\D", "").equals("") & cidade.equals("") & uf.equals("") & bairro.equals("") & complemento.equals("") & email.equals("") & nomeMotorista.equals("") & numeroCnh.equals("") & categCnh.equals("SELECIONE..")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("camposObrigatorios"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtNome.requestFocus();
			return false;
		} else if (nome.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoNome"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtNome.requestFocus();
			return false;
		} else if (razaoSocial.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoRazaoSocial"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtRazaoSocial.requestFocus();
			return false;
		} else if (cpfCnpj.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCpfCnpj"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtCpfCnpj.requestFocus();
			return false;
		} else if (contato.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoContato"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtTelCel.requestFocus();
			return false;
		} else if (inscricaoEstadual.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoInscEstadual"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtInscricaoEstadual.requestFocus();
			return false;
		} else if (endereco.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoEndereco"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtEndereco.requestFocus();
			return false;
		} else if (cep.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCep"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtCep.requestFocus();
			return false;
		} else if (cidade.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCidade"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtCidade.requestFocus();
			return false;
		} else if (uf.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoUf"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.jcbUf.requestFocus();
			return false;
		} else if (bairro.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoBairro"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtBairro.requestFocus();
			return false;
		} else if (complemento.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoComplemento"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtComplemento.requestFocus();
			return false;
		} else if (email.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoEmail"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtEmail.requestFocus();
			return false;
		} else if (nomeMotorista.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoNomeMotorista"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtNomeMotorista.requestFocus();
			return false;
		} else if (numeroCnh.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoNumeroCnh"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtNumCnh.requestFocus();
			return false;
		} else if (categCnh.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCategCnh"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.jcbCategCnh.requestFocus();
			return false;
		} else if (rdbCpf.isSelected()) {
			if (!ValidationsUtil.validaCpf(cpfCnpj.replaceAll("\\D", ""))) {
				Modal.mensagem(new MensagensModel(Bundle.getString("cpfInvalido"), nivelMensagens.ERRO), rootPane);
				FrmClientePJ.txtCpfCnpj.requestFocus();
				return false;
			}
		} else if (rdbCnpj.isSelected()) {
			if (!ValidationsUtil.validaCnpj(cpfCnpj.replaceAll("\\D", ""))) {
				Modal.mensagem(new MensagensModel(Bundle.getString("cnpjInvalido"), nivelMensagens.ERRO), rootPane);
				FrmClientePJ.txtCpfCnpj.requestFocus();
				return false;
			}
		} else if (!ValidationsUtil.validaEmail(email)) {
			Modal.mensagem(new MensagensModel(Bundle.getString("emailInvalido"), nivelMensagens.ERRO), rootPane);
			FrmClientePJ.txtEmail.requestFocus();
			return false;
		}
		return true;
	}
	
	public void salvar(String codigo, String nome, String razaoSocial, String cpfCnpj, String contato, String inscricaoEstadual, String endereco, String cep, String cidade, String uf, String bairro, String complemento, String email, String nomeMotorista, String numeroCnh, String categCnh, JRootPane rootPane, Opcoes opcao) {
		ClientesPJModel cliente = new ClientesPJModel();
		ClientesPJ clientes = new DAOClientesPJ();
		if (codigo != "") {
			cliente.setCod(codigo);
		}
		cliente.setNome(nome);
		cliente.setRazaoSocial(razaoSocial);
		cliente.setCpfCnpj(cpfCnpj);
		cliente.setContato(contato);
		cliente.setInscricaoEstadual(inscricaoEstadual);
		cliente.setEndereco(endereco);
		cliente.setCep(cep);
		cliente.setCidade(cidade);
		cliente.setUf(uf);
		cliente.setBairro(bairro);
		cliente.setComplemento(complemento);
		cliente.setEmail(email);
		cliente.setNomeMotorista(nomeMotorista);
		cliente.setNumeroCnh(numeroCnh);
		cliente.setCategCnh(categCnh);
		if (opcao.equals(Opcoes.SALVAR)) {
			try {
				ResultSet rs = clientes.verificarSeExiste(cpfCnpj);
				if (rs.next()) {
					Modal.mensagem(new MensagensModel(Bundle.getString("clientePJExiste", cpfCnpj), nivelMensagens.ERRO), rootPane);
					return;
				} else {
					clientes.salvar(cliente);
					LOG.info("Novo cliente pj salvo com sucesso!");
					Modal.mensagem(new MensagensModel(Bundle.getString("clienteCadExito"), nivelMensagens.INFO), rootPane);
					FrmClientePJ.limparCampos();
				}
			} catch(SQLException e) {
				LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
			}
		} else if (opcao.equals(Opcoes.ALTERAR)) {
			clientes.alterar(cliente);
			LOG.info("O cliente {} foi alterado com sucesso!", cliente.getNome());
			Modal.mensagem(new MensagensModel(Bundle.getString("clienteAltExito", nome), nivelMensagens.INFO), rootPane);
		}
	}
}