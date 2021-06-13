package br.com.locacar.view.veiculo;

import br.com.locacar.action.componentes.*;
import br.com.locacar.view.componentes.*;
import br.com.locacar.action.veiculo.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.enums.*;
import br.com.locacar.util.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

/**
 * View para cadastro e alteração de veiculos!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmVeiculos extends JDialog {
	private static final Logger LOG = LogManager.getLogger(FrmVeiculos.class.getName());
	public static JComboBox<Object> jcbMarca, jcbModelo;
	public static JFormattedTextField txtPlaca;
	public static JTextField txtVersao, txtAnoFabricacao, txtAnoModelo, txtCor, txtCombustivel, txtQtdePortas, txtTransmissao, txtRenavam, txtSituacao, txtDataLocacao;
	public static JLabel lblPlaca, lblMarca, lblModelo, lblVersao, lblAnoFabricacao, lblAnoModelo, lblCor, lblCombustivel, lblTransmissao, lblRenavam, lblQtdePortas, lblSituacao, lblDataLocacao, lblAux, lblCodigo;
	public static JCheckBox checkAlarme, checkTravas, checkSensorRe, checkBancosCouro, checkFreiosABS, checkAirbags, checkCameraRe, checkMultimidia, checkBancosRegulaveis, checkVidrosEletricos, checkDirHidraulica, checkDirEletrica;
	public JButton btnSalvar, btnFechar;
	private String placa, marca, modelo, versao, anoFab, anoMod, cor, combustivel, transmissao, renavam, portas, alarme, travas, sensorRe, bancosCouro, freiosAbs, airbags, cameraRe, multimidia, bancosRegulaveis, vidrosEletricos, direcaoHidraulica, direcaoEletrica, situacao, codigo;
	private JProgressBar progresso;
	
	public FrmVeiculos() {
		initComponents();
		noMove();
	}
	
	private void initComponents() {
		getContentPane().setLayout(null);
		setSize(715, 356);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("LOCACAR - Cadastrar novo veículo");
		getContentPane().setBackground(new Color(153, 180, 209));
		URL img = new PathFilesUtil().getIconTopo();
		Image icone = Toolkit.getDefaultToolkit().getImage(img);
		setIconImage(icone);
		
		progresso = new BarraProgresso();
		progresso.setBounds(229, 140, 245, 58);
		progresso.setVisible(false);
		
		JPanel dadosVeiculo = new JPanel();
		dadosVeiculo.setLayout(null);
		dadosVeiculo.setBounds(6, 1, 687, 235);
		dadosVeiculo.setBackground(new Color(153, 180, 209));
		dadosVeiculo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DADOS DO VEÍCULO", 4, Font.BOLD, null, Color.BLACK));
		
		JPanel itens = new JPanel();
		itens.setLayout(null);
		itens.setBounds(8, 110, 417, 115);
		itens.setBackground(new Color(153, 180, 209));
		itens.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "ITENS", 4, Font.BOLD, null, Color.BLACK));
		
		lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(10, 5, 80, 50);
		lblPlaca.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtPlaca = new JFormattedTextField(Masks.mascaraPlaca());
		txtPlaca.setBounds(10, 40, 78, 22);
		txtPlaca.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtPlaca.setForeground(Color.BLUE);
		txtPlaca.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtPlaca.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(95, 5, 80, 50);
		lblMarca.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbMarca = new JComboBox<Object>();
		jcbMarca.setBounds(95, 40, 167, 21);
		jcbMarca.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbMarca.setBackground(Color.WHITE);
		jcbMarca.setForeground(Color.BLUE);
		jcbMarca.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbMarca.addItem("SELECIONE..");
		jcbMarca.addFocusListener(new ActionListarModelosPorMarca() {
			public void focusLost(FocusEvent e) {
				listarModeloPorMarca(jcbMarca, jcbModelo);
			}
		});
		
		lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(271, 5, 80, 50);
		lblModelo.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbModelo = new JComboBox<Object>();
		jcbModelo.setBounds(271, 40, 167, 21);
		jcbModelo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbModelo.setBackground(Color.WHITE);
		jcbModelo.setForeground(Color.BLUE);
		jcbModelo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbModelo.addItem("SELECIONE..");
		
		lblVersao = new JLabel("Versão");
		lblVersao.setBounds(448, 5, 80, 50);
		lblVersao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtVersao = new JTextFieldHint(txtVersao, "", "VERSÃO");
		txtVersao.setBounds(448, 40, 102, 22);
		txtVersao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtVersao.setForeground(Color.BLUE);
		txtVersao.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtVersao.setDocument(new ActionCapslock());
		
		lblAnoFabricacao = new JLabel("Ano Fab.");
		lblAnoFabricacao.setBounds(558, 5, 80, 50);
		lblAnoFabricacao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtAnoFabricacao = new JTextFieldHint(txtAnoFabricacao, "", "AF");
		txtAnoFabricacao.setBounds(558, 40, 55, 22);
		txtAnoFabricacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtAnoFabricacao.setForeground(Color.BLUE);
		txtAnoFabricacao.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtAnoFabricacao.setDocument(new ActionLimiteCaracteres());
		
		lblAnoModelo = new JLabel("Ano Mod.");
		lblAnoModelo.setBounds(621, 5, 80, 50);
		lblAnoModelo.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtAnoModelo = new JTextFieldHint(txtAnoModelo, "", "AM");
		txtAnoModelo.setBounds(621, 40, 55, 22);
		txtAnoModelo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtAnoModelo.setForeground(Color.BLUE);
		txtAnoModelo.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtAnoModelo.setDocument(new ActionLimiteCaracteres());
		
		lblCor = new JLabel("Cor");
		lblCor.setBounds(10, 50, 80, 50);
		lblCor.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtCor = new JTextFieldHint(txtCor, "", "COR");
		txtCor.setBounds(10, 84, 78, 22);
		txtCor.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCor.setForeground(Color.BLUE);
		txtCor.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCor.setDocument(new ActionCapslock());
		
		lblCombustivel = new JLabel("Combustível");
		lblCombustivel.setBounds(95, 50, 80, 50);
		lblCombustivel.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtCombustivel = new JTextFieldHint(txtCombustivel, "", "COMBUSTÍVEL");
		txtCombustivel.setBounds(95, 84, 167, 22);
		txtCombustivel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCombustivel.setForeground(Color.BLUE);
		txtCombustivel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCombustivel.setDocument(new ActionCapslock());
		
		lblTransmissao = new JLabel("Transmissão");
		lblTransmissao.setBounds(271, 50, 80, 50);
		lblTransmissao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtTransmissao = new JTextFieldHint(txtTransmissao, "", "TRANSMISSÃO");
		txtTransmissao.setBounds(271, 84, 167, 22);
		txtTransmissao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtTransmissao.setForeground(Color.BLUE);
		txtTransmissao.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtTransmissao.setDocument(new ActionCapslock());
		
		lblRenavam = new JLabel("Renavam");
		lblRenavam.setBounds(448, 50, 80, 50);
		lblRenavam.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtRenavam = new JTextFieldHint(txtRenavam, "", "RENAVAM");
		txtRenavam.setBounds(448, 84, 102, 22);
		txtRenavam.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtRenavam.setForeground(Color.BLUE);
		txtRenavam.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtRenavam.setDocument(new ActionLimiteCaracteres());
		txtRenavam.addKeyListener(new ActionApenasNumeros() {
			public void keyTyped(KeyEvent e) {
				valoresNumericos(e);
			}
		});
		
		lblAux = new JLabel("");
		lblAux.setBounds(10, 220, 200, 50);
		lblAux.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		
		lblCodigo = new JLabel("");
		lblCodigo.setBounds(117, 220, 200, 50);
		lblCodigo.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		
		lblQtdePortas = new JLabel("Portas");
		lblQtdePortas.setBounds(558, 50, 80, 50);
		lblQtdePortas.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtQtdePortas = new JTextFieldHint(txtQtdePortas, "", "PORTAS");
		txtQtdePortas.setBounds(558, 84, 118, 22);
		txtQtdePortas.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtQtdePortas.setForeground(Color.BLUE);
		txtQtdePortas.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtQtdePortas.addKeyListener(new ActionApenasNumeros() {
			public void keyTyped(KeyEvent e) {
				valoresNumericos(e);
			}
		});
		
		checkAlarme = new JCheckBox("Alarme");
		checkAlarme.setBounds(5, 18, 80, 20);
		checkAlarme.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkAlarme.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkAlarme.setBackground(new Color(153, 180, 209));
		
		checkTravas = new JCheckBox("Travas");
		checkTravas.setBounds(5, 41, 80, 20);
		checkTravas.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkTravas.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkTravas.setBackground(new Color(153, 180, 209));
		
		checkSensorRe = new JCheckBox("Sensor de ré");
		checkSensorRe.setBounds(5, 64, 110, 20);
		checkSensorRe.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkSensorRe.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkSensorRe.setBackground(new Color(153, 180, 209));
		
		checkBancosCouro = new JCheckBox("Bancos de couro");
		checkBancosCouro.setBounds(120, 87, 140, 20);
		checkBancosCouro.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkBancosCouro.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkBancosCouro.setBackground(new Color(153, 180, 209));
		
		checkFreiosABS = new JCheckBox("Freios ABS");
		checkFreiosABS.setBounds(120, 18, 120, 20);
		checkFreiosABS.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkFreiosABS.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkFreiosABS.setBackground(new Color(153, 180, 209));
		
		checkAirbags = new JCheckBox("Airbags");
		checkAirbags.setBounds(120, 41, 80, 20);
		checkAirbags.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkAirbags.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkAirbags.setBackground(new Color(153, 180, 209));
		
		checkCameraRe = new JCheckBox("Câmera de ré");
		checkCameraRe.setBounds(120, 64, 120, 20);
		checkCameraRe.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkCameraRe.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkCameraRe.setBackground(new Color(153, 180, 209));
		
		checkMultimidia = new JCheckBox("Multimídia");
		checkMultimidia.setBounds(5, 87, 100, 20);
		checkMultimidia.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkMultimidia.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkMultimidia.setBackground(new Color(153, 180, 209));
		
		checkBancosRegulaveis = new JCheckBox("Bancos reguláveis");
		checkBancosRegulaveis.setBounds(263, 18, 150, 20);
		checkBancosRegulaveis.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkBancosRegulaveis.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkBancosRegulaveis.setBackground(new Color(153, 180, 209));
		
		checkVidrosEletricos = new JCheckBox("Vidros elétricos");
		checkVidrosEletricos.setBounds(263, 41, 130, 20);
		checkVidrosEletricos.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkVidrosEletricos.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkVidrosEletricos.setBackground(new Color(153, 180, 209));
		
		checkDirHidraulica = new JCheckBox("Direção hidráulica");
		checkDirHidraulica.setBounds(263, 64, 150, 20);
		checkDirHidraulica.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkDirHidraulica.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkDirHidraulica.setBackground(new Color(153, 180, 209));
		
		checkDirEletrica = new JCheckBox("Direção elétrica");
		checkDirEletrica.setBounds(263, 87, 150, 20);
		checkDirEletrica.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		checkDirEletrica.setCursor(new Cursor(Cursor.HAND_CURSOR));
		checkDirEletrica.setBackground(new Color(153, 180, 209));
		
		lblSituacao = new JLabel("Situação");
		lblSituacao.setBounds(448, 105, 80, 50);
		lblSituacao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtSituacao = new JTextFieldHint(txtSituacao, "", "SITUAÇÃO");
		txtSituacao.setBounds(448, 139, 150, 22);
		txtSituacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtSituacao.setEditable(false);
		txtSituacao.setFocusable(false);
		txtSituacao.setBackground(Color.WHITE);
		txtSituacao.setForeground(Color.BLUE);
		txtSituacao.setDocument(new ActionCapslock());
		
		lblDataLocacao = new JLabel("Data da Locação");
		lblDataLocacao.setBounds(448, 150, 100, 50);
		lblDataLocacao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		lblDataLocacao.setVisible(false);
		
		txtDataLocacao = new JTextFieldHint(txtDataLocacao, "", "DATA LOCAÇÃO");
		txtDataLocacao.setBounds(448, 185, 150, 22);
		txtDataLocacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtDataLocacao.setEditable(false);
		txtDataLocacao.setFocusable(false);
		txtDataLocacao.setBackground(Color.WHITE);
		txtDataLocacao.setForeground(Color.BLUE);
		txtDataLocacao.setVisible(false);
		txtDataLocacao.setDocument(new ActionCapslock());
		
		btnSalvar = new PersonalizaButtons("imgToSave.png", "imgToSaveOver.png", "Salvar", "Salvar");
		btnSalvar.setBounds(580, 245, 50, 70);
		btnSalvar.addActionListener(new ActionSalvarAlterarVeiculo() {
			public void actionPerformed(ActionEvent e) {
				codigo = lblCodigo.getText();
				placa = txtPlaca.getText();
				marca = jcbMarca.getSelectedItem().toString();
				modelo = jcbModelo.getSelectedItem().toString();
				versao = txtVersao.getText();
				anoFab = txtAnoFabricacao.getText();
				anoMod = txtAnoModelo.getText();
				cor = txtCor.getText();
				combustivel = txtCombustivel.getText();
				transmissao = txtTransmissao.getText();
				renavam = txtRenavam.getText();
				portas = txtQtdePortas.getText();
				alarme = checkAlarme.isSelected() ? "SIM" : "NÃO";
				travas = checkTravas.isSelected() ? "SIM" : "NÃO";
				sensorRe = checkSensorRe.isSelected() ? "SIM" : "NÃO";
				bancosCouro = checkBancosCouro.isSelected() ? "SIM" : "NÃO";
				freiosAbs = checkFreiosABS.isSelected() ? "SIM" : "NÃO";
				airbags = checkAirbags.isSelected() ? "SIM" : "NÃO";
				cameraRe = checkCameraRe.isSelected() ? "SIM" : "NÃO";
				multimidia = checkMultimidia.isSelected() ? "SIM" : "NÃO";
				bancosRegulaveis = checkBancosRegulaveis.isSelected() ? "SIM" : "NÃO";
				vidrosEletricos = checkVidrosEletricos.isSelected() ? "SIM" : "NÃO";
				direcaoHidraulica = checkDirHidraulica.isSelected() ? "SIM" : "NÃO";
				direcaoEletrica = checkDirEletrica.isSelected() ? "SIM" : "NÃO";
				situacao = txtSituacao.getText();
				if (validarCampos(placa, marca, modelo, versao, anoFab, anoMod, cor, combustivel, transmissao, renavam, portas, getRootPane()))
					if (codigo.equals("")) {
						new Thread() {
							public void run() {
								progresso.setVisible(true);
								progresso.setString("Salvando veículo no banco de dados...");
								salvar(codigo, placa, marca, modelo, versao, anoFab, anoMod, cor, combustivel, transmissao, renavam, portas, alarme, travas, sensorRe, bancosCouro, freiosAbs, airbags, cameraRe, multimidia, bancosRegulaveis, vidrosEletricos, direcaoHidraulica, direcaoEletrica, situacao, getRootPane(), Opcoes.SALVAR);
								progresso.setVisible(false);
							}
						}.start();
					} else {
						new Thread() {
							public void run() {
								progresso.setVisible(true);
								progresso.setString("Alterando veículo no banco de dados...");
								salvar(codigo, placa, marca, modelo, versao, anoFab, anoMod, cor, combustivel, transmissao, renavam, portas, alarme, travas, sensorRe, bancosCouro, freiosAbs, airbags, cameraRe, multimidia, bancosRegulaveis, vidrosEletricos, direcaoHidraulica, direcaoEletrica, situacao, getRootPane(), Opcoes.ALTERAR);
								progresso.setVisible(false);
							}
						}.start();
					}
			}
		});
		
		btnFechar = new PersonalizaButtons("imgClose.png", "imgClose.png", "Fechar Janela", "Fechar");
		btnFechar.setBounds(640, 245, 50, 70);
		btnFechar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); } });
		
		add(progresso);
		dadosVeiculo.add(lblPlaca);
		dadosVeiculo.add(txtPlaca);
		dadosVeiculo.add(lblMarca);
		dadosVeiculo.add(jcbMarca);
		dadosVeiculo.add(lblModelo);
		dadosVeiculo.add(jcbModelo);
		dadosVeiculo.add(lblVersao);
		dadosVeiculo.add(txtVersao);
		dadosVeiculo.add(lblAnoFabricacao);
		dadosVeiculo.add(txtAnoFabricacao);
		dadosVeiculo.add(lblAnoModelo);
		dadosVeiculo.add(txtAnoModelo);
		dadosVeiculo.add(lblCor);
		dadosVeiculo.add(txtCor);
		dadosVeiculo.add(lblCombustivel);
		dadosVeiculo.add(txtCombustivel);
		dadosVeiculo.add(lblTransmissao);
		dadosVeiculo.add(txtTransmissao);
		dadosVeiculo.add(lblRenavam);
		dadosVeiculo.add(txtRenavam);
		dadosVeiculo.add(lblQtdePortas);
		dadosVeiculo.add(txtQtdePortas);
		itens.add(checkAlarme);
		itens.add(checkTravas);
		itens.add(checkSensorRe);
		itens.add(checkBancosCouro);
		itens.add(checkFreiosABS);
		itens.add(checkAirbags);
		itens.add(checkCameraRe);
		itens.add(checkMultimidia);
		itens.add(checkBancosRegulaveis);
		itens.add(checkVidrosEletricos);
		itens.add(checkDirHidraulica);
		itens.add(checkDirEletrica);
		dadosVeiculo.add(itens);
		dadosVeiculo.add(lblSituacao);
		dadosVeiculo.add(txtSituacao);
		dadosVeiculo.add(lblDataLocacao);
		dadosVeiculo.add(txtDataLocacao);
		add(dadosVeiculo);
		add(lblAux);
		add(lblCodigo);
		add(btnSalvar);
		add(btnFechar);
		addWindowListener(new ActionCarregarDadosVeiculos() {
			public void windowOpened(WindowEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Iniciando... Aguarde!");
						txtSituacao.setText("DISPONÍVEL");
						PopularCombos.popularCombosMarcaModelo(jcbMarca, jcbModelo);
						carregaDadosVeiculos(lblCodigo, txtSituacao, txtDataLocacao, txtPlaca, jcbMarca, jcbModelo, txtVersao, txtAnoFabricacao, txtAnoModelo, txtCor, txtCombustivel, txtTransmissao, txtRenavam, txtQtdePortas, checkAlarme, checkTravas, checkSensorRe, checkBancosCouro, checkFreiosABS, checkAirbags, checkCameraRe, checkMultimidia, checkBancosRegulaveis, checkVidrosEletricos, checkDirHidraulica, checkDirEletrica);
						progresso.setVisible(false);
					}
				}.start();
			}
		});
		
		LOG.info("View de cadastros e alterações dos veículos iniciado com sucesso!");
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
		txtPlaca.setText("");
		jcbMarca.setSelectedItem("SELECIONE..");
		jcbModelo.removeAllItems();
		jcbModelo.addItem("SELECIONE..");
		txtVersao.setText("");
		txtAnoFabricacao.setText("");
		txtAnoModelo.setText("");
		txtCor.setText("");
		txtCombustivel.setText("");
		txtTransmissao.setText("");
		txtRenavam.setText("");
		txtQtdePortas.setText("");
		checkAlarme.setSelected(false);
		checkTravas.setSelected(false);
		checkSensorRe.setSelected(false);
		checkBancosCouro.setSelected(false);
		checkFreiosABS.setSelected(false);
		checkAirbags.setSelected(false);
		checkCameraRe.setSelected(false);
		checkMultimidia.setSelected(false);
		checkBancosRegulaveis.setSelected(false);
		checkVidrosEletricos.setSelected(false);
		checkDirHidraulica.setSelected(false);
		checkDirEletrica.setSelected(false);
		txtPlaca.requestFocus();
	}
}