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
 * Classe responsável pela autenticação dos usuários!
 * @author Felipe Nascimento
 */
public abstract class ActionAutenticar implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionAutenticar.class.getName());
	private UsuariosModel usuarioModel;
	private FrmPrincipal princ;
	private Usuarios usuarios;
	private Splash splash;
	
	public void autenticar(JFrame frmLogin, JPanel panel, String usuario, Object perfil, String senha, JRootPane rootPane) {
		new Thread() {
			public void run() {
				LOG.info("Realizando a autenticação do usuário...");
				frmLogin.setGlassPane(panel);
				panel.setVisible(true);
				splash = new Splash("Autenticando.. Aguarde!");
				splash.setVisible(true);
				
				usuarioModel = new UsuariosModel();
				usuarios = new DAOUsuarios();
				usuarioModel.setNome(usuario);
				ResultSet rs = usuarios.buscarParaAutenticar(usuarioModel);
				
				if (rs != null) {
					try {
						if (rs.next()) {
							if (rs.getString("SENHA").equals(senha)) {
								if (rs.getString("SITUACAO").equals("INATIVO")) {
									LOG.info("Falha na autenticação, usuário inativo no banco de dados!");
									splash.setVisible(false);
									panel.setVisible(false);
									FrmLogin.limpaCampos();
									Modal.mensagem(new MensagensModel(Bundle.getString("userInativo", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
								} else if (rs.getString("PERFIL").equals(perfil)) {
									if (perfil.equals("ADMINISTRADOR")) {
										LOG.info("Autenticação realizada com sucesso!");
										frmLogin.dispose();
										princ = new FrmPrincipal();
										princ.setVisible(true);
										splash.setVisible(false);
										panel.setVisible(false);
										FrmLogin.limpaCampos();
									} else if (perfil.equals("BÁSICO")) {
										LOG.info("Autenticação realizada com sucesso!");
										frmLogin.dispose();
										princ = new FrmPrincipal();
										FrmPrincipal.btnNovoUsuario.setEnabled(false);
										FrmPrincipal.btnPesquisarUsuario.setEnabled(false);
										princ.setVisible(true);
										splash.setVisible(false);
										panel.setVisible(false);
										FrmLogin.limpaCampos();
									}
								} else if (perfil.equals("SELECIONE..")) {
									LOG.info("Falha na autenticação, perfil do usuário não selecionado!");
									splash.setVisible(false);
									panel.setVisible(false);
									FrmLogin.limpaCampos();
									Modal.mensagem(new MensagensModel(Bundle.getString("userSelecionarPerfil", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
								} else if (rs.getString("STATUS").equals(String.valueOf(0))) {
									LOG.info("Falha na autenticação, usuário excluído no banco de dados!");
									splash.setVisible(false);
									panel.setVisible(false);
									FrmLogin.limpaCampos();
									Modal.mensagem(new MensagensModel(Bundle.getString("userExcluidoBase"), nivelMensagens.ERRO), rootPane);
								} else if (perfil != rs.getString("PERFIL")) {
									LOG.info("Falha na autenticação, perfil selecionado incompatível com o usuário!");
									splash.setVisible(false);
									panel.setVisible(false);
									FrmLogin.limpaCampos();
									Modal.mensagem(new MensagensModel(Bundle.getString("userPerfilIncorreto", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
								}
							} else {
								LOG.info("Falha na autenticação, senha incorreta!");
								splash.setVisible(false);
								panel.setVisible(false);
								FrmLogin.limpaCampos();
								Modal.mensagem(new MensagensModel(Bundle.getString("userSenhaIncorreta", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
							}
						} else if (usuario.equals("") && senha.equals("")) {
							LOG.info("Falha na autenticação, dados do usuário não informados!");
							splash.setVisible(false);
							panel.setVisible(false);
							FrmLogin.limpaCampos();
							Modal.mensagem(new MensagensModel(Bundle.getString("userInformarAcessos", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
						} else {
							LOG.info("Falha na autenticação, usuário não cadastrado no banco de dados!");
							splash.setVisible(false);
							panel.setVisible(false);
							FrmLogin.limpaCampos();
							Modal.mensagem(new MensagensModel(Bundle.getString("userNaoCadastrado", usuario, perfil, senha), nivelMensagens.ERRO), rootPane);
						}
					} catch(SQLException e) {
						LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
						JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
						splash.setVisible(false);
						panel.setVisible(false);
						FrmLogin.limpaCampos();
					}
				} else {
					splash.setVisible(false);
					panel.setVisible(false);
					FrmLogin.limpaCampos();
				}
			}
		}.start();
	}
}