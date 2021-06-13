package br.com.locacar.view;

import br.com.locacar.view.componentes.*;
import br.com.locacar.view.locacao.*;
import br.com.locacar.view.usuario.*;
import br.com.locacar.view.veiculo.*;
import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.action.*;
import br.com.locacar.domain.*;
import br.com.locacar.enums.*;
import br.com.locacar.util.*;
import javax.swing.border.*;
import javax.swing.Timer;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.awt.*;

/**
 * View principal!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class FrmPrincipal extends JFrame {
	private static final Logger LOG = LogManager.getLogger(FrmPrincipal.class.getName());
	public static JButton btnNovaLocacao, btnConsultarLocacao, btnNovoCliente, btnPesquisarCliente, btnLogoff, btnNovoVeiculo, btnPesquisarVeiculo, btnNovoUsuario, btnPesquisarUsuario, btnFerramentas;
	private JLabel lblUsuarioLogado, lblDiaSemana, lblData, lblHora, lblInfosSistema, lblLocacar;
	private Background background;
	private JPanel barraInferior;
	private Calendar cal;
	
	public FrmPrincipal() {
		initComponents();
		getInfosSystem();
		getDayOfWeek();
		initClock();
		getDate();
		noMove();
	}
	
	private void initComponents() {
		URL img = new PathFilesUtil().getIconTopo();
		Image icone = Toolkit.getDefaultToolkit().getImage(img);
		setIconImage(icone);
		setResizable(false);
        setUndecorated(true);
        
        background = new Background(PathFilesUtil.getImage().concat("/images/imgBackground.jpg"));
        
        barraInferior = new JPanel();
        barraInferior.setBackground(new Color(120, 180, 209));
        barraInferior.setBorder(new BevelBorder(BevelBorder.RAISED));
        barraInferior.setPreferredSize(new Dimension(this.getWidth(), 26));
        barraInferior.setLayout(new BoxLayout(barraInferior, BoxLayout.X_AXIS));
        
        lblUsuarioLogado = new JLabel("OLÁ SR(A) ".concat(FrmLogin.usuarioLogado).concat(", ").concat(getGreeting()));
        lblUsuarioLogado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        lblDiaSemana = new JLabel();
        lblDiaSemana.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        lblData = new JLabel();
        lblData.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        lblHora = new JLabel();
        lblHora.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        lblInfosSistema = new JLabel();
        lblInfosSistema.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        lblLocacar = new JLabel("LOCACAR - SISTEMA PARA LOCAÇÃO DE VEÍCULOS");
        lblLocacar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        btnNovaLocacao = new PersonalizaButtons("imgNewLocation.png", "imgNewLocation.png", "Realizar nova locação!", "Nova Locação");
        btnNovaLocacao.setBounds(0, 0, 80, 70);
        btnNovaLocacao.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { new FrmLocacao().setVisible(true); } });
        
        btnConsultarLocacao = new PersonalizaButtons("imgSearchRent.png", "imgSearchRent.png", "Realizar consulta de locação!", "Consultar Locação");
        btnConsultarLocacao.setBounds(90, 0, 110, 70);
        btnConsultarLocacao.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { new FrmConsultarLocacao().setVisible(true); } });
        
        btnNovoCliente = new PersonalizaButtons("imgNewClient.png", "imgNewClient.png", "Cadastrar novo cliente!", "Novo Cliente");
        btnNovoCliente.setBounds(210, 0, 80, 70);
        btnNovoCliente.addActionListener(new ActionSelecionaCliente() { public void actionPerformed(ActionEvent e) { selecionar(FrmPrincipal.this, Opcoes.VIEW_CADASTRO, getRootPane()); } });
        
        btnPesquisarCliente = new PersonalizaButtons("imgSearchClient.png", "imgSearchClient.png", "Pesquisar cliente!", "Pesquisar Cliente");
        btnPesquisarCliente.setBounds(300, 0, 110, 70);
        btnPesquisarCliente.addActionListener(new ActionSelecionaCliente() { public void actionPerformed(ActionEvent e) { selecionar(FrmPrincipal.this, Opcoes.VIEW_CONSULTA, getRootPane()); } });
        
        btnNovoVeiculo = new PersonalizaButtons("imgNewVehicle.png", "imgNewVehicle.png", "Cadastrar novo veículo!", "Novo Veículo");
        btnNovoVeiculo.setBounds(420, 0, 110, 70);
        btnNovoVeiculo.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { new FrmVeiculos().setVisible(true); } });
        
        btnPesquisarVeiculo = new PersonalizaButtons("imgSearchVehicle.png", "imgSearchVehicle.png", "Pesquisar veículo!", "Pesquisar Veículo");
        btnPesquisarVeiculo.setBounds(540, 0, 110, 70);
        btnPesquisarVeiculo.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { new FrmConsultarVeiculos().setVisible(true); } });
        
        btnNovoUsuario = new PersonalizaButtons("imgUser.png", "imgUserOver.png", "Cadastrar novo usuário!", "Novo Usuário");
        btnNovoUsuario.setBounds(660, 0, 110, 70);
        btnNovoUsuario.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { new FrmUsuarios().setVisible(true); } });
        
        btnPesquisarUsuario = new PersonalizaButtons("imgSearchUser.png", "imgSearchUser.png", "Pesquisar usuário!", "Pesquisar Usuário");
        btnPesquisarUsuario.setBounds(780, 0, 110, 70);
        btnPesquisarUsuario.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { new FrmConsultarUsuarios().setVisible(true); } });
        
        btnFerramentas = new PersonalizaButtons("imgTools.png", "imgToolsOver.png", "Ferramentas!", "Ferramentas");
        btnFerramentas.setBounds(900, 0, 110, 70);
        
        btnLogoff = new PersonalizaButtons("imgLogoff.png", "imgLogoffOver.png", "Logoff!", "Logoff");
        btnLogoff.setBounds(1316, 0, 50, 70);
        btnLogoff.addActionListener(new ActionLogoff() { public void actionPerformed(ActionEvent e) { logoff(FrmPrincipal.this, getRootPane()); } });
		
        background.add(btnNovaLocacao);
        background.add(btnConsultarLocacao);
        background.add(btnNovoCliente);
        background.add(btnPesquisarCliente);
        background.add(btnNovoVeiculo);
        background.add(btnPesquisarVeiculo);
        background.add(btnNovoUsuario);
        background.add(btnPesquisarUsuario);
        background.add(btnFerramentas);
        background.add(btnLogoff);
		add(background);
		
		add(barraInferior, BorderLayout.SOUTH);
		barraInferior.add(lblUsuarioLogado);
		barraInferior.add(lblDiaSemana);
		barraInferior.add(lblData);
		barraInferior.add(lblHora);
		barraInferior.add(lblInfosSistema);
		barraInferior.add(lblLocacar);
		
		LOG.info("View principal iniciado com sucesso!");
	}
	
	private void noMove() {
		Toolkit t = Toolkit.getDefaultToolkit();
        Dimension dim = t.getScreenSize();
        this.addComponentListener(new ComponentAdapter() {
        	public void componentMoved(ComponentEvent e) {
        		setEnabled(false);
        		setEnabled(true);
        	}
    	});
        this.setSize((dim.width), (dim.height - 28));
	}
	
	private String getGreeting() {
		String saudacao = "";
		cal = new GregorianCalendar();
		int hora = cal.get(Calendar.HOUR_OF_DAY);
		if (hora >= 00 & hora <= 12)
			saudacao = "BOM DIA! | ";
		else if (hora >= 13 & hora <= 17)
			saudacao = "BOA TARDE! | ";
		else if (hora >= 18 & hora <= 23)
			saudacao = "BOA NOITE! | ";
		return saudacao;
	}
	
	private void getDayOfWeek() {
		cal = new GregorianCalendar();
		int diaSemana = cal.get(Calendar.DAY_OF_WEEK);
		switch (diaSemana) {
			case Calendar.SUNDAY:
				lblDiaSemana.setText("DOMINGO | ");
				break;
			case Calendar.MONDAY:
				lblDiaSemana.setText("SEGUNDA-FEIRA | ");
				break;
			case Calendar.TUESDAY:
				lblDiaSemana.setText("TERÇA-FEIRA | ");
				break;
			case Calendar.WEDNESDAY:
				lblDiaSemana.setText("QUARTA-FEIRA | ");
				break;
			case Calendar.THURSDAY:
				lblDiaSemana.setText("QUINTA-FEIRA | ");
				break;
			case Calendar.FRIDAY:
				lblDiaSemana.setText("SEXTA-FEIRA | ");
				break;
			case Calendar.SATURDAY:
				lblDiaSemana.setText("SÁBADO | ");
				break;
		}
	}
	
	private void getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy | ");
		lblData.setText(sdf.format(new Date()));
	}
	
	private void initClock() {
		Timer time = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DecimalFormat df = new DecimalFormat("00");
				cal = Calendar.getInstance();
				int h = cal.get(Calendar.HOUR_OF_DAY);
				int m = cal.get(Calendar.MINUTE);
				int s = cal.get(Calendar.SECOND);
				int pmAm = cal.get(Calendar.AM_PM);
				lblHora.setText(df.format(h).concat(":").concat(df.format(m)).concat(":").concat(df.format(s)).concat("").concat((pmAm == Calendar.AM ? "-AM | " : "-PM | ")));
			}
		});
		time.start();
	}
	
	private void getInfosSystem() {
		try {
			Properties props = System.getProperties();
			String hostname = InetAddress.getLocalHost().getHostName();
			String ip = InetAddress.getLocalHost().getHostAddress();
			lblInfosSistema.setText("HOSTNAME: ".concat(hostname.toUpperCase()).concat(" | IP: ").concat(ip).concat(" | SISTEMA: ").concat(props.getProperty("os.name").toUpperCase()).concat(" | VERSÃO JAVA: ").concat(getJavaVersion()).concat(" | "));
		} catch(Exception e) {
			LOG.info("Não foi possível obter as informações do sistema! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("infosSistema ").concat(e.getMessage().toUpperCase())).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private String getJavaVersion() {
		Package p = Package.getPackage("java.lang");
		return (p != null ? p.getImplementationVersion() : null);
	}
}