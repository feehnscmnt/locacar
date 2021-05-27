package br.com.locacar.view.usuario;

import br.com.locacar.action.componentes.*;
import br.com.locacar.view.componentes.*;
import br.com.locacar.action.usuario.*;
import br.com.locacar.view.opcao.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

/**
 * View de cadastro e alteração de usuários!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmUsuarios extends JDialog {
	private static final Logger LOG = LogManager.getLogger(FrmUsuarios.class.getName());
	public static JLabel lblUsuario, lblSenha, lblConfSenha, lblPerfil, lblSituacao, lblAux, lblCodigo;
	private String codigo, usuario, senha, confSenha, perfil, situacao;
	public static JComboBox<Object> jcbPerfilUsuario, jcbSituacao;
	public static JPasswordField txtSenha, txtConfSenha;
	public static JTextField txtUsuario;
	public JButton btnSalvar, btnFechar;
	private JProgressBar progresso;
	
	public FrmUsuarios() { LOG.info("View de cadastros e alterações dos usuários iniciado com sucesso!"); setModal(true); initComponents(); noMove(); }
	
	private void initComponents() {
		setLayout(null);
		setSize(413, 286);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("LOCACAR - Cadastrar novo usuário");
		getContentPane().setBackground(new Color(153, 180, 209));
		URL img = new PathFilesUtil().getIconTopo();
		Image icone = Toolkit.getDefaultToolkit().getImage(img);
		setIconImage(icone);
		
		progresso = new BarraProgresso();
		progresso.setBounds(80, 90, 245, 58);
		progresso.setVisible(false);
		
		JPanel dadosUsuario = new JPanel();
		dadosUsuario.setLayout(null);
		dadosUsuario.setBounds(7, 5, 385, 160);
		dadosUsuario.setBackground(new Color(153, 180, 209));
		dadosUsuario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DADOS DO USUÁRIO", 4, Font.BOLD, null, Color.BLACK));
		
		lblUsuario = new JLabel("Nome");
		lblUsuario.setBounds(10, 12, 80, 50);
		lblUsuario.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtUsuario = new JTextFieldHint(txtUsuario, "", "NOME DO USUÁRIO");
		txtUsuario.setBounds(10, 44, 180, 20);
		txtUsuario.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtUsuario.setForeground(Color.BLUE);
		txtUsuario.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtUsuario.setDocument(new ActionCapslock());
		
		lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 48, 80, 50);
		lblSenha.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtSenha = new JPasswordFieldHint(txtSenha, "imgPadlockPass.png", "SENHA");
		txtSenha.setBounds(10, 80, 180, 20);
        txtSenha.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtSenha.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtSenha.setForeground(Color.BLUE);
		
		lblConfSenha = new JLabel("Confirma Senha");
		lblConfSenha.setBounds(10, 84, 80, 50);
		lblConfSenha.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtConfSenha = new JPasswordFieldHint(txtConfSenha, "imgPadlockPass.png", "CONFIRMA SENHA");
		txtConfSenha.setBounds(10, 117, 180, 20);
		txtConfSenha.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtConfSenha.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtConfSenha.setForeground(Color.BLUE);
		
		lblPerfil = new JLabel("Perfil de Acesso");
		lblPerfil.setBounds(210, 12, 80, 50);
		lblPerfil.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbPerfilUsuario = new JComboBox<Object>();
		jcbPerfilUsuario.setBounds(210, 44, 161, 21);
		jcbPerfilUsuario.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbPerfilUsuario.setForeground(Color.BLUE);
		jcbPerfilUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbPerfilUsuario.setFocusable(false);
		jcbPerfilUsuario.addItem("SELECIONE..");
		
		lblSituacao = new JLabel("Situação");
		lblSituacao.setBounds(210, 48, 80, 50);
		lblSituacao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbSituacao = new JComboBox<Object>();
		jcbSituacao.setBounds(210, 80, 161, 21);
		jcbSituacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbSituacao.setForeground(Color.BLUE);
		jcbSituacao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbSituacao.setFocusable(false);
		jcbSituacao.addItem("SELECIONE..");
		jcbSituacao.addItem("ATIVO");
		jcbSituacao.addItem("INATIVO");
		
		lblAux = new JLabel("");
		lblAux.setBounds(210, 102, 200, 50);
		lblAux.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		
		lblCodigo = new JLabel("");
		lblCodigo.setBounds(317, 102, 200, 50);
		lblCodigo.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		
		btnSalvar = new PersonalizaButtons("imgToSave.png", "imgToSaveOver.png", "Salvar", "Salvar");
		btnSalvar.setBounds(265, 170, 50, 70);
		btnSalvar.addActionListener(new ActionSalvarAlterarUsuario() {
			public void actionPerformed(ActionEvent e) {
				codigo = lblCodigo.getText();
				usuario = txtUsuario.getText();
				senha = String.valueOf(txtSenha.getPassword()).toLowerCase();
				confSenha = String.valueOf(txtConfSenha.getPassword()).toLowerCase();
				perfil = jcbPerfilUsuario.getSelectedItem().toString();
				situacao = jcbSituacao.getSelectedItem().toString();
				if (validarCampos(usuario, senha, confSenha, perfil, situacao, getRootPane()))
					if (codigo.equals("")) {
						new Thread() {
							public void run() {
								progresso.setVisible(true);
								progresso.setString("Salvando usuário no banco de dados...");
								salvar(codigo, usuario, senha, perfil, situacao, getRootPane(), Opcoes.SALVAR);
								progresso.setVisible(false);
							}
						}.start();
					} else {
						new Thread() {
							public void run() {
								progresso.setVisible(true);
								progresso.setString("Alterando usuário no banco de dados...");
								salvar(codigo, usuario, senha, perfil, situacao, getRootPane(), Opcoes.ALTERAR);
								progresso.setVisible(false);
							}
						}.start();
					}
			}
		});
		
		btnFechar = new PersonalizaButtons("imgClose.png", "imgClose.png", "Fechar Janela", "Fechar");
		btnFechar.setBounds(335, 170, 50, 70);
		btnFechar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); } });
		
		add(progresso);
		dadosUsuario.add(lblUsuario);
		dadosUsuario.add(txtUsuario);
		dadosUsuario.add(lblSenha);
		dadosUsuario.add(txtSenha);
		dadosUsuario.add(lblConfSenha);
		dadosUsuario.add(txtConfSenha);
		dadosUsuario.add(lblPerfil);
		dadosUsuario.add(jcbPerfilUsuario);
		dadosUsuario.add(lblSituacao);
		dadosUsuario.add(jcbSituacao);
		dadosUsuario.add(lblAux);
		dadosUsuario.add(lblCodigo);
		add(dadosUsuario);
		add(btnSalvar);
		add(btnFechar);
		addWindowListener(new ActionCarregarDadosUsuarios() {
			public void windowOpened(WindowEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Iniciando... Aguarde!");
						PopularCombos.popularComboPerfilUsers(jcbPerfilUsuario);
						carregaDadosUsuarios(lblCodigo, txtUsuario, txtSenha, jcbPerfilUsuario, jcbSituacao);
						progresso.setVisible(false);
					}
				}.start();
			}
		});
	}
	
	private void noMove() {
		this.addComponentListener(new ComponentAdapter() {
			public void componentMoved(ComponentEvent e) {
				setEnabled(false);
				setEnabled(true);
			}
		});
	}
	
	public static void limparCampos() {
		txtUsuario.setText("");
		txtSenha.setText("");
		txtConfSenha.setText("");
		jcbPerfilUsuario.setSelectedItem("SELECIONE..");
		jcbSituacao.setSelectedItem("SELECIONE..");
		txtUsuario.requestFocus();
	}
}