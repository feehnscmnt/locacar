package br.com.locacar.view.usuario;

import br.com.locacar.action.componentes.*;
import br.com.locacar.view.componentes.*;
import br.com.locacar.action.usuario.*;
import org.apache.logging.log4j.*;
import br.com.locacar.util.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

/**
 * View para realizar a consulta de usuários!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmConsultarUsuarios extends JDialog {
	private static final Logger LOG = LogManager.getLogger(FrmConsultarUsuarios.class.getName());
	public static JButton btnConsultar, btnAlterar, btnExcluir, btnFechar;
	public static JTable jTabDadosUsuario;
	public static JTextField txtUsuario;
	private JMenuItem menuAlt, menuExc;
	private JScrollPane barraRoalgem;
	private JProgressBar progresso;
	private JLabel lblUsuario;
	private JPopupMenu popup;
	
	public FrmConsultarUsuarios() {
		initComponents();
		noMove();
	}
	
	private void initComponents() {
		setSize(509, 430);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("LOCACAR - Consultar usuários");
		getContentPane().setBackground(new Color(153, 180, 209));
		URL img = new PathFilesUtil().getIconTopo();
		Image icone = Toolkit.getDefaultToolkit().getImage(img);
		setIconImage(icone);
		
		progresso = new BarraProgresso();
		progresso.setBounds(120, 185, 245, 58);
		progresso.setVisible(false);
		
		JPanel dadosUsuario = new JPanel();
		dadosUsuario.setLayout(null);
		dadosUsuario.setBounds(10, 5, 473, 300);
		dadosUsuario.setBackground(new Color(153, 180, 209));
		dadosUsuario.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DADOS DO USUÁRIO", 4, Font.BOLD, null, Color.BLACK));
		
		lblUsuario = new JLabel("Nome do Usuário");
		lblUsuario.setBounds(15, 42, 100, 15);
		lblUsuario.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtUsuario = new JTextFieldHint(txtUsuario, "", "NOME DO USUÁRIO");
		txtUsuario.setBounds(15, 57, 181, 20);
		txtUsuario.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtUsuario.setForeground(Color.BLUE);
		txtUsuario.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtUsuario.setDocument(new ActionCapslock());
		
		btnConsultar = new PersonalizaButtons("imgSearch.png", "imgSearch.png", "Consultar Usuário", "Consultar");
		btnConsultar.setBounds(190, 23, 100, 70);
		btnConsultar.setFocusable(false);
		btnConsultar.addActionListener(new ActionConsultarUsuario() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Buscando usuário no banco de dados...");
						consultar(txtUsuario, jTabDadosUsuario, btnAlterar, btnExcluir, getRootPane());
						progresso.setVisible(false);
					}
				}.start();
			}
		});
		
		menuAlt = new JMenuItem("Alterar");
		menuAlt.addActionListener(new ActionAlterarUsuario() {
			public void actionPerformed(ActionEvent e) {
				alterar(jTabDadosUsuario, getRootPane());
			}
		});
		
		menuExc = new JMenuItem("Excluir");
		menuExc.addActionListener(new ActionExcluirUsuario() {
			public void actionPerformed(ActionEvent e) {
				excluir(jTabDadosUsuario, getRootPane());
			}
		});
		
		popup = new JPopupMenu();
		popup.add(menuAlt);
		popup.add(new JSeparator());
		popup.add(menuExc);
		
		String[] cols = { "CÓDIGO", "NOME", "SENHA", "PERFIL", "SITUAÇÃO" };
		final DefaultTableModel model = new DefaultTableModel(null, cols) { public boolean isCellEditable(int line, int cols) { return true; } };		
		jTabDadosUsuario = new GridView(model, popup, "");
		jTabDadosUsuario.getColumnModel().getColumn(0).setPreferredWidth(100);
		jTabDadosUsuario.getColumnModel().getColumn(1).setPreferredWidth(100);
		jTabDadosUsuario.getColumnModel().getColumn(2).setPreferredWidth(100);
		jTabDadosUsuario.getColumnModel().getColumn(3).setPreferredWidth(100);
		jTabDadosUsuario.getColumnModel().getColumn(4).setPreferredWidth(100);
		barraRoalgem = new JScrollPane(jTabDadosUsuario);
		barraRoalgem.setBounds(15, 100, 444, 185);
		
		btnAlterar = new PersonalizaButtons("imgAlter.png", "imgAlterOver.png", "Alterar Usuário", "Alterar");
		btnAlterar.setBounds(300, 317, 50, 70);
		btnAlterar.addActionListener(new ActionAlterarUsuario() {
			public void actionPerformed(ActionEvent e) {
				alterar(jTabDadosUsuario, getRootPane());
			}
		});
		
		btnExcluir = new PersonalizaButtons("imgDelete.png", "imgDeleteOver.png", "Excluir Usuário", "Excluir");
		btnExcluir.setBounds(365, 317, 50, 70);
		btnExcluir.addActionListener(new ActionExcluirUsuario() {
			public void actionPerformed(ActionEvent e) {
				excluir(jTabDadosUsuario, getRootPane());
			}
		});
		
		btnFechar = new PersonalizaButtons("imgClose.png", "imgClose.png", "Fechar Janela", "Fechar");
		btnFechar.setBounds(430, 317, 50, 70);
		btnFechar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); } });
		
		add(progresso);
		dadosUsuario.add(lblUsuario);
		dadosUsuario.add(txtUsuario);
		dadosUsuario.add(btnConsultar);
		dadosUsuario.add(barraRoalgem);
		add(dadosUsuario);
		add(btnAlterar);
		add(btnExcluir);
		add(btnFechar);
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				btnAlterar.setEnabled(false);
				btnExcluir.setEnabled(false);
			}
		});
		
		LOG.info("View para consulta de usuários iniciado com sucesso!");
	}
	
	private void noMove() {
		this.addComponentListener(new ComponentAdapter() {
			public void componentMoved(ComponentEvent e) {
				setEnabled(false);
				setEnabled(true);
			}
		});
	}
}