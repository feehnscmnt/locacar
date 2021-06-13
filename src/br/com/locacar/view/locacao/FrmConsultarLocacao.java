package br.com.locacar.view.locacao;

import br.com.locacar.view.componentes.*;
import br.com.locacar.action.locacao.*;
import org.apache.logging.log4j.*;
import br.com.locacar.util.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

/**
 * View para realizar a consulta de locações!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmConsultarLocacao extends JDialog {
	private static final Logger LOG = LogManager.getLogger(FrmConsultarLocacao.class.getName());
	public static JButton btnConsultar, btnVisualizar, btnDarBaixa, btnFechar;
	public static JTextField txtNumeroLocacao;
	public static JTable jTabDadosLocacao;
	JMenuItem menuView, menuBai;
	JScrollPane barraRoalgem;
	JLabel lblNumeroLocacao;
	JProgressBar progresso;
	JPopupMenu popup;
	
	public FrmConsultarLocacao() {
		initComponents();
		noMove();
	}
	
	private void initComponents() {
		setSize(659, 430);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("LOCACAR - Consultar locações");
		getContentPane().setBackground(new Color(153, 180, 209));
		URL img = new PathFilesUtil().getIconTopo();
		Image icone = Toolkit.getDefaultToolkit().getImage(img);
		setIconImage(icone);
		
		progresso = new BarraProgresso();
		progresso.setBounds(200, 170, 245, 58);
		progresso.setVisible(false);
		
		JPanel dadosLocacao = new JPanel();
		dadosLocacao.setLayout(null);
		dadosLocacao.setBounds(10, 5, 624, 300);
		dadosLocacao.setBackground(new Color(153, 180, 209));
		dadosLocacao.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DADOS DAS LOCAÇÕES", 4, Font.BOLD, null, Color.BLACK));
		
		lblNumeroLocacao = new JLabel("Número da Locação");
		lblNumeroLocacao.setBounds(15, 42, 100, 15);
		lblNumeroLocacao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtNumeroLocacao = new JTextFieldHint(txtNumeroLocacao, "", "NÚMERO");
		txtNumeroLocacao.setBounds(15, 57, 94, 22);
		txtNumeroLocacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtNumeroLocacao.setForeground(Color.BLUE);
		txtNumeroLocacao.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		btnConsultar = new PersonalizaButtons("imgSearch.png", "imgSearch.png", "Consultar Usuário", "Consultar");
		btnConsultar.setBounds(97, 23, 100, 70);
		btnConsultar.setFocusable(false);
		btnConsultar.addActionListener(new ActionConsultarLocacao() {
			public void actionPerformed(ActionEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Buscando locação no banco de dados...");
						consultar(txtNumeroLocacao, jTabDadosLocacao, btnVisualizar, btnDarBaixa, rootPane);
						progresso.setVisible(false);
					}
				}.start();
			}
		});
		
		menuView = new JMenuItem("Visualizar");
		menuView.addActionListener(new ActionVisualizarLocacao() {
			public void actionPerformed(ActionEvent e) {
				visualizar(jTabDadosLocacao, getRootPane());
			}
		});
		
		menuBai = new JMenuItem("Dar Baixa");
		menuBai.addActionListener(new ActionBaixaLocacao() {
			public void actionPerformed(ActionEvent e) {
				baixa(jTabDadosLocacao, getRootPane());
			}
		});
		
		popup = new JPopupMenu();
		popup.add(menuView);
		popup.add(new JSeparator());
		popup.add(menuBai);
		
		String[] cols = { "CÓDIGO", "SITUAÇÃO", "NÚMERO", "CPF/CNPJ", "NOME", "ENDEREÇO",
		"CIDADE", "BAIRRO", "E-MAIL", "PLACA", "MARCA", "MODELO", "COMBUSTÍVEL",
		"COR", "ANO", "RENAVAM", "KILOMETRAGEM", "DATA LOCAÇÃO", "HORA LOCAÇÃO",
		"DATA RETORNO", "HORA RETORNO", "LOCAL ENTREGA", "OBSERVAÇÕES", "VALOR DIA",
		"QTDE DIAS", "VALOR TAXA", "VALOR PROTEÇÃO", "TOTAL" };
		final DefaultTableModel model = new DefaultTableModel(null, cols) { public boolean isCellEditable(int line, int cols) { return true; } };		
		jTabDadosLocacao = new GridView(model, popup, "");
		barraRoalgem = new JScrollPane(jTabDadosLocacao);
		barraRoalgem.setBounds(15, 100, 595, 185);
		
		btnVisualizar = new PersonalizaButtons("imgView.png", "imgView.png", "Visualizar locação", "Visualizar");
		btnVisualizar.setBounds(440, 317, 60, 70);
		btnVisualizar.addActionListener(new ActionVisualizarLocacao() {
			public void actionPerformed(ActionEvent e) {
				visualizar(jTabDadosLocacao, getRootPane());
			}
		});
		
		btnDarBaixa = new PersonalizaButtons("imgDarBaixa.png", "imgDarBaixa.png", "Dar Baixa", "Dar Baixa");
		btnDarBaixa.setBounds(511, 317, 60, 70);
		btnDarBaixa.addActionListener(new ActionBaixaLocacao() {
			public void actionPerformed(ActionEvent e) {
				baixa(jTabDadosLocacao, getRootPane());
			}
		});
		
		btnFechar = new PersonalizaButtons("imgClose.png", "imgClose.png", "Fechar Janela", "Fechar");
		btnFechar.setBounds(580, 317, 50, 70);
		btnFechar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); } });
		
		add(progresso);
		dadosLocacao.add(lblNumeroLocacao);
		dadosLocacao.add(txtNumeroLocacao);
		dadosLocacao.add(btnConsultar);
		dadosLocacao.add(barraRoalgem);
		add(dadosLocacao);
		add(btnVisualizar);
		add(btnDarBaixa);
		add(btnFechar);
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				btnVisualizar.setEnabled(false);
				btnDarBaixa.setEnabled(false);
			}
		});
		
		LOG.info("View para consulta de locações iniciado com sucesso!");
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