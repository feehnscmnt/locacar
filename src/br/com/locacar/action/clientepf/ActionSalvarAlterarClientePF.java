
package br.com.locacar.action.clientepf;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.clientepf.*;
import br.com.locacar.view.clientepf.*;
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
 * Classe action responsável pela validação dos campos e pela inclusão do novo cliente pessoa física no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionSalvarAlterarClientePF implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionSalvarAlterarClientePF.class.getName());
	
	public boolean validarCampos(String nome, String cpf, String rg, String contato, String dataNasc, String categCnh, String numeroCnh, String email, String endereco, String cep, String cidade, String uf, String bairro, String complemento, JRootPane rootPane) {
		if (nome.equals("") & cpf.replaceAll("\\D", "").equals("") & rg.replaceAll("\\D", "").equals("") & contato.replaceAll("\\D", "").equals("") & dataNasc.equals("") & categCnh.equals("SELECIONE..") & numeroCnh.equals("") & email.equals("") &
			endereco.equals("") & cep.replaceAll("\\D", "").equals("") & cidade.equals("") & uf.equals("") & bairro.equals("") & complemento.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("camposObrigatorios"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtNome.requestFocus();
			return false;
		} else if (nome.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoNome"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtNome.requestFocus();
			return false;
		} else if (cpf.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCpf"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtCpf.requestFocus();
			return false;
		} else if (rg.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoRg"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtRg.requestFocus();
			return false;
		} else if (contato.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoContato"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtTelCel.requestFocus();
			return false;
		} else if (dataNasc.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoDataNasc"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.jdcDataNasc.requestFocus();
			return false;
		} else if (categCnh.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCategCnh"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.jcbCategCnh.requestFocus();
			return false;
		} else if (numeroCnh.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoNumeroCnh"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtNumCnh.requestFocus();
			return false;
		} else if (email.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoEmail"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtEmail.requestFocus();
			return false;
		} else if (endereco.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoEndereco"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtEndereco.requestFocus();
			return false;
		} else if (cep.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCep"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtCep.requestFocus();
			return false;
		} else if (cidade.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCidade"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtCidade.requestFocus();
			return false;
		} else if (uf.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoUf"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.jcbUf.requestFocus();
			return false;
		} else if (bairro.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoBairro"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtBairro.requestFocus();
			return false;
		} else if (complemento.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoComplemento"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtComplemento.requestFocus();
			return false;
		} else if (!ValidationsUtil.validaCpf(cpf.replaceAll("\\D", ""))) {
			Modal.mensagem(new MensagensModel(Bundle.getString("cpfInvalido"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtCpf.requestFocus();
			return false;
		} else if (!ValidationsUtil.validaEmail(email)) {
			Modal.mensagem(new MensagensModel(Bundle.getString("emailInvalido"), nivelMensagens.ERRO), rootPane);
			FrmClientePF.txtEmail.requestFocus();
			return false;
		}
		return true;
	}
	
	public void salvar(String codigo, String nome, String cpf, String rg, String contato, String dataNasc, String categCnh, String numeroCnh, String email, String endereco, String cep, String cidade, String uf, String bairro, String complemento, String imagem, JRootPane rootPane, Opcoes opcao) {
		ClientesPFModel cliente = new ClientesPFModel();
		ClientesPF clientes = new DAOClientesPF();
		if (codigo != "") {
			cliente.setCod(codigo);
		}
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setRg(rg);
		cliente.setContato(contato);
		cliente.setDataNasc(dataNasc);
		cliente.setCategCnh(categCnh);
		cliente.setNumeroCnh(numeroCnh);
		cliente.setEmail(email);
		cliente.setEndereco(endereco);
		cliente.setCep(cep);
		cliente.setCidade(cidade);
		cliente.setUf(uf);
		cliente.setBairro(bairro);
		cliente.setComplemento(complemento);
		cliente.setImagem(imagem);
		if (opcao.equals(Opcoes.SALVAR)) {
			try {
				ResultSet rs = clientes.verificarSeExiste(cpf);
				if (rs.next()) {
					Modal.mensagem(new MensagensModel(Bundle.getString("clientePFExiste", cpf), nivelMensagens.ERRO), rootPane);
					return;
				} else {
					clientes.salvar(cliente);
					LOG.info("Novo cliente pf salvo com sucesso!");
					Modal.mensagem(new MensagensModel(Bundle.getString("clienteCadExito"), nivelMensagens.INFO), rootPane);
					FrmClientePF.limparCampos();
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