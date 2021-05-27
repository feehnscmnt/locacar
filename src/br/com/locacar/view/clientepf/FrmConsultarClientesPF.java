package br.com.locacar.view.clientepf;

import br.com.locacar.view.componentes.*;
import br.com.locacar.action.clientepf.*;
import org.apache.logging.log4j.*;
import br.com.locacar.util.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

/**
 * View para realizar a consulta de clientes pessoa física!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmConsultarClientesPF extends JDialog {
	private static final Logger LOG = LogManager.getLogger(FrmConsultarClientesPF.class.getName());
	public static JButton btnConsultarPorCpf, btnConsultarTodos, btnAlterar, btnExcluir, btnFechar;
	public static JFormattedTextField txtCpf;
	public static JTable jTabDadosCliente;
	private JMenuItem menuAlt, menuExc;
	private JScrollPane barraRoalgem;
	private JProgressBar progresso;
	private JLabel lblCliente;
	private JPopupMenu popup;
	private String cpf;
	
	public FrmConsultarClientesPF() { LOG.info("View para consulta de clientes pessoa física iniciado com sucesso!"); setModal(true); initComponents(); noMove(); }
	
	private void initComponents() {
		setSize(443, 450);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("LOCACAR - Consultar clientes pessoa física");
		getContentPane().setBackground(new Color(153, 180, 209));
		URL img = new PathFilesUtil().getIconTopo();
		Image icone = Toolkit.getDefaultToolkit().getImage(img);
		setIconImage(icone);
		
		progresso = new BarraProgresso();
		progresso.setBounds(90, 185, 245, 58);
		progresso.setVisible(false);
		
		JPanel dadosCliente = new JPanel();
		dadosCliente.setLayout(null);
		dadosCliente.setBounds(5, 5, 418, 100);
		dadosCliente.setBackground(new Color(153, 180, 209));
		dadosCliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DADOS DO CLIENTE", 4, Font.BOLD, null, Color.BLACK));
		
		lblCliente = new JLabel("CPF");
		lblCliente.setBounds(10, 50, 47, 10);
		lblCliente.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtCpf = new JFormattedTextField(Masks.mascaraCpf());
		txtCpf.setBounds(42, 45, 122, 20);
		txtCpf.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCpf.setForeground(Color.BLUE);
		txtCpf.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCpf.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		btnConsultarPorCpf = new PersonalizaButtons("imgSearch.png", "imgSearch.png", "Consultar por CPF", "Consultar por CPF");
		btnConsultarPorCpf.setBounds(180, 20, 105, 70);
		btnConsultarPorCpf.setFocusable(false);
		btnConsultarPorCpf.addActionListener(new ActionConsultarClientePF() {
			public void actionPerformed(ActionEvent e) {
				cpf = txtCpf.getText();
				if (validaCpf(cpf, getRootPane())) {
					new Thread() {
						public void run() {
							progresso.setVisible(true);
							progresso.setString("Buscando cliente por CPF...");
							consultarPorCpf(txtCpf, jTabDadosCliente, btnAlterar, btnExcluir, getRootPane());
							progresso.setVisible(false);
						}
					}.start();
				}
			}
		});
		
		btnConsultarTodos = new PersonalizaButtons("imgSearch.png", "imgSearch.png", "Consultar todos", "Consultar todos");
		btnConsultarTodos.setBounds(300, 20, 100, 70);
		btnConsultarTodos.setFocusable(false);
		btnConsultarTodos.addActionListener(new ActionConsultarClientePF() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Buscando todos os clientes...");
						consultarTodos(txtCpf, jTabDadosCliente, btnAlterar, btnExcluir, getRootPane());
						progresso.setVisible(false);
					}
				}.start();
			}
		});
		
		menuAlt = new JMenuItem("Alterar");
		menuAlt.addActionListener(new ActionAlterarClientePF() {
			public void actionPerformed(ActionEvent e) {
				alterar(jTabDadosCliente, getRootPane());
			}
		});
		
		menuExc = new JMenuItem("Excluir");
		menuExc.addActionListener(new ActionExcluirClientePF() {
			public void actionPerformed(ActionEvent e) {
				excluir(jTabDadosCliente, getRootPane());
			}
		});
		
		popup = new JPopupMenu();
		popup.add(menuAlt);
		popup.add(new JSeparator());
		popup.add(menuExc);
		
		String[] cols = { "CÓDIGO", "NOME", "CPF", "RG", "CONTATO", "DATA DE NASCIMENTO", "CATEGORIA CNH", "NÚMERO CNH", "E-MAIL", "ENDEREÇO", "CEP", "CIDADE", "UF", "BAIRRO", "COMPLEMENTO" };
		final DefaultTableModel model = new DefaultTableModel(null, cols) { public boolean isCellEditable(int line, int cols) { return true; } };
		jTabDadosCliente = new GridView(model, popup, "");
		barraRoalgem = new JScrollPane(jTabDadosCliente);
		barraRoalgem.setBounds(9, 106, 410, 223);
		
		btnAlterar = new PersonalizaButtons("imgAlter.png", "imgAlterOver.png", "Alterar Usuário", "Alterar");
		btnAlterar.setBounds(235, 342, 50, 70);
		btnAlterar.addActionListener(new ActionAlterarClientePF() {
			public void actionPerformed(ActionEvent e) {
				alterar(jTabDadosCliente, getRootPane());
			}
		});
		
		btnExcluir = new PersonalizaButtons("imgDelete.png", "imgDeleteOver.png", "Excluir Usuário", "Excluir");
		btnExcluir.setBounds(300, 342, 50, 70);
		btnExcluir.addActionListener(new ActionExcluirClientePF() {
			public void actionPerformed(ActionEvent e) {
				excluir(jTabDadosCliente, getRootPane());
			}
		});
		
		btnFechar = new PersonalizaButtons("imgClose.png", "imgClose.png", "Fechar Janela", "Fechar");
		btnFechar.setBounds(365, 342, 50, 70);
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		add(progresso);
		dadosCliente.add(lblCliente);
		dadosCliente.add(txtCpf);
		dadosCliente.add(btnConsultarPorCpf);
		dadosCliente.add(btnConsultarTodos);
		add(dadosCliente);
		add(barraRoalgem);
		add(btnAlterar);
		add(btnExcluir);
		add(btnFechar);
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				btnAlterar.setEnabled(false);
				btnExcluir.setEnabled(false);
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
}