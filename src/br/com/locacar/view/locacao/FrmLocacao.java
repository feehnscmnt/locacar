package br.com.locacar.view.locacao;

import br.com.locacar.action.componentes.*;
import br.com.locacar.view.componentes.*;
import br.com.locacar.action.locacao.*;
import br.com.locacar.action.veiculo.*;
import br.com.locacar.action.email.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.opcao.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import com.toedter.calendar.*;
import br.com.locacar.util.*;
import br.com.locacar.view.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.awt.*;
import java.net.*;

/**
 * View de cadastro de locações!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmLocacao extends JDialog {
	private static final Logger LOG = LogManager.getLogger(FrmLocacao.class.getName());
	public static JLabel lblNome, lblEndereco, lblCidade, lblBairro, lblEmail, lblPlaca, lblMarca, lblModelo, lblAnoModelo, lblCor, lblCombustivel, lblRenavam, lblDataLocacao, lblHoraLocacao, lblDataRetornoLocacao, lblHoraRetornoLocacao, lblKilometragem, lblObservacoes, lblLocalEntrega, lblValorDia, lblQtdeDias, lblValorTaxaServico, lblValorProtecao, lblValorTotal, lblNumLocacao, lblAux, lblSituacao, lblCodigo;
	public static JFormattedTextField txtCpfCnpj, txtPlaca, txtValorDia, txtValorTaxaServico, txtValorProtecao, txtValorTotal;
	public static JTextField txtNome, txtEndereco, txtCidade, txtBairro, txtEmail, txtCombustivel, txtCor, txtAnoModelo, txtRenavam, txtLocalEntrega, txtKilometragem, txtObservacao, txtQtdeDias, txtNumLocacao, txtSituacao;
	public static JRadioButton rdbCpf, rdbCnpj, rdbClientePf, rdbClientePj, rdbVeiculoCadastrado, rdbVeiculoNaoCadastrado;
	public static JDateChooser jdcDataLocacao, jdcDataRetornoLocacao;
	public static JComboBox<Object> jcbMarca, jcbModelo, jcbHoraLocacao, jcbHoraRetornoLocacao;
	String codigo, situacao, numeroLocacao, cpfCnpj, nome, endereco, cidade, bairro, email, placa, marca, modelo, combustivel, cor, ano, renavam, kilometragem, dataLocacao, horaLocacao, dataRetornoLocacao, horaRetornoLocacao, observacoes, localEntrega, valorDia, qtdeDias, valorTaxaServico, valorProtecao, valorTotal;
	public JButton btnBuscarCliente, btnBuscarVeiculo, btnCalcular, btnSalvar, btnFechar;
	JProgressBar progresso;
	
	public FrmLocacao() { LOG.info("View de cadastro de locações iniciado com sucesso!"); setModal(true); initComponents(); noMove(); }
	
	private void initComponents() {
		getContentPane().setLayout(null);
		setSize(710, 568);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("LOCACAR - Cadastrar nova locação");
		getContentPane().setBackground(new Color(153, 180, 209));
		URL img = new PathFilesUtil().getIconTopo();
		Image icone = Toolkit.getDefaultToolkit().getImage(img);
		setIconImage(icone);
		
		progresso = new BarraProgresso();
		progresso.setBounds(220, 465, 245, 58);
		progresso.setVisible(false);
		
		JPanel dadosCliente = new JPanel();
		dadosCliente.setLayout(null);
		dadosCliente.setBounds(5, 1, 334, 250);
		dadosCliente.setBackground(new Color(153, 180, 209));
		dadosCliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DADOS DO CLIENTE", 4, Font.BOLD, null, Color.BLACK));
		
		JPanel dadosVeiculo = new JPanel();
		dadosVeiculo.setLayout(null);
		dadosVeiculo.setBounds(339, 1, 350, 250);
		dadosVeiculo.setBackground(new Color(153, 180, 209));
		dadosVeiculo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DADOS DO VEÍCULO", 4, Font.BOLD, null, Color.BLACK));
		
		JPanel dadosLocacao = new JPanel();
		dadosLocacao.setLayout(null);
		dadosLocacao.setBounds(5, 253, 684, 190);
		dadosLocacao.setBackground(new Color(153, 180, 209));
		dadosLocacao.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DADOS DA LOCAÇÃO", 4, Font.BOLD, null, Color.BLACK));
		
		rdbCpf = new JRadioButton("CPF");
		rdbCpf.setBounds(10, 25, 50, 20);
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
		rdbCnpj.setBounds(80, 25, 60, 20);
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
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rdbCpf);
		bg1.add(rdbCnpj);
		
		txtCpfCnpj = new JFormattedTextField(Masks.mascaraCpf());
		txtCpfCnpj.setBounds(170, 25, 156, 20);
		txtCpfCnpj.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCpfCnpj.setBackground(Color.WHITE);
		txtCpfCnpj.setForeground(Color.BLUE);
		txtCpfCnpj.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCpfCnpj.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(13, 35, 80, 50);
		lblNome.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtNome = new JTextFieldHint(txtNome, "", "NOME DO CLIENTE");
		txtNome.setBounds(50, 51, 276, 20);
		txtNome.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtNome.setEditable(false);
		txtNome.setFocusable(false);
		txtNome.setBackground(Color.WHITE);
		txtNome.setForeground(Color.BLUE);
		txtNome.setDocument(new ActionCapslock());
		
		lblEndereco = new JLabel("End");
		lblEndereco.setBounds(13, 60, 80, 50);
		lblEndereco.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtEndereco = new JTextFieldHint(txtEndereco, "", "ENDEREÇO DO CLIENTE");
		txtEndereco.setBounds(50, 76, 276, 20);
		txtEndereco.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtEndereco.setEditable(false);
		txtEndereco.setFocusable(false);
		txtEndereco.setBackground(Color.WHITE);
		txtEndereco.setForeground(Color.BLUE);
		txtEndereco.setDocument(new ActionCapslock());
		
		lblCidade = new JLabel("Cid");
		lblCidade.setBounds(13, 85, 80, 50);
		lblCidade.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtCidade = new JTextFieldHint(txtCidade, "", "CIDADE");
		txtCidade.setBounds(50, 101, 276, 20);
		txtCidade.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCidade.setEditable(false);
		txtCidade.setFocusable(false);
		txtCidade.setBackground(Color.WHITE);
		txtCidade.setForeground(Color.BLUE);
		txtCidade.setDocument(new ActionCapslock());
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(13, 110, 80, 50);
		lblBairro.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtBairro = new JTextFieldHint(txtBairro, "", "BAIRRO");
		txtBairro.setBounds(50, 126, 276, 20);
		txtBairro.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtBairro.setEditable(false);
		txtBairro.setFocusable(false);
		txtBairro.setBackground(Color.WHITE);
		txtBairro.setForeground(Color.BLUE);
		txtBairro.setDocument(new ActionCapslock());
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(13, 137, 80, 50);
		lblEmail.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtEmail = new JTextFieldHint(txtEmail, "", "E-MAIL");
		txtEmail.setBounds(50, 151, 276, 20);
		txtEmail.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtEmail.setEditable(false);
		txtEmail.setFocusable(false);
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setForeground(Color.BLUE);
		txtEmail.setDocument(new ActionCapslock());
		
		rdbClientePf = new JRadioButton("Cliente PF");
		rdbClientePf.setBounds(10, 200, 85, 20);
		rdbClientePf.setFocusable(false);
		rdbClientePf.setSelected(true);
		rdbClientePf.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rdbClientePf.setBackground(new Color(153, 180, 209));
		
		rdbClientePj = new JRadioButton("Cliente PJ");
		rdbClientePj.setBounds(100, 200, 85, 20);
		rdbClientePj.setFocusable(false);
		rdbClientePj.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rdbClientePj.setBackground(new Color(153, 180, 209));
		
		ButtonGroup bg2 = new ButtonGroup();
		bg2.add(rdbClientePf);
		bg2.add(rdbClientePj);
		
		btnBuscarCliente = new PersonalizaButtons("imgSearch.png", "imgSearch.png", "Buscar cliente", "Buscar cliente");
		btnBuscarCliente.setBounds(225, 172, 100, 70);
		btnBuscarCliente.setFocusable(false);
		btnBuscarCliente.addActionListener(new ActionBuscarCliente() {
			public void actionPerformed(ActionEvent e) {
				if (validaCpfCnpj(rdbCpf, rdbCnpj, txtCpfCnpj.getText(), getRootPane())) {
					new Thread() {
						public void run() {
							progresso.setVisible(true);
							progresso.setString("Buscando cliente no banco de dados...");
							buscarCliente(txtCpfCnpj.getText(), txtNome, txtEndereco, txtCidade, txtBairro, txtEmail, rdbClientePf, rdbClientePj, getRootPane());
							progresso.setVisible(false);
						}
					}.start();
				}
			}
		});
		
		rdbVeiculoCadastrado = new JRadioButton("Veículo cadastrado");
		rdbVeiculoCadastrado.setBounds(10, 25, 145, 20);
		rdbVeiculoCadastrado.setFocusable(false);
		rdbVeiculoCadastrado.setSelected(true);
		rdbVeiculoCadastrado.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rdbVeiculoCadastrado.setBackground(new Color(153, 180, 209));
		rdbVeiculoCadastrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
				jcbMarca.setEnabled(false);
				jcbModelo.setEnabled(false);
				txtAnoModelo.setEditable(false);
				txtCor.setEditable(false);
				txtCombustivel.setEditable(false);
				txtRenavam.setEditable(false);
				btnBuscarVeiculo.setEnabled(true);
				txtPlaca.requestFocus();
			}
		});
		
		rdbVeiculoNaoCadastrado = new JRadioButton("Veículo não cadastrado");
		rdbVeiculoNaoCadastrado.setBounds(175, 25, 160, 20);
		rdbVeiculoNaoCadastrado.setFocusable(false);
		rdbVeiculoNaoCadastrado.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rdbVeiculoNaoCadastrado.setBackground(new Color(153, 180, 209));
		rdbVeiculoNaoCadastrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaCampos();
				jcbMarca.setEnabled(true);
				jcbModelo.setEnabled(true);
				txtAnoModelo.setEditable(true);
				txtCor.setEditable(true);
				txtCombustivel.setEditable(true);
				txtRenavam.setEditable(true);
				btnBuscarVeiculo.setEnabled(false);
				txtPlaca.requestFocus();
			}
		});
		
		ButtonGroup bg3 = new ButtonGroup();
		bg3.add(rdbVeiculoCadastrado);
		bg3.add(rdbVeiculoNaoCadastrado);
		
		lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(13, 35, 80, 50);
		lblPlaca.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtPlaca = new JFormattedTextField(Masks.mascaraPlaca());
		txtPlaca.setBounds(50, 51, 78, 22);
		txtPlaca.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtPlaca.setBackground(Color.WHITE);
		txtPlaca.setForeground(Color.BLUE);
		txtPlaca.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtPlaca.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		lblMarca = new JLabel("Marca");
		lblMarca.setBounds(13, 60, 80, 50);
		lblMarca.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbMarca = new JComboBox<Object>();
		jcbMarca.setBounds(50, 76, 167, 21);
		jcbMarca.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbMarca.setEnabled(false);
		jcbMarca.setBackground(Color.WHITE);
		jcbMarca.setForeground(Color.BLUE);
		jcbMarca.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbMarca.addItem("SELECIONE..");
		jcbMarca.addFocusListener(new ActionListarModelosPorMarca() {
			public void focusLost(FocusEvent e) {
				listarModeloPorMarca(jcbMarca, jcbModelo);
			}
		});
		
		lblModelo = new JLabel("Mod");
		lblModelo.setBounds(13, 85, 80, 50);
		lblModelo.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbModelo = new JComboBox<Object>();
		jcbModelo.setBounds(50, 101, 167, 21);
		jcbModelo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbModelo.setEnabled(false);
		jcbModelo.setBackground(Color.WHITE);
		jcbModelo.setForeground(Color.BLUE);
		jcbModelo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbModelo.addItem("SELECIONE..");
		
		lblAnoModelo = new JLabel("Ano");
		lblAnoModelo.setBounds(13, 110, 80, 50);
		lblAnoModelo.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtAnoModelo = new JTextFieldHint(txtAnoModelo, "", "AM");
		txtAnoModelo.setBounds(50, 126, 55, 22);
		txtAnoModelo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtAnoModelo.setEditable(false);
		txtAnoModelo.setBackground(Color.WHITE);
		txtAnoModelo.setForeground(Color.BLUE);
		txtAnoModelo.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtAnoModelo.setDocument(new ActionLimiteCaracteres());
		
		lblCor = new JLabel("Cor");
		lblCor.setBounds(113, 110, 80, 50);
		lblCor.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtCor = new JTextFieldHint(txtCor, "", "COR");
		txtCor.setBounds(140, 126, 78, 22);
		txtCor.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCor.setEditable(false);
		txtCor.setBackground(Color.WHITE);
		txtCor.setForeground(Color.BLUE);
		txtCor.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCor.setDocument(new ActionCapslock());
		
		lblCombustivel = new JLabel("Comb");
		lblCombustivel.setBounds(13, 136, 80, 50);
		lblCombustivel.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtCombustivel = new JTextFieldHint(txtCombustivel, "", "COMBUSTÍVEL");
		txtCombustivel.setBounds(50, 151, 168, 22);
		txtCombustivel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCombustivel.setEditable(false);
		txtCombustivel.setBackground(Color.WHITE);
		txtCombustivel.setForeground(Color.BLUE);
		txtCombustivel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCombustivel.setDocument(new ActionCapslock());
		
		lblRenavam = new JLabel("Ren");
		lblRenavam.setBounds(13, 163, 80, 50);
		lblRenavam.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtRenavam = new JTextFieldHint(txtRenavam, "", "RENAVAM");
		txtRenavam.setBounds(50, 176, 168, 22);
		txtRenavam.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtRenavam.setEditable(false);
		txtRenavam.setBackground(Color.WHITE);
		txtRenavam.setForeground(Color.BLUE);
		txtRenavam.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtRenavam.setDocument(new ActionLimiteCaracteres());
		txtRenavam.addKeyListener(new ActionApenasNumeros() {
			public void keyTyped(KeyEvent e) {
				valoresNumericos(e);
			}
		});
		
		btnBuscarVeiculo = new PersonalizaButtons("imgSearch.png", "imgSearch.png", "Buscar veículo", "Buscar veículo");
		btnBuscarVeiculo.setBounds(242, 172, 100, 70);
		btnBuscarVeiculo.setFocusable(false);
		btnBuscarVeiculo.addActionListener(new ActionBuscarVeiculo() {
			public void actionPerformed(ActionEvent e) {
				if (rdbVeiculoCadastrado.isSelected()) {
					if (validaCampoPlaca(txtPlaca.getText(), getRootPane())) {
						buscarVeiculo(rdbVeiculoCadastrado, txtPlaca, jcbMarca, jcbModelo, txtAnoModelo, txtCor, txtCombustivel, txtRenavam, getRootPane());
					}
				}
			}
		});
		
		lblDataLocacao = new JLabel("Data da Locação");
		lblDataLocacao.setBounds(10, 10, 90, 50);
		lblDataLocacao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jdcDataLocacao = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		((JTextFieldDateEditor) jdcDataLocacao.getDateEditor()).setBackground(Color.WHITE);
		jdcDataLocacao.setBounds(10, 45, 123, 20);
		jdcDataLocacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jdcDataLocacao.setBackground(Color.WHITE);
		jdcDataLocacao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblHoraLocacao = new JLabel("Hora da Locação");
		lblHoraLocacao.setBounds(145, 10, 90, 50);
		lblHoraLocacao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbHoraLocacao = new JComboBox<Object>();
		jcbHoraLocacao.setBounds(145, 45, 123, 20);
		jcbHoraLocacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbHoraLocacao.setBackground(Color.WHITE);
		jcbHoraLocacao.setForeground(Color.BLUE);
		jcbHoraLocacao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbHoraLocacao.addItem("SELECIONE..");
		
		lblDataRetornoLocacao = new JLabel("Data de Retorno");
		lblDataRetornoLocacao.setBounds(280, 10, 90, 50);
		lblDataRetornoLocacao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jdcDataRetornoLocacao = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		((JTextFieldDateEditor) jdcDataRetornoLocacao.getDateEditor()).setBackground(Color.WHITE);
		jdcDataRetornoLocacao.setBounds(280, 45, 123, 20);
		jdcDataRetornoLocacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jdcDataRetornoLocacao.setBackground(Color.WHITE);
		jdcDataRetornoLocacao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblHoraRetornoLocacao = new JLabel("Hora de Retorno");
		lblHoraRetornoLocacao.setBounds(414, 10, 90, 50);
		lblHoraRetornoLocacao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbHoraRetornoLocacao = new JComboBox<Object>();
		jcbHoraRetornoLocacao.setBounds(414, 45, 123, 20);
		jcbHoraRetornoLocacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbHoraRetornoLocacao.setBackground(Color.WHITE);
		jcbHoraRetornoLocacao.setForeground(Color.BLUE);
		jcbHoraRetornoLocacao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbHoraRetornoLocacao.addItem("SELECIONE..");
		
		lblKilometragem = new JLabel("Kilometragem");
		lblKilometragem.setBounds(550, 10, 90, 50);
		lblKilometragem.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtKilometragem = new JTextFieldHint(txtKilometragem, "", "KILÔMETROS");
		txtKilometragem.setBounds(550, 45, 123, 21);
		txtKilometragem.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtKilometragem.setBackground(Color.WHITE);
		txtKilometragem.setForeground(Color.BLUE);
		txtKilometragem.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		lblObservacoes = new JLabel("Observações");
		lblObservacoes.setBounds(10, 50, 90, 50);
		lblObservacoes.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtObservacao = new JTextFieldHint(txtObservacao, "", "OBSERVAÇÕES");
		txtObservacao.setBounds(10, 84, 323, 20);
		txtObservacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtObservacao.setBackground(Color.WHITE);
		txtObservacao.setForeground(Color.BLUE);
		txtObservacao.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtObservacao.setDocument(new ActionCapslock());
		
		lblLocalEntrega = new JLabel("Local de Entrega");
		lblLocalEntrega.setBounds(350, 50, 90, 50);
		lblLocalEntrega.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtLocalEntrega = new JTextFieldHint(txtLocalEntrega, "", "LOCAL DE ENTREGA");
		txtLocalEntrega.setBounds(350, 84, 323, 20);
		txtLocalEntrega.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtLocalEntrega.setBackground(Color.WHITE);
		txtLocalEntrega.setForeground(Color.BLUE);
		txtLocalEntrega.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtLocalEntrega.setDocument(new ActionCapslock());
		
		lblValorDia = new JLabel("Valor do Dia");
		lblValorDia.setBounds(10, 89, 90, 50);
		lblValorDia.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtValorDia = new JFormattedTextField(Masks.mascaraValorMonetario());
		txtValorDia.setBounds(10, 122, 70, 20);
		txtValorDia.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtValorDia.setBackground(Color.WHITE);
		txtValorDia.setForeground(Color.GREEN);
		txtValorDia.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtValorDia.addFocusListener(new ActionQtdeDiasLocacao() {
			public void focusLost(FocusEvent e) {
				qtdeDiasLocacao(jdcDataLocacao, jdcDataRetornoLocacao, txtQtdeDias, getRootPane());
			}
		});
		
		lblQtdeDias = new JLabel("Qtde Dias");
		lblQtdeDias.setBounds(95, 89, 90, 50);
		lblQtdeDias.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtQtdeDias = new JTextField();
		txtQtdeDias.setBounds(95, 122, 70, 20);
		txtQtdeDias.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtQtdeDias.setEditable(false);
		txtQtdeDias.setFocusable(false);
		txtQtdeDias.setBackground(Color.WHITE);
		txtQtdeDias.setForeground(Color.GREEN);
		
		lblValorTaxaServico = new JLabel("Taxa Serviços");
		lblValorTaxaServico.setBounds(10, 126, 90, 50);
		lblValorTaxaServico.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtValorTaxaServico = new JFormattedTextField(Masks.mascaraValorMonetario());
		txtValorTaxaServico.setBounds(10, 160, 70, 20);
		txtValorTaxaServico.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtValorTaxaServico.setBackground(Color.WHITE);
		txtValorTaxaServico.setForeground(Color.GREEN);
		txtValorTaxaServico.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		lblValorProtecao = new JLabel("Val. Proteção");
		lblValorProtecao.setBounds(95, 126, 90, 50);
		lblValorProtecao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtValorProtecao = new JFormattedTextField(Masks.mascaraValorMonetario());
		txtValorProtecao.setBounds(95, 160, 70, 20);
		txtValorProtecao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtValorProtecao.setBackground(Color.WHITE);
		txtValorProtecao.setForeground(Color.GREEN);
		txtValorProtecao.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		lblValorTotal = new JLabel("Total");
		lblValorTotal.setBounds(182, 89, 90, 50);
		lblValorTotal.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtValorTotal = new JFormattedTextField();
		txtValorTotal.setBounds(182, 122, 100, 20);
		txtValorTotal.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtValorTotal.setEditable(false);
		txtValorTotal.setBackground(Color.WHITE);
		txtValorTotal.setForeground(Color.GREEN);
		
		btnCalcular = new PersonalizaButtons("imgCalculate.png", "imgCalculate.png", "Calcular", "Calcular");
		btnCalcular.setBounds(300, 110, 50, 70);
		btnCalcular.addActionListener(new ActionCalcularValorLocacao() {
			public void actionPerformed(ActionEvent e) {
				calcular(txtValorDia, txtQtdeDias, txtValorTaxaServico, txtValorProtecao, txtValorTotal, getRootPane());
			}
		});
		
		lblSituacao = new JLabel("Situação");
		lblSituacao.setBounds(578, 89, 90, 50);
		lblSituacao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtSituacao = new JTextField();
		txtSituacao.setBounds(578, 122, 95, 20);
		txtSituacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtSituacao.setEditable(false);
		txtSituacao.setFocusable(false);
		txtSituacao.setBackground(Color.WHITE);
		txtSituacao.setForeground(Color.RED);
		
		lblNumLocacao = new JLabel("Número Locação");
		lblNumLocacao.setBounds(578, 126, 90, 50);
		lblNumLocacao.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtNumLocacao = new JTextField();
		txtNumLocacao.setBounds(578, 160, 95, 20);
		txtNumLocacao.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtNumLocacao.setEditable(false);
		txtNumLocacao.setFocusable(false);
		txtNumLocacao.setBackground(Color.WHITE);
		txtNumLocacao.setForeground(Color.BLUE);
		
		lblAux = new JLabel("");
		lblAux.setBounds(10, 427, 200, 50);
		lblAux.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		
		lblCodigo = new JLabel("");
		lblCodigo.setBounds(117, 427, 200, 50);
		lblCodigo.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		
		btnSalvar = new PersonalizaButtons("imgToSave.png", "imgToSaveOver.png", "Salvar", "Salvar");
		btnSalvar.setBounds(573, 450, 50, 70);
		btnSalvar.addActionListener(new ActionSalvarLocacao() {
			public void actionPerformed(ActionEvent e) {
				codigo = lblCodigo.getText();
				numeroLocacao = txtNumLocacao.getText();
				cpfCnpj = txtCpfCnpj.getText();
				nome = txtNome.getText();
				endereco = txtEndereco.getText();
				cidade = txtCidade.getText();
				bairro = txtBairro.getText();
				email = txtEmail.getText();
				placa = txtPlaca.getText();
				marca = jcbMarca.getSelectedItem().toString();
				modelo = jcbModelo.getSelectedItem().toString();
				combustivel = txtCombustivel.getText();
				cor = txtCor.getText();
				ano = txtAnoModelo.getText();
				renavam = txtRenavam.getText();
				kilometragem = txtKilometragem.getText();
				getDataLocacao();
				horaLocacao = jcbHoraLocacao.getSelectedItem().toString();
				getDataRetornoLocacao();
				horaRetornoLocacao = jcbHoraRetornoLocacao.getSelectedItem().toString();
				localEntrega = txtLocalEntrega.getText();
				observacoes = txtObservacao.getText();
				valorDia = txtValorDia.getText();
				qtdeDias = txtQtdeDias.getText();
				valorTaxaServico = txtValorTaxaServico.getText();
				valorProtecao = txtValorProtecao.getText();
				valorTotal = txtValorTotal.getText();
				situacao = txtSituacao.getText();
				if (validaCamposCliente(nome, endereco, cidade, bairro, email, getRootPane()) &&
					validaCamposVeiculo(placa, marca, modelo, combustivel, cor, ano, renavam, getRootPane()) &&
					validaCamposDadosLocacao(getDataLocacao(), horaLocacao, getDataRetornoLocacao(), horaRetornoLocacao, kilometragem, observacoes, localEntrega, valorDia, qtdeDias, valorTaxaServico, valorProtecao, valorTotal, getRootPane()))
					if (codigo.equals("")) {
						int os = JOptionPane.showConfirmDialog(null, new MensagensModel(Bundle.getString("gerarOrdemServico")).getText(), "GERAR OS", JOptionPane.YES_NO_OPTION);
						if (os == JOptionPane.YES_OPTION) {
							new Thread() {
								public void run() {
									progresso.setVisible(true);
									progresso.setString("Gerando ordem de serviço...");
									GeradorOrdemServico.gerarOs(numeroLocacao, cpfCnpj, nome, endereco, bairro, cidade, email, placa, marca, modelo, cor, ano, combustivel, renavam, kilometragem, getDataLocacao(), horaLocacao, getDataRetornoLocacao(), horaRetornoLocacao, localEntrega, observacoes, qtdeDias, valorDia, valorTaxaServico, valorProtecao, valorTotal, FrmLogin.USUARIO_LOGADO);
									progresso.setString("Enviando e-mail para o cliente...");
									EnviarEmail.enviarEmail(email, numeroLocacao);
									progresso.setString("Salvando locação no banco de dados...");
									salvar(codigo, numeroLocacao, cpfCnpj, nome, endereco, cidade, bairro, email, placa, marca, modelo, combustivel, cor, ano, renavam, kilometragem, getDataLocacao(), horaLocacao, getDataRetornoLocacao(), horaRetornoLocacao, localEntrega, observacoes, valorDia, qtdeDias, valorTaxaServico, valorProtecao, valorTotal, situacao, getRootPane(), Opcoes.SALVAR);
									progresso.setVisible(false);
								}
							}.start();
						} else {
							new Thread() {
								public void run() {
									progresso.setVisible(true);
									progresso.setString("Salvando locação no banco de dados...");
									salvar(codigo, numeroLocacao, cpfCnpj, nome, endereco, cidade, bairro, email, placa, marca, modelo, combustivel, cor, ano, renavam, kilometragem, getDataLocacao(), horaLocacao, getDataRetornoLocacao(), horaRetornoLocacao, localEntrega, observacoes, valorDia, qtdeDias, valorTaxaServico, valorProtecao, valorTotal, situacao, getRootPane(), Opcoes.SALVAR);
									progresso.setVisible(false);
								}
							}.start();
						}
					}
			}
		});
		
		btnFechar = new PersonalizaButtons("imgClose.png", "imgClose.png", "Fechar Janela", "Fechar");
		btnFechar.setBounds(633, 450, 50, 70);
		btnFechar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); } });
		
		add(progresso);
		dadosCliente.add(rdbCpf);
		dadosCliente.add(rdbCnpj);
		dadosCliente.add(txtCpfCnpj);
		dadosCliente.add(lblNome);
		dadosCliente.add(txtNome);
		dadosCliente.add(lblEndereco);
		dadosCliente.add(txtEndereco);
		dadosCliente.add(lblCidade);
		dadosCliente.add(txtCidade);
		dadosCliente.add(lblBairro);
		dadosCliente.add(txtBairro);
		dadosCliente.add(lblEmail);
		dadosCliente.add(txtEmail);
		dadosCliente.add(rdbClientePf);
		dadosCliente.add(rdbClientePj);
		dadosCliente.add(btnBuscarCliente);
		add(dadosCliente);
		dadosVeiculo.add(rdbVeiculoCadastrado);
		dadosVeiculo.add(rdbVeiculoNaoCadastrado);
		dadosVeiculo.add(lblPlaca);
		dadosVeiculo.add(txtPlaca);
		dadosVeiculo.add(lblMarca);
		dadosVeiculo.add(jcbMarca);
		dadosVeiculo.add(lblModelo);
		dadosVeiculo.add(jcbModelo);
		dadosVeiculo.add(lblAnoModelo);
		dadosVeiculo.add(txtAnoModelo);
		dadosVeiculo.add(lblCor);
		dadosVeiculo.add(txtCor);
		dadosVeiculo.add(lblCombustivel);
		dadosVeiculo.add(txtCombustivel);
		dadosVeiculo.add(lblRenavam);
		dadosVeiculo.add(txtRenavam);
		dadosVeiculo.add(btnBuscarVeiculo);
		add(dadosVeiculo);
		dadosLocacao.add(lblDataLocacao);
		dadosLocacao.add(jdcDataLocacao);
		dadosLocacao.add(lblHoraLocacao);
		dadosLocacao.add(jcbHoraLocacao);
		dadosLocacao.add(lblDataRetornoLocacao);
		dadosLocacao.add(jdcDataRetornoLocacao);
		dadosLocacao.add(lblHoraRetornoLocacao);
		dadosLocacao.add(jcbHoraRetornoLocacao);
		dadosLocacao.add(lblKilometragem);
		dadosLocacao.add(txtKilometragem);
		dadosLocacao.add(lblObservacoes);
		dadosLocacao.add(txtObservacao);
		dadosLocacao.add(lblLocalEntrega);
		dadosLocacao.add(txtLocalEntrega);
		dadosLocacao.add(lblValorDia);
		dadosLocacao.add(txtValorDia);
		dadosLocacao.add(lblQtdeDias);
		dadosLocacao.add(txtQtdeDias);
		dadosLocacao.add(lblValorTaxaServico);
		dadosLocacao.add(txtValorTaxaServico);
		dadosLocacao.add(lblValorProtecao);
		dadosLocacao.add(txtValorProtecao);
		dadosLocacao.add(lblValorTotal);
		dadosLocacao.add(txtValorTotal);
		dadosLocacao.add(btnCalcular);
		dadosLocacao.add(lblSituacao);
		dadosLocacao.add(txtSituacao);
		dadosLocacao.add(lblNumLocacao);
		dadosLocacao.add(txtNumLocacao);
		add(dadosLocacao);
		add(lblAux);
		add(lblCodigo);
		add(btnSalvar);
		add(btnFechar);
		addWindowListener(new ActionCarregarDadosLocacao() {
			public void windowOpened(WindowEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Iniciando... Aguarde!");
						txtSituacao.setText("EM ABERTO");
						PopularCombos.popularCombosMarcaModelo(jcbMarca, jcbModelo);
						PopularCombos.popularComboHoras(jcbHoraLocacao, jcbHoraRetornoLocacao);
						GeradorNumeroLocacao.numeroLocacao(txtNumLocacao);
						carregarDadosLocacao(lblCodigo, rdbCpf, rdbCnpj, txtCpfCnpj, txtNome, txtEndereco, txtCidade, txtBairro, txtEmail, txtPlaca, jcbMarca, jcbModelo, txtAnoModelo, txtCor, txtCombustivel, txtRenavam, jdcDataLocacao, jcbHoraLocacao, jdcDataRetornoLocacao, jcbHoraRetornoLocacao, txtKilometragem, txtObservacao, txtLocalEntrega, txtValorDia, txtQtdeDias, txtValorTaxaServico, txtValorProtecao, txtValorTotal, txtSituacao, txtNumLocacao);
						progresso.setVisible(false);
					}
				}.start();
			}
		});
	}
	
	private void limpaCampos() {
		jcbMarca.setSelectedItem("SELECIONE..");
		jcbModelo.addItem("SELECIONE..");
		txtAnoModelo.setText("");
		txtCor.setText("");
		txtCombustivel.setText("");
		txtRenavam.setText("");
		txtPlaca.setText("");
		txtPlaca.requestFocus();
	}
	
	private String getDataLocacao() {
		if (jdcDataLocacao.getDate() != null)
			dataLocacao = new SimpleDateFormat("dd/MM/yyyy").format(jdcDataLocacao.getDate());
		else
			dataLocacao = "";
		return dataLocacao;
	}
	
	private String getDataRetornoLocacao() {
		if (jdcDataRetornoLocacao.getDate() != null)
			dataRetornoLocacao = new SimpleDateFormat("dd/MM/yyyy").format(jdcDataRetornoLocacao.getDate());
		else
			dataRetornoLocacao = "";
		return dataRetornoLocacao;
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