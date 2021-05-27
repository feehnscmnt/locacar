package br.com.locacar.view.veiculo;

import br.com.locacar.view.componentes.*;
import br.com.locacar.action.veiculo.*;
import org.apache.logging.log4j.*;
import br.com.locacar.util.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

/**
 * View para realizar a consulta de veículos!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmConsultarVeiculos extends JDialog {
	private static final Logger LOG = LogManager.getLogger(FrmConsultarVeiculos.class.getName());
	public static JButton btnConsultar, btnAlterar, btnExcluir, btnFechar;
	public static JFormattedTextField txtPlaca;
	public static JTable jTabDadosVeiculo;
	private JMenuItem menuAlt, menuExc;
	private JScrollPane barraRoalgem;
	private JProgressBar progresso;
	private JPopupMenu popup;
	private JLabel lblPlaca;
	
	public FrmConsultarVeiculos() { LOG.info("View para consulta de veículos iniciado com sucesso!"); setModal(true); initComponents(); noMove(); }
	
	private void initComponents() {
		setSize(659, 430);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("LOCACAR - Consultar veículos");
		getContentPane().setBackground(new Color(153, 180, 209));
		URL img = new PathFilesUtil().getIconTopo();
		Image icone = Toolkit.getDefaultToolkit().getImage(img);
		setIconImage(icone);
		
		progresso = new BarraProgresso();
		progresso.setBounds(200, 170, 245, 58);
		progresso.setVisible(false);
		
		JPanel dadosVeiculo = new JPanel();
		dadosVeiculo.setLayout(null);
		dadosVeiculo.setBounds(10, 5, 624, 300);
		dadosVeiculo.setBackground(new Color(153, 180, 209));
		dadosVeiculo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DADOS DOS VÍCULOS", 4, Font.BOLD, null, Color.BLACK));
		
		lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(15, 42, 100, 15);
		lblPlaca.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtPlaca = new JFormattedTextField(Masks.mascaraPlaca());
		txtPlaca.setBounds(15, 57, 78, 22);
		txtPlaca.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtPlaca.setForeground(Color.BLUE);
		txtPlaca.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtPlaca.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		btnConsultar = new PersonalizaButtons("imgSearch.png", "imgSearch.png", "Consultar Usuário", "Consultar");
		btnConsultar.setBounds(90, 23, 100, 70);
		btnConsultar.setFocusable(false);
		btnConsultar.addActionListener(new ActionConsultarVeiculo() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Buscando veículo no banco de dados...");
						consultar(txtPlaca, jTabDadosVeiculo, btnAlterar, btnExcluir, getRootPane());
						progresso.setVisible(false);
					}
				}.start();
			}
		});
		
		menuAlt = new JMenuItem("Alterar");
		menuAlt.addActionListener(new ActionAlterarVeiculo() {
			public void actionPerformed(ActionEvent e) {
				alterar(jTabDadosVeiculo, getRootPane());
			}
		});
		
		menuExc = new JMenuItem("Excluir");
		menuExc.addActionListener(new ActionExcluirVeiculo() {
			public void actionPerformed(ActionEvent e) {
				excluir(jTabDadosVeiculo, getRootPane());
			}
		});
		
		popup = new JPopupMenu();
		popup.add(menuAlt);
		popup.add(new JSeparator());
		popup.add(menuExc);
		
		String[] cols = { "CÓDIGO", "SITUAÇÃO", "DATA LOCAÇÃO", "PLACA", "MARCA", "MODELO", "VERSÃO",
		"ANO FAB", "ANO MOD", "COR", "COMBUSTÍVEL", "TRANSMISSÃO",
		"RENAVAM", "PORTAS", "ALARME", "TRAVAS", "SENSOR DE RÉ",
		"BANCOS DE COURO", "FREIOS ABS", "AIRBAGS", "CÂMERA DE RÉ",
		"MULTIMÍDIA", "BANCOS REGULÁVEIS", "VIDROS ELÉTRICOS",
		"DIREÇÃO HIDRÁULICA", "DIREÇÃO ELÉTRICA" };
		final DefaultTableModel model = new DefaultTableModel(null, cols) { public boolean isCellEditable(int line, int cols) { return true; } };		
		jTabDadosVeiculo = new GridView(model, popup, "");
		barraRoalgem = new JScrollPane(jTabDadosVeiculo);
		barraRoalgem.setBounds(15, 100, 595, 185);
		
		btnAlterar = new PersonalizaButtons("imgAlter.png", "imgAlterOver.png", "Alterar Veículo", "Alterar");
		btnAlterar.setBounds(450, 317, 50, 70);
		btnAlterar.addActionListener(new ActionAlterarVeiculo() {
			public void actionPerformed(ActionEvent e) {
				alterar(jTabDadosVeiculo, getRootPane());
			}
		});
		
		btnExcluir = new PersonalizaButtons("imgDelete.png", "imgDeleteOver.png", "Excluir Veículo", "Excluir");
		btnExcluir.setBounds(515, 317, 50, 70);
		btnExcluir.addActionListener(new ActionExcluirVeiculo() {
			public void actionPerformed(ActionEvent e) {
				excluir(jTabDadosVeiculo, getRootPane());
			}
		});
		
		btnFechar = new PersonalizaButtons("imgClose.png", "imgClose.png", "Fechar Janela", "Fechar");
		btnFechar.setBounds(580, 317, 50, 70);
		btnFechar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); } });
		
		add(progresso);
		dadosVeiculo.add(lblPlaca);
		dadosVeiculo.add(txtPlaca);
		dadosVeiculo.add(btnConsultar);
		dadosVeiculo.add(barraRoalgem);
		add(dadosVeiculo);
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