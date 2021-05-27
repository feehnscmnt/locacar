package br.com.locacar.view.clientepj;

import br.com.locacar.action.componentes.*;
import br.com.locacar.view.componentes.*;
import br.com.locacar.action.clientepj.*;
import br.com.locacar.view.opcao.*;
import org.apache.logging.log4j.*;
import br.com.locacar.action.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import javax.swing.border.*;
import javax.swing.text.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.net.*;

/**
 * View de cadastro e alteração de clientes pessoa jurídica!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmClientePJ extends JDialog {
	private static final Logger LOG = LogManager.getLogger(FrmClientePJ.class.getName());
	public static JLabel lblNome, lblRazaoSocial, lblTelCel, lblInscricaoEstadual, lblEndereco, lblCep, lblCidade, lblUf, lblBairro, lblComplemento, lblEmail, lblNomeMotorista, lblNumCnh, lblCategCnh, lblAux, lblCodigo;
	private String codigo, nome, razaoSocial, cpfCnpj, contato, inscricaoEstadual, endereco, cep, cidade, uf, bairro, complemento, email, nomeMotorista, numeroCnh, categCnh;
	public static JTextField txtNome, txtRazaoSocial, txtEndereco, txtCidade, txtBairro, txtComplemento, txtEmail, txtNomeMotorista, txtNumCnh;
	public static JFormattedTextField txtCpfCnpj, txtTelCel, txtInscricaoEstadual, txtCep;
	public static JComboBox<Object> jcbUf, jcbCategCnh;
	public static JRadioButton rdbCpf, rdbCnpj;
	public JButton btnSalvar, btnFechar;
	private JProgressBar progresso;
	
	public FrmClientePJ() { LOG.info("View de cadastros e alterações dos clientes pessoa jurídica iniciado com sucesso!"); setModal(true); initComponents(); noMove(); }
	
	private void initComponents() {
		setLayout(null);
		setSize(792, 465);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("LOCACAR - Cadastrar novo cliente pessoa jurídica");
		getContentPane().setBackground(new Color(153, 180, 209));
		URL img = new PathFilesUtil().getIconTopo();
		Image icone = Toolkit.getDefaultToolkit().getImage(img);
		setIconImage(icone);
		
		progresso = new BarraProgresso();
		progresso.setBounds(265, 160, 245, 58);
		progresso.setVisible(false);
		
		JPanel dadosCliente = new JPanel();
		dadosCliente.setLayout(null);
		dadosCliente.setBounds(6, 1, 765, 320);
		dadosCliente.setBackground(new Color(153, 180, 209));
		dadosCliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DADOS DO CLIENTE", 4, Font.BOLD, null, Color.BLACK));
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 8, 80, 50);
		lblNome.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtNome = new JTextFieldHint(txtNome, "", "NOME DO CLIENTE");
		txtNome.setBounds(10, 42, 368, 20);
		txtNome.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtNome.setForeground(Color.BLUE);
		txtNome.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtNome.setDocument(new ActionCapslock());
		
		lblRazaoSocial = new JLabel("Razão Social");
		lblRazaoSocial.setBounds(10, 49, 80, 50);
		lblRazaoSocial.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtRazaoSocial = new JTextFieldHint(txtRazaoSocial, "", "RAZÃO SOCIAL");
		txtRazaoSocial.setBounds(10, 83, 368, 20);
		txtRazaoSocial.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtRazaoSocial.setForeground(Color.BLUE);
		txtRazaoSocial.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtRazaoSocial.setDocument(new ActionCapslock());
		
		rdbCpf = new JRadioButton("CPF");
		rdbCpf.setBounds(10, 122, 50, 20);
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
		rdbCnpj.setBounds(100, 122, 60, 20);
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
		txtCpfCnpj.setBounds(221, 122, 156, 20);
		txtCpfCnpj.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCpfCnpj.setForeground(Color.BLUE);
		txtCpfCnpj.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCpfCnpj.setFocusLostBehavior(JFormattedTextField.PERSIST);
		txtCpfCnpj.addFocusListener(new ActionBuscaEmpresa() {
			public void focusLost(FocusEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Buscando CNPJ...");
						if (rdbCnpj.isSelected())
							buscar(txtCpfCnpj, txtNome, txtRazaoSocial, txtEmail, txtEndereco, txtCep, txtCidade, txtBairro, jcbUf, txtTelCel, getRootPane());
						else
							txtTelCel.requestFocus();
						progresso.setVisible(false);
					}
				}.start();
			}
		});
		
		lblTelCel = new JLabel("Telefone / Celular");
		lblTelCel.setBounds(10, 131, 90, 50);
		lblTelCel.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtTelCel = new JFormattedTextField(Masks.mascaraTelCel());
		txtTelCel.setBounds(10, 163, 125, 20);
		txtTelCel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtTelCel.setForeground(Color.BLUE);
		txtTelCel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtTelCel.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		lblInscricaoEstadual = new JLabel("Inscrição Estadual");
		lblInscricaoEstadual.setBounds(221, 131, 90, 50);
		lblInscricaoEstadual.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtInscricaoEstadual = new JFormattedTextField(Masks.mascaraInscEstadual());
		txtInscricaoEstadual.setBounds(221, 163, 156, 20);
		txtInscricaoEstadual.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtInscricaoEstadual.setForeground(Color.BLUE);
		txtInscricaoEstadual.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtInscricaoEstadual.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 171, 80, 50);
		lblEmail.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtEmail = new JTextFieldHint(txtEmail, "", "E-MAIL DO CLIENTE");
		txtEmail.setBounds(10, 205, 368, 20);
		txtEmail.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtEmail.setForeground(Color.BLUE);
		txtEmail.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtEmail.setDocument(new ActionCapslock());
		
		lblNomeMotorista = new JLabel("Nome do Motorista");
		lblNomeMotorista.setBounds(10, 214, 90, 50);
		lblNomeMotorista.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtNomeMotorista = new JTextFieldHint(txtNomeMotorista, "", "NOME DO MOTORISTA DA EMPRESA");
		txtNomeMotorista.setBounds(10, 248, 368, 20);
		txtNomeMotorista.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtNomeMotorista.setForeground(Color.BLUE);
		txtNomeMotorista.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtNomeMotorista.setDocument(new ActionCapslock());
		
		lblNumCnh = new JLabel("Número da CNH");
		lblNumCnh.setBounds(10, 256, 100, 50);
		lblNumCnh.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtNumCnh = new JTextFieldHint(txtNumCnh, "", "NÚMERO CNH");
		txtNumCnh.setBounds(10, 289, 125, 20);
		txtNumCnh.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtNumCnh.setForeground(Color.BLUE);
		txtNumCnh.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtNumCnh.setDocument(new ActionLimiteCaracteres());
		txtNumCnh.addKeyListener(new ActionApenasNumeros() {
			public void keyTyped(KeyEvent e) {
				valoresNumericos(e);
			}
		});
		
		lblCategCnh = new JLabel("Categoria da CNH");
		lblCategCnh.setBounds(221, 256, 90, 50);
		lblCategCnh.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbCategCnh = new JComboBox<Object>();
		jcbCategCnh.setBounds(221, 289, 156, 20);
		jcbCategCnh.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbCategCnh.setForeground(Color.BLUE);
		jcbCategCnh.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbCategCnh.addItem("SELECIONE..");
		
		lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(385, 8, 80, 50);
		lblEndereco.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtEndereco = new JTextFieldHint(txtEndereco, "", "ENDEREÇO DO CLIENTE");
		txtEndereco.setBounds(385, 42, 368, 20);
		txtEndereco.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtEndereco.setForeground(Color.BLUE);
		txtEndereco.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtEndereco.setDocument(new ActionCapslock());
		
		lblCep = new JLabel("CEP");
		lblCep.setBounds(385, 49, 80, 50);
		lblCep.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtCep = new JFormattedTextField(Masks.mascaraCep());
		txtCep.setBounds(385, 83, 86, 20);
		txtCep.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCep.setForeground(Color.BLUE);
		txtCep.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCep.setFocusLostBehavior(JFormattedTextField.PERSIST);
		txtCep.addFocusListener(new ActionBuscaCep() {
			public void focusLost(FocusEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Buscando CEP...");
						buscar(txtCep, txtEndereco, txtBairro, txtCidade, jcbUf, txtComplemento, getRootPane());
						progresso.setVisible(false);
					}
				}.start();
			}
		});
		
		lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(478, 49, 80, 50);
		lblCidade.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtCidade = new JTextFieldHint(txtCidade, "", "CIDADE");
		txtCidade.setBounds(478, 83, 275, 20);
		txtCidade.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCidade.setForeground(Color.BLUE);
		txtCidade.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCidade.setDocument(new ActionCapslock());
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(385, 88, 80, 50);
		lblBairro.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtBairro = new JTextFieldHint(txtBairro, "", "BAIRRO");
		txtBairro.setBounds(385, 122, 297, 20);
		txtBairro.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtBairro.setForeground(Color.BLUE);
		txtBairro.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtBairro.setDocument(new ActionCapslock());
		
		lblUf = new JLabel("UF");
		lblUf.setBounds(690, 88, 80, 50);
		lblUf.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbUf = new JComboBox<Object>();
		jcbUf.setBounds(690, 122, 61, 20);
		jcbUf.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbUf.setForeground(Color.BLUE);
		jcbUf.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbUf.addItem("");
		
		lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(385, 131, 80, 50);
		lblComplemento.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtComplemento = new JTextFieldHint(txtComplemento, "", "COMPLEMENTO DO ENDEREÇO");
		txtComplemento.setBounds(385, 163, 368, 20);
		txtComplemento.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtComplemento.setForeground(Color.BLUE);
		txtComplemento.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtComplemento.setDocument(new ActionCapslock());
		
		lblAux = new JLabel("");
		lblAux.setBounds(635, 302, 200, 50);
		lblAux.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		
		lblCodigo = new JLabel("");
		lblCodigo.setBounds(740, 302, 200, 50);
		lblCodigo.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		
		btnSalvar = new PersonalizaButtons("imgToSave.png", "imgToSaveOver.png", "Salvar", "Salvar");
		btnSalvar.setBounds(655, 355, 50, 70);
		btnSalvar.addActionListener(new ActionSalvarAlterarClientePJ() {
			public void actionPerformed(ActionEvent e) {
				codigo = lblCodigo.getText();
				nome = txtNome.getText();
				razaoSocial = txtRazaoSocial.getText();
				cpfCnpj = txtCpfCnpj.getText();
				contato = txtTelCel.getText();
				inscricaoEstadual = txtInscricaoEstadual.getText();
				endereco = txtEndereco.getText();
				cep = txtCep.getText();
				cidade = txtCidade.getText();
				uf = jcbUf.getSelectedItem().toString();
				bairro = txtBairro.getText();
				complemento = txtComplemento.getText();
				email = txtEmail.getText();
				nomeMotorista = txtNomeMotorista.getText();
				numeroCnh = txtNumCnh.getText();
				categCnh = jcbCategCnh.getSelectedItem().toString();
				if (validarCampos(nome, razaoSocial, rdbCpf, rdbCnpj, cpfCnpj, contato, inscricaoEstadual, endereco, cep, cidade, uf, bairro, complemento, email, nomeMotorista, numeroCnh, categCnh, getRootPane()))
					if (codigo.equals("")) {
						new Thread() {
							public void run() {
								progresso.setVisible(true);
								progresso.setString("Salvando cliente no banco de dados...");
								salvar(codigo, nome, razaoSocial, cpfCnpj, contato, inscricaoEstadual, endereco, cep, cidade, uf, bairro, complemento, email, nomeMotorista, numeroCnh, categCnh, getRootPane(), Opcoes.SALVAR);
								progresso.setVisible(false);
							}
						}.start();
					} else {
						new Thread() {
							public void run() {
								progresso.setVisible(true);
								progresso.setString("Alterando cliente no banco de dados...");
								salvar(codigo, nome, razaoSocial, cpfCnpj, contato, inscricaoEstadual, endereco, cep, cidade, uf, bairro, complemento, email, nomeMotorista, numeroCnh, categCnh, getRootPane(), Opcoes.ALTERAR);
								progresso.setVisible(false);
							}
						}.start();
					}
			}
		});
		
		btnFechar = new PersonalizaButtons("imgClose.png", "imgClose.png", "Fechar Janela", "Fechar");
		btnFechar.setBounds(715, 355, 50, 70);
		btnFechar.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); } });
		
		add(progresso);
		dadosCliente.add(lblNome);
		dadosCliente.add(txtNome);
		dadosCliente.add(lblRazaoSocial);
		dadosCliente.add(txtRazaoSocial);
		dadosCliente.add(rdbCpf);
		dadosCliente.add(rdbCnpj);
		dadosCliente.add(txtCpfCnpj);
		dadosCliente.add(lblTelCel);
		dadosCliente.add(txtTelCel);
		dadosCliente.add(lblInscricaoEstadual);
		dadosCliente.add(txtInscricaoEstadual);
		dadosCliente.add(lblEmail);
		dadosCliente.add(txtEmail);
		dadosCliente.add(lblNomeMotorista);
		dadosCliente.add(txtNomeMotorista);
		dadosCliente.add(lblNumCnh);
		dadosCliente.add(txtNumCnh);
		dadosCliente.add(lblCategCnh);
		dadosCliente.add(jcbCategCnh);
		dadosCliente.add(lblEndereco);
		dadosCliente.add(txtEndereco);
		dadosCliente.add(lblCep);
		dadosCliente.add(txtCep);
		dadosCliente.add(lblCidade);
		dadosCliente.add(txtCidade);
		dadosCliente.add(lblBairro);
		dadosCliente.add(txtBairro);
		dadosCliente.add(lblUf);
		dadosCliente.add(jcbUf);
		dadosCliente.add(lblComplemento);
		dadosCliente.add(txtComplemento);
		add(dadosCliente);
		add(lblAux);
		add(lblCodigo);
		add(btnSalvar);
		add(btnFechar);
		addWindowListener(new ActionCarregarDadosClientesPJ() {
			public void windowOpened(WindowEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Iniciando... Aguarde!");
						PopularCombos.popularComboCategCnh(jcbCategCnh);
						PopularCombos.popularComboUF(jcbUf);
						carregarDadosClientes(lblCodigo, txtNome, txtRazaoSocial, rdbCpf, rdbCnpj, txtCpfCnpj, txtTelCel, txtInscricaoEstadual, txtEndereco, txtCep, txtCidade, jcbUf, txtBairro, txtComplemento, txtEmail, txtNomeMotorista, txtNumCnh, jcbCategCnh);
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
		txtNome.setText("");
		txtRazaoSocial.setText("");
		txtCpfCnpj.setText("");
		txtTelCel.setText("");
		txtInscricaoEstadual.setText("");
		txtEndereco.setText("");
		txtCep.setText("");
		txtCidade.setText("");
		jcbUf.setSelectedItem("");
		txtBairro.setText("");
		txtComplemento.setText("");
		txtEmail.setText("");
		txtNomeMotorista.setText("");
		txtNumCnh.setText("");
		jcbCategCnh.setSelectedItem("SELECIONE..");
		txtNome.requestFocus();
	}
}