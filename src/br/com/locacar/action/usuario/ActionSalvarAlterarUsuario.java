package br.com.locacar.action.usuario;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.usuario.*;
import br.com.locacar.view.usuario.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.opcao.*;
import br.com.locacar.view.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela validação dos campos e pela inclusão do novo usuário no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionSalvarAlterarUsuario implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionSalvarAlterarUsuario.class.getName());
	
	public boolean validarCampos(String usuario, String senha, String confSenha, String perfil, String situacao, JRootPane rootPane) {
		if (usuario.equals("") & senha.equals("") & confSenha.equals("") & perfil.equals("SELECIONE..") & situacao.equals("SELECIONE..")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("camposObrigatorios"), nivelMensagens.ERRO), rootPane);
			FrmUsuarios.txtUsuario.requestFocus();
			return false;
		} else if (usuario.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoUsuario"), nivelMensagens.ERRO), rootPane);
			FrmUsuarios.txtUsuario.requestFocus();
			return false;
		} else if (senha.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoSenha"), nivelMensagens.ERRO), rootPane);
			FrmUsuarios.txtSenha.requestFocus();
			return false;
		} else if (confSenha.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoConfSenha"), nivelMensagens.ERRO), rootPane);
			FrmUsuarios.txtConfSenha.requestFocus();
			return false;
		} else if (perfil.equals("SELECIONE..")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoPerfil"), nivelMensagens.ERRO), rootPane);
			FrmUsuarios.jcbPerfilUsuario.requestFocus();
			return false;
		} else if (situacao.equals("SELECIONE..")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoSituacao"), nivelMensagens.ERRO), rootPane);
			FrmUsuarios.jcbSituacao.requestFocus();
			return false;
		} else if (!confSenha.equals(senha)) {
			Modal.mensagem(new MensagensModel(Bundle.getString("senhasDivergentes"), nivelMensagens.ERRO), rootPane);
			FrmUsuarios.txtConfSenha.requestFocus();
			return false;
		}
		return true;
	}
	
	public void salvar(String codigo, String usuario, String senha, String perfil, String situacao, JRootPane rootPane, Opcoes opcao) {
		Usuarios usuarios = new DAOUsuarios();
		UsuariosModel login = new UsuariosModel();
		if (codigo != "") {
			login.setCod(codigo);
		}
		login.setNome(usuario);
		login.setSenha(senha);
		login.setPerfil(perfil);
		login.setSituacao(situacao);
		if (opcao.equals(Opcoes.SALVAR)) {
			usuarios.salvar(login);
			LOG.info("Novo usuário salvo com sucesso!");
			Modal.mensagem(new MensagensModel(Bundle.getString("userCadExito"), nivelMensagens.INFO), rootPane);
			FrmUsuarios.limparCampos();
		} else if (opcao.equals(Opcoes.ALTERAR)) {
			usuarios.alterar(login);
			LOG.info("O usuário {} foi alterado com sucesso!", login.getNome());
			Modal.mensagem(new MensagensModel(Bundle.getString("userAltExito", usuario), nivelMensagens.INFO), rootPane);
		}
	}
}