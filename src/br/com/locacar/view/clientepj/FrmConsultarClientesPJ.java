package br.com.locacar.view.clientepj;

import br.com.locacar.view.componentes.*;
import br.com.locacar.action.clientepj.*;
import org.apache.logging.log4j.*;
import br.com.locacar.util.*;
import javax.swing.border.*;
import javax.swing.table.*;
import javax.swing.text.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

/**
 * View para realizar a consulta de clientes pessoa jurídica!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmConsultarClientesPJ extends JDialog {
	private static final Logger LOG = LogManager.getLogger(FrmConsultarClientesPJ.class.getName());
	public static JButton btnConsultarPorCpfCnpj, btnConsultarTodos, btnAlterar, btnExcluir, btnFechar;
	public static JFormattedTextField txtCpfCnpj;
	public static JRadioButton rdbCpf, rdbCnpj;
	public static JTable jTabDadosCliente;
	private JMenuItem menuAlt, menuExc;
	private JScrollPane barraRoalgem;
	private JProgressBar progresso;
	public JLabel lblCliente;
	private JPopupMenu popup;
	private String cpf;
	
	public FrmConsultarClientesPJ() { LOG.info("View para consulta de clientes pessoa física iniciado com sucesso!"); setModal(true); initComponents(); noMove(); }
	
	private void initComponents() {
		setSize(443, 450);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("LOCACAR - Consultar clientes pessoa jurídica");
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
		
		rdbCpf = new JRadioButton("CPF");
		rdbCpf.setBounds(10, 30, 50, 20);
		rdbCpf.setFocusable(false);
		rdbCpf.setSelected(true);
		rdbCpf.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rdbCpf.setBackground(new Color(153, 180, 209));
		rdbCpf.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					txtCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(Masks.mascaraCpf()));
				txtCpfCnpj.requestFocus();
			}
		});
		
		rdbCnpj = new JRadioButton("CNPJ");
		rdbCnpj.setBounds(100, 30, 60, 20);
		rdbCnpj.setFocusable(false);
		rdbCnpj.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rdbCnpj.setBackground(new Color(153, 180, 209));
		rdbCnpj.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED)
					txtCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(Masks.mascaraCnpj()));
				txtCpfCnpj.requestFocus();
			}
		});
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbCpf);
		bg.add(rdbCnpj);
		
		txtCpfCnpj = new JFormattedTextField(Masks.mascaraCpf());
		txtCpfCnpj.setBounds(10, 60, 156, 20);
		txtCpfCnpj.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCpfCnpj.setForeground(Color.BLUE);
		txtCpfCnpj.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCpfCnpj.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		btnConsultarPorCpfCnpj = new PersonalizaButtons("imgSearch.png", "imgSearch.png", "Consultar por CPF/CNPJ", "Consultar por CPF/CNPJ");
		btnConsultarPorCpfCnpj.setBounds(160, 20, 160, 70);
		btnConsultarPorCpfCnpj.setFocusable(false);
		btnConsultarPorCpfCnpj.addActionListener(new ActionConsultarClientePJ() {
			public void actionPerformed(ActionEvent e) {
				cpf = txtCpfCnpj.getText();
				if (validaCpf(rdbCpf, rdbCnpj, cpf, getRootPane())) {
					new Thread() {
						public void run() {
							progresso.setVisible(true);
							progresso.setString("Buscando cliente por CNPJ...");
							consultarPorCpfCnpj(rdbCpf, rdbCnpj, txtCpfCnpj, jTabDadosCliente, btnAlterar, btnExcluir, getRootPane());
							progresso.setVisible(false);
						}
					}.start();
				}
			}
		});
		
		btnConsultarTodos = new PersonalizaButtons("imgSearch.png", "imgSearch.png", "Consultar todos", "Consultar todos");
		btnConsultarTodos.setBounds(310, 20, 100, 70);
		btnConsultarTodos.setFocusable(false);
		btnConsultarTodos.addActionListener(new ActionConsultarClientePJ() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Buscando todos os clientes...");
						consultarTodos(txtCpfCnpj, jTabDadosCliente, btnAlterar, btnExcluir, getRootPane());
						progresso.setVisible(false);
					}
				}.start();
			}
		});
		
		menuAlt = new JMenuItem("Alterar");
		menuAlt.addActionListener(new ActionAlterarClientePJ() {
			public void actionPerformed(ActionEvent e) {
				alterar(jTabDadosCliente, getRootPane());
			}
		});
		
		menuExc = new JMenuItem("Excluir");
		menuExc.addActionListener(new ActionExcluirClientePJ() {
			public void actionPerformed(ActionEvent e) {
				excluir(jTabDadosCliente, getRootPane());
			}
		});
		
		popup = new JPopupMenu();
		popup.add(menuAlt);
		popup.add(new JSeparator());
		popup.add(menuExc);
		
		String[] cols = { "CÓDIGO", "NOME", "RAZÃO SOCIAL", "CPF/CNPJ", "CONTATO", "INSCRIÇÃO ESTADUAL", "ENDEREÇO", "CEP", "CIDADE", "UF", "BAIRRO", "COMPLEMENTO", "E-MAIL", "NOME DO MOTORISTA", "NÚMERO CNH", "CATEGORIA CNH" };
		final DefaultTableModel model = new DefaultTableModel(null, cols) { public boolean isCellEditable(int line, int cols) { return true; } };
		jTabDadosCliente = new GridView(model, popup, "");
		barraRoalgem = new JScrollPane(jTabDadosCliente);
		barraRoalgem.setBounds(9, 106, 410, 223);
		
		btnAlterar = new PersonalizaButtons("imgAlter.png", "imgAlterOver.png", "Alterar Usuário", "Alterar");
		btnAlterar.setBounds(235, 342, 50, 70);
		btnAlterar.addActionListener(new ActionAlterarClientePJ() {
			public void actionPerformed(ActionEvent e) {
				alterar(jTabDadosCliente, getRootPane());
			}
		});
		
		btnExcluir = new PersonalizaButtons("imgDelete.png", "imgDeleteOver.png", "Excluir Usuário", "Excluir");
		btnExcluir.setBounds(300, 342, 50, 70);
		btnExcluir.addActionListener(new ActionExcluirClientePJ() {
			public void actionPerformed(ActionEvent e) {
				excluir(jTabDadosCliente, getRootPane());
			}
		});
		
		btnFechar = new PersonalizaButtons("imgClose.png", "imgClose.png", "Fechar Janela", "Fechar");
		btnFechar.setBounds(365, 342, 50, 70);
		btnFechar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); } });
		
		add(progresso);
		dadosCliente.add(rdbCpf);
		dadosCliente.add(rdbCnpj);
		dadosCliente.add(txtCpfCnpj);
		dadosCliente.add(btnConsultarPorCpfCnpj);
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