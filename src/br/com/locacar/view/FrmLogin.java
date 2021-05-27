package br.com.locacar.view;

import br.com.locacar.action.componentes.*;
import br.com.locacar.view.componentes.*;
import org.apache.logging.log4j.*;
import br.com.locacar.action.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

/**
 * View de autenticação de usuários!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmLogin extends JFrame {
	private static final Logger LOG = LogManager.getLogger(FrmLogin.class.getName());
	private JLabel lblUsuario, lblPerfil, lblSenha, lblImgLogin;
	public static JComboBox<Object> jcbPerfilUsuario;
	public static JPasswordField txtSenha;
	private JCheckBox chBoxVisualizaSenha;
	public static String USUARIO_LOGADO;
	private JButton btnLogar, btnFechar;
	public static JTextField txtUsuario;
	private JProgressBar progresso;
	private JPanel pcbImagem;
	private Object perfil;
	private String senha;
	
	public FrmLogin() { LOG.info("View de autenticação iniciado com sucesso!"); initComponents(); noMove(); }
	
	private void initComponents() {
		getContentPane().setLayout(null);
		setTitle("LOCACAR - Autenticação");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(450, 240);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(153, 180, 209));
		URL img = getClass().getClassLoader().getResource("br/com/locacar/files/images/imgLogoTopoForms.png");
		Image icone = Toolkit.getDefaultToolkit().getImage(img);
		setIconImage(icone);
		
		progresso = new BarraProgresso();
		progresso.setBounds(100, 44, 245, 58);
		progresso.setVisible(false);
		
		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(10, 25, 60, 10);
		lblUsuario.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 12));
		
		txtUsuario = new JTextFieldHint(txtUsuario, "imgUserTxt.png", "USUÁRIO");
		txtUsuario.setBounds(67, 21, 205, 20);
        txtUsuario.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtUsuario.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtUsuario.setForeground(Color.BLUE);
		txtUsuario.setDocument(new ActionCapslock());
		
		lblPerfil = new JLabel("Perfil:");
		lblPerfil.setBounds(10, 66, 60, 10);
		lblPerfil.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 12));
		
		jcbPerfilUsuario = new JComboBox<Object>();
		jcbPerfilUsuario.setBounds(67, 61, 205, 20);
		jcbPerfilUsuario.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbPerfilUsuario.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbPerfilUsuario.setBackground(Color.WHITE);
		jcbPerfilUsuario.setForeground(Color.BLUE);
		jcbPerfilUsuario.addItem("SELECIONE..");
		
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 105, 60, 10);
		lblSenha.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 12));
		
		txtSenha = new JPasswordFieldHint(txtSenha, "imgPadlockPass.png", "SENHA");
		txtSenha.setBounds(67, 102, 205, 20);
        txtSenha.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtSenha.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtSenha.setForeground(Color.BLUE);
		
		btnLogar = new PersonalizaButtons("imgLog.png", "imgLogOver.png", "Efetuar Autenticação", "");
		btnLogar.setBounds(85, 130, 50, 70);
		btnLogar.addActionListener(new ActionAutenticar() {
			public void actionPerformed(ActionEvent e) {
				USUARIO_LOGADO = txtUsuario.getText();
				perfil = jcbPerfilUsuario.getSelectedItem().toString();
				senha = String.valueOf(txtSenha.getPassword()).toLowerCase();
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Autenticando... Aguarde!");
						autenticar(FrmLogin.this, USUARIO_LOGADO, perfil, senha, getRootPane());
						progresso.setVisible(false);
					}
				}.start();
			}
		});
		
		btnFechar = new PersonalizaButtons("imgClose.png", "imgClose.png", "Fechar Janela", "");
		btnFechar.setBounds(180, 130, 50, 70);
		btnFechar.addActionListener(new ActionFinalizar() { public void actionPerformed(ActionEvent e) { finalizar(txtUsuario); } });
		
		chBoxVisualizaSenha = new JCheckBox("Visualizar Senha");
		chBoxVisualizaSenha.setBounds(285, 170, 143, 20);
		chBoxVisualizaSenha.setBackground(new Color(153, 180, 209));
		chBoxVisualizaSenha.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 15));
		chBoxVisualizaSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
		chBoxVisualizaSenha.addActionListener(new ActionVisualizarSenha() { public void actionPerformed(ActionEvent e) { visualizaSenha(chBoxVisualizaSenha, txtSenha); } });
		
		pcbImagem = new JPanel();
		pcbImagem.setLayout(new BorderLayout());
		pcbImagem.setBounds(290, 20, 130, 136);
		lblImgLogin = new JLabel();
		Toolkit tkLogin = Toolkit.getDefaultToolkit();
		Image imgLogin = tkLogin.getImage(PathFilesUtil.getImage() + "/images/imgPadlock.png");
		Image imgLoginAj = imgLogin.getScaledInstance(128, 134, 1);
		ImageIcon icnLogin = new ImageIcon(imgLoginAj);
		lblImgLogin.setIcon(icnLogin);
		pcbImagem.add(lblImgLogin);
		
		add(progresso);
		add(lblUsuario);
		add(txtUsuario);
		add(lblPerfil);
		add(jcbPerfilUsuario);
		add(lblSenha);
		add(txtSenha);
		add(btnLogar);
		add(btnFechar);
		add(chBoxVisualizaSenha);
		getRootPane().setDefaultButton(btnLogar);
		add(pcbImagem);
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				PopularCombos.popularComboPerfilUsers(jcbPerfilUsuario);
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
	
	public static void limpaCampos() {
		txtUsuario.setText("");
		jcbPerfilUsuario.setSelectedItem("SELECIONE..");
        txtSenha.setText("");
        txtUsuario.requestFocus();
	}
}