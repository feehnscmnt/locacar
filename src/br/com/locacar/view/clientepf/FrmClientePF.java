package br.com.locacar.view.clientepf;

import br.com.locacar.action.componentes.*;
import br.com.locacar.view.componentes.*;
import br.com.locacar.action.clientepf.*;
import br.com.locacar.view.opcao.*;
import org.apache.logging.log4j.*;
import br.com.locacar.action.*;
import br.com.locacar.domain.*;
import com.toedter.calendar.*;
import br.com.locacar.util.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.*;
import java.beans.*;
import java.text.*;
import java.awt.*;
import java.net.*;
import java.io.*;

/**
 * View de cadastro e alteração de clientes pessoa física!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmClientePF extends JDialog {
	private static final Logger LOG = LogManager.getLogger(FrmClientePF.class.getName());
	public static JLabel lblNome, lblCpf, lblRg, lblTelCel, lblDataNasc, lblCategCnh, lblNumCnh, lblEmail, lblEndereco, lblCep, lblCidade, lblUf, lblBairro, lblComplemento, lblAux, lblCodigo;
	private String codigo, nome, cpf, rg, contato, dataNasc, categCnh, numeroCnh, email, endereco, cep, cidade, uf, bairro, complemento, imagem;
	public static JTextField txtNome, txtEmail, txtEndereco, txtCidade, txtBairro, txtComplemento, txtNumCnh;
	public JButton btnCapturarImagem, btnProcurarImagem, btnSalvar, btnFechar;
	public static JFormattedTextField txtCpf, txtRg, txtTelCel, txtCep;
	public static JComboBox<Object> jcbUf, jcbDispositivo, jcbCategCnh;
	private File imagemCapturada, imagemSelecionada;
	public static JDateChooser jdcDataNasc;
	public static JPanel pcbImagem;
	private JProgressBar progresso;
	
	public FrmClientePF() { LOG.info("View de cadastros e alterações dos clientes pessoa física iniciado com sucesso!"); setModal(true); initComponents(); noMove(); }
	
	private void initComponents() {
		setLayout(null);
		setSize(792, 437);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setTitle("LOCACAR - Cadastrar novo cliente pessoa física");
		getContentPane().setBackground(new Color(153, 180, 209));
		URL img = new PathFilesUtil().getIconTopo();
		Image icone = Toolkit.getDefaultToolkit().getImage(img);
		setIconImage(icone);
		
		progresso = new BarraProgresso();
		progresso.setBounds(270, 150, 245, 58);
		progresso.setVisible(false);
		
		JPanel dadosCliente = new JPanel();
		dadosCliente.setLayout(null);
		dadosCliente.setBounds(7, 1, 762, 225);
		dadosCliente.setBackground(new Color(153, 180, 209));
		dadosCliente.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "DADOS DO CLIENTE", 4, Font.BOLD, null, Color.BLACK));
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 10, 80, 50);
		lblNome.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtNome = new JTextFieldHint(txtNome, "", "NOME DO CLIENTE");
		txtNome.setBounds(10, 45, 368, 20);
		txtNome.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtNome.setForeground(Color.BLUE);
		txtNome.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtNome.setDocument(new ActionCapslock());
		
		lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 55, 80, 50);
		lblCpf.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtCpf = new JFormattedTextField(Masks.mascaraCpf());
		txtCpf.setBounds(10, 90, 123, 20);
		txtCpf.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCpf.setForeground(Color.BLUE);
		txtCpf.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCpf.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		lblRg = new JLabel("RG");
		lblRg.setBounds(140, 55, 80, 50);
		lblRg.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtRg = new JFormattedTextField(Masks.mascaraRg());
		txtRg.setBounds(140, 90, 105, 20);
		txtRg.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtRg.setForeground(Color.BLUE);
		txtRg.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtRg.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		lblTelCel = new JLabel("Telefone / Celular");
		lblTelCel.setBounds(253, 55, 90, 50);
		lblTelCel.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtTelCel = new JFormattedTextField(Masks.mascaraTelCel());
		txtTelCel.setBounds(253, 90, 125, 20);
		txtTelCel.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtTelCel.setForeground(Color.BLUE);
		txtTelCel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtTelCel.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		lblDataNasc = new JLabel("Data de Nascimento");
		lblDataNasc.setBounds(10, 100, 100, 50);
		lblDataNasc.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jdcDataNasc = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		((JTextFieldDateEditor) jdcDataNasc.getDateEditor()).setBackground(Color.WHITE);
		jdcDataNasc.setBounds(10, 138, 123, 20);
		jdcDataNasc.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jdcDataNasc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		lblCategCnh = new JLabel("Categoria da CNH");
		lblCategCnh.setBounds(140, 100, 100, 50);
		lblCategCnh.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbCategCnh = new JComboBox<Object>();
		jcbCategCnh.setBounds(140, 138, 105, 20);
		jcbCategCnh.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbCategCnh.setForeground(Color.BLUE);
		jcbCategCnh.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbCategCnh.addItem("SELECIONE..");
		
		lblNumCnh = new JLabel("Número da CNH");
		lblNumCnh.setBounds(253, 100, 100, 50);
		lblNumCnh.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtNumCnh = new JTextFieldHint(txtNumCnh, "", "NÚMERO CNH");
		txtNumCnh.setBounds(253, 138, 125, 20);
		txtNumCnh.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtNumCnh.setForeground(Color.BLUE);
		txtNumCnh.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtNumCnh.setDocument(new ActionLimiteCaracteres());
		txtNumCnh.addKeyListener(new ActionApenasNumeros() {
			public void keyTyped(KeyEvent e) {
				valoresNumericos(e);
			}
		});
		
		lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(10, 149, 80, 50);
		lblEmail.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtEmail = new JTextFieldHint(txtEmail, "", "E-MAIL DO CLIENTE");
		txtEmail.setBounds(10, 186, 368, 20);
		txtEmail.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtEmail.setForeground(Color.BLUE);
		txtEmail.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtEmail.setDocument(new ActionCapslock());
		
		lblEndereco = new JLabel("Endereço");
		lblEndereco.setBounds(385, 10, 80, 50);
		lblEndereco.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtEndereco = new JTextFieldHint(txtEndereco, "", "ENDEREÇO DO CLIENTE");
		txtEndereco.setBounds(385, 45, 368, 20);
		txtEndereco.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtEndereco.setForeground(Color.BLUE);
		txtEndereco.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtEndereco.setDocument(new ActionCapslock());
		
		lblCep = new JLabel("CEP");
		lblCep.setBounds(385, 55, 80, 50);
		lblCep.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtCep = new JFormattedTextField(Masks.mascaraCep());
		txtCep.setBounds(385, 90, 86, 20);
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
		lblCidade.setBounds(478, 55, 80, 50);
		lblCidade.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtCidade = new JTextFieldHint(txtCidade, "", "CIDADE");
		txtCidade.setBounds(478, 90, 275, 20);
		txtCidade.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtCidade.setForeground(Color.BLUE);
		txtCidade.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtCidade.setDocument(new ActionCapslock());
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(385, 100, 80, 50);
		lblBairro.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtBairro = new JTextFieldHint(txtBairro, "", "BAIRRO");
		txtBairro.setBounds(385, 138, 297, 20);
		txtBairro.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtBairro.setForeground(Color.BLUE);
		txtBairro.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtBairro.setDocument(new ActionCapslock());
		
		lblUf = new JLabel("UF");
		lblUf.setBounds(690, 100, 80, 50);
		lblUf.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		jcbUf = new JComboBox<Object>();
		jcbUf.setBounds(690, 138, 61, 20);
		jcbUf.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbUf.setForeground(Color.BLUE);
		jcbUf.setCursor(new Cursor(Cursor.HAND_CURSOR));
		jcbUf.addItem("");
		
		lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(385, 149, 80, 50);
		lblComplemento.setFont(new Font("Microsoft Sans Serif", Font.ITALIC, 10));
		
		txtComplemento = new JTextFieldHint(txtComplemento, "", "COMPLEMENTO DO ENDEREÇO");
		txtComplemento.setBounds(385, 186, 368, 20);
		txtComplemento.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		txtComplemento.setForeground(Color.BLUE);
		txtComplemento.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		txtComplemento.setDocument(new ActionCapslock());
		
		lblAux = new JLabel("");
		lblAux.setBounds(635, 210, 200, 50);
		lblAux.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		
		lblCodigo = new JLabel("");
		lblCodigo.setBounds(740, 210, 200, 50);
		lblCodigo.setFont(new Font("Microsoft Sans Serif", Font.BOLD + Font.ITALIC, 12));
		
		pcbImagem = new JPanel();
		pcbImagem.setBounds(10, 235, 160, 155);
		pcbImagem.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		pcbImagem.setLayout(new BorderLayout());
		pcbImagem.setBackground(Color.LIGHT_GRAY);
		
		jcbDispositivo = new JComboBox<Object>();
		jcbDispositivo.setBounds(180, 235, 233, 21);
		jcbDispositivo.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 14));
		jcbDispositivo.setForeground(Color.BLUE);
		jcbDispositivo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnCapturarImagem = new PersonalizaButtons("imgWebcam.png", "imgWebcamOver.png", "Capturar Imagem", "Capturar Imagem");
		btnCapturarImagem.setBounds(180, 285, 90, 70);
		btnCapturarImagem.addActionListener(new ActionCapturarImagem() {
			public void actionPerformed(ActionEvent e) {
				capturar(btnCapturarImagem, pcbImagem, jcbDispositivo, getRootPane());
			}
			public void propertyChange(PropertyChangeEvent evt) {}
		});
		
		btnProcurarImagem = new PersonalizaButtons("imgProcurarImagem.png", "imgProcurarImagem.png", "Procurar Imagem", "Procurar Imagem");
		btnProcurarImagem.setBounds(320, 285, 90, 70);
		btnProcurarImagem.addActionListener(new ActionProcurarImagem() {
			public void actionPerformed(ActionEvent e) {
				procurar(pcbImagem);
			}
		});
		
		btnSalvar = new PersonalizaButtons("imgToSave.png", "imgToSaveOver.png", "Salvar", "Salvar");
		btnSalvar.setBounds(650, 320, 50, 70);
		btnSalvar.addActionListener(new ActionSalvarAlterarClientePF() {
			public void actionPerformed(ActionEvent e) {
				codigo = lblCodigo.getText();
				nome = txtNome.getText();
				cpf = txtCpf.getText();
				rg = txtRg.getText();
				contato = txtTelCel.getText();
				getDataNasc();
				categCnh = jcbCategCnh.getSelectedItem().toString();
				numeroCnh = txtNumCnh.getText();
				email = txtEmail.getText();
				endereco = txtEndereco.getText();
				cep = txtCep.getText();
				cidade = txtCidade.getText();
				uf = jcbUf.getSelectedItem().toString();
				bairro = txtBairro.getText();
				complemento = txtComplemento.getText();
				getImagem();
				if (validarCampos(nome, cpf, rg, contato, getDataNasc(), categCnh, numeroCnh, email, endereco, cep, cidade, uf, bairro, complemento, getRootPane()))
					if (codigo.equals("")) {
						new Thread() {
							public void run() {
								progresso.setVisible(true);
								progresso.setString("Salvando cliente no banco de dados...");
								salvar(codigo, nome, cpf, rg, contato, getDataNasc(), categCnh, numeroCnh, email, endereco, cep, cidade, uf, bairro, complemento, getImagem(), getRootPane(), Opcoes.SALVAR);
								progresso.setVisible(false);
							}
						}.start();
					} else {
						new Thread() {
							public void run() {
								progresso.setVisible(true);
								progresso.setString("Alterando cliente no banco de dados...");
								salvar(codigo, nome, cpf, rg, contato, getDataNasc(), categCnh, numeroCnh, email, endereco, cep, cidade, uf, bairro, complemento, getImagem(), getRootPane(), Opcoes.ALTERAR);
								progresso.setVisible(false);
							}
						}.start();
					}
			}
		});
		
		btnFechar = new PersonalizaButtons("imgClose.png", "imgClose.png", "Fechar Janela", "Fechar");
		btnFechar.setBounds(710, 320, 50, 70);
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		add(progresso);
		dadosCliente.add(lblNome);
		dadosCliente.add(txtNome);
		dadosCliente.add(lblCpf);
		dadosCliente.add(txtCpf);
		dadosCliente.add(lblRg);
		dadosCliente.add(txtRg);
		dadosCliente.add(lblTelCel);
		dadosCliente.add(txtTelCel);
		dadosCliente.add(lblDataNasc);
		dadosCliente.add(jdcDataNasc);
		dadosCliente.add(lblCategCnh);
		dadosCliente.add(jcbCategCnh);
		dadosCliente.add(lblNumCnh);
		dadosCliente.add(txtNumCnh);
		dadosCliente.add(lblEmail);
		dadosCliente.add(txtEmail);
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
		add(pcbImagem);
		add(jcbDispositivo);
		add(btnCapturarImagem);
		add(btnProcurarImagem);
		add(btnSalvar);
		add(btnFechar);
		addWindowListener(new ActionCarregarDadosClientesPF() {
			public void windowOpened(WindowEvent e) {
				new Thread() {
					public void run() {
						progresso.setVisible(true);
						progresso.setString("Iniciando... Aguarde!");
						PopularCombos.popularComboCategCnh(jcbCategCnh);
						PopularCombos.popularComboUF(jcbUf);
						PopularCombos.popularComboDispositivos(jcbDispositivo);
						carregarDadosClientes(lblCodigo, txtNome, txtCpf, txtRg, txtTelCel, jdcDataNasc, jcbCategCnh, txtNumCnh, txtEmail, txtEndereco, txtCep, txtCidade, jcbUf, txtBairro, txtComplemento, pcbImagem);
						progresso.setVisible(false);
					}
				}.start();
			}
		});
	}
	
	public static void limparCampos() {
		txtNome.setText("");
		txtCpf.setText("");
		txtRg.setText("");
		txtTelCel.setText("");
		jdcDataNasc.setDate(null);
		jcbCategCnh.setSelectedItem("SELECIONE..");
		txtNumCnh.setText("");
		txtEmail.setText("");
		txtEndereco.setText("");
		txtCep.setText("");
		txtCidade.setText("");
		jcbUf.setSelectedItem("");
		txtBairro.setText("");
		txtComplemento.setText("");
		ActionCapturarImagem.limpaVisorImagem(pcbImagem);
		ActionProcurarImagem.limpaVisorImagem(pcbImagem);
		jcbDispositivo.setSelectedItem("SELECIONAR DISPOSITIVO");
		txtNome.requestFocus();
	}
	
	private String getDataNasc() {
		if (jdcDataNasc.getDate() != null)
			dataNasc = new SimpleDateFormat("dd/MM/yyyy").format(jdcDataNasc.getDate());
		else
			dataNasc = "";
		return dataNasc;
	}
	
	private String getImagem() {
		imagemCapturada = ActionCapturarImagem.imagem;
		imagemSelecionada = ActionProcurarImagem.imagem;
		if (imagemCapturada != null)
			imagem = String.valueOf(imagemCapturada.getName());
		else if (imagemSelecionada != null)
			imagem = String.valueOf(imagemSelecionada.getName());
		return imagem;
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