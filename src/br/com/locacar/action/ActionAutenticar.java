package br.com.locacar.action;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.usuario.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.view.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Classe respons�vel pela autentica��o dos usu�rios!
 * @author Felipe Nascimento
 */
public abstract class ActionAutenticar implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionAutenticar.class.getName());
	private FrmPrincipal princ;
	
	public void autenticar(JFrame frm, String usuario, Object perfil, String senha, JRootPane rootPane) {
		LOG.info("Realizando a autentica��o do usu�rio...");
		UsuariosModel loginModel = new UsuariosModel();
		Usuarios usuarios = new DAOUsuarios();
		loginModel.setNome(usuario);
		ResultSet rs = usuarios.buscarParaAutenticar(loginModel);
		try {
			if (rs.next()) {
				if (rs.getString("SENHA").equals(senha)) {
					if (rs.getString("SITUACAO").equals("INATIVO")) {
						LOG.info("Falha na autentica��o, usu�rio inativo no banco de dados!");
						Modal.mensagem(new MensagensModel(Bundle.getString("userInativo", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
						FrmLogin.limpaCampos();
					} else if (rs.getString("PERFIL").equals(perfil)) {
						if (perfil.equals("ADMINISTRADOR")) {
							LOG.info("Autentica��o realizada com sucesso!");
							frm.dispose();
							princ = new FrmPrincipal();
							princ.setVisible(true);
							FrmLogin.limpaCampos();
						} else if (perfil.equals("B�SICO")) {
							LOG.info("Autentica��o realizada com sucesso!");
							frm.dispose();
							princ = new FrmPrincipal();
							FrmPrincipal.btnNovoUsuario.setEnabled(false);
							FrmPrincipal.btnPesquisarUsuario.setEnabled(false);
							princ.setVisible(true);
							FrmLogin.limpaCampos();
						}
					} else if (perfil.equals("SELECIONE..")) {
						LOG.info("Falha na autentica��o, perfil do usu�rio n�o selecionado!");
						Modal.mensagem(new MensagensModel(Bundle.getString("userSelecionarPerfil", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
						FrmLogin.limpaCampos();
					} else if (rs.getString("STATUS").equals(String.valueOf(0))) {
						LOG.info("Falha na autentica��o, usu�rio exclu�do no banco de dados!");
						Modal.mensagem(new MensagensModel(Bundle.getString("userExcluidoBase"), nivelMensagens.ERRO), rootPane);
						FrmLogin.limpaCampos();
					} else if (perfil != rs.getString("PERFIL")) {
						LOG.info("Falha na autentica��o, perfil selecionado incompat�vel com o usu�rio!");
						Modal.mensagem(new MensagensModel(Bundle.getString("userPerfilIncorreto", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
						FrmLogin.limpaCampos();
					}
				} else {
					LOG.info("Falha na autentica��o, senha incorreta!");
					Modal.mensagem(new MensagensModel(Bundle.getString("userSenhaIncorreta", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
					FrmLogin.limpaCampos();
				}
			} else if (usuario.equals("") && senha.equals("")) {
				LOG.info("Falha na autentica��o, dados do usu�rio n�o informados!");
				Modal.mensagem(new MensagensModel(Bundle.getString("userInformarAcessos", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
				FrmLogin.limpaCampos();
			} else {
				LOG.info("Falha na autentica��o, usu�rio n�o cadastrado no banco de dados!");
				Modal.mensagem(new MensagensModel(Bundle.getString("userNaoCadastrado", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
				FrmLogin.limpaCampos();
			}
		} catch(SQLException e) {
			LOG.error("Houve problemas na conex�o com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
}