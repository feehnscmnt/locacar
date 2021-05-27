package br.com.locacar.action.veiculo;

import br.com.locacar.model.veiculo.*;
import br.com.locacar.view.veiculo.*;
import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Classe responsável por carregar do banco de dados as informações dos veículos para alterações!
 * @author Felipe Nascimento
 */
public abstract class ActionCarregarDadosVeiculos extends WindowAdapter {
	private static final Logger LOG = LogManager.getLogger(ActionCarregarDadosVeiculos.class.getName());
	
	public void carregaDadosVeiculos(JLabel codigo, JTextField situacao, JTextField dataLocacao, JFormattedTextField placa, JComboBox<Object> marca, JComboBox<Object> modelo, JTextField versao, JTextField anoFab, JTextField anoMod, JTextField cor, JTextField combustivel, JTextField transmissao, JTextField renavam, JTextField portas, JCheckBox alarme, JCheckBox travas, JCheckBox sensorRe, JCheckBox bancosCouro, JCheckBox freiosAbs, JCheckBox airbags, JCheckBox cameraRe, JCheckBox multimidia, JCheckBox bancosRegulaveis, JCheckBox vidrosEletricos, JCheckBox direcaoHidraulica, JCheckBox direcaoEletrica) {
		LOG.info("Carregando os dados dos veículos para alterações...");
		if (codigo.getText() != "") {
			Veiculos veiculos = new DAOVeiculos();
			ResultSet rs = veiculos.buscarParaAlteracao(codigo.getText());
			try {
				if (rs.next()) {
					if (rs.getString("SITUACAO").equals("DISPONÍVEL")) {
						situacao.setForeground(Color.BLUE);
						situacao.setText(rs.getString("SITUACAO"));
					} else if (rs.getString("SITUACAO").equals("ALUGADO")) {
						situacao.setForeground(Color.RED);
						situacao.setText(rs.getString("SITUACAO"));
					}
					if (!rs.getString("DATALOCACAO").isEmpty()) {
						FrmVeiculos.lblDataLocacao.setVisible(true);
						dataLocacao.setVisible(true);
						dataLocacao.setForeground(Color.RED);
						dataLocacao.setText(rs.getString("DATALOCACAO"));
					}
					placa.setText(rs.getString("PLACA"));
					marca.setSelectedItem(rs.getString("MARCA"));
					modelo.setSelectedItem(rs.getString("MODELO"));
					versao.setText(rs.getString("VERSAO"));
					anoFab.setText(rs.getString("ANOFABRICACAO"));
					anoMod.setText(rs.getString("ANOMODELO"));
					cor.setText(rs.getString("COR"));
					combustivel.setText(rs.getString("COMBUSTIVEL"));
					transmissao.setText(rs.getString("TRANSMISSAO"));
					renavam.setText(rs.getString("RENAVAM"));
					portas.setText(rs.getString("PORTAS"));
					alarme.setSelected(rs.getString("ALARME").equals("SIM") ? true : false);
					travas.setSelected(rs.getString("TRAVAS").equals("SIM") ? true : false);
					sensorRe.setSelected(rs.getString("SENSORRE").equals("SIM") ? true : false);
					bancosCouro.setSelected(rs.getString("BANCOSCOURO").equals("SIM") ? true : false);
					freiosAbs.setSelected(rs.getString("FREIOSABS").equals("SIM") ? true : false);
					airbags.setSelected(rs.getString("AIRBAGS").equals("SIM") ? true : false);
					cameraRe.setSelected(rs.getString("CAMERARE").equals("SIM") ? true : false);
					multimidia.setSelected(rs.getString("MULTIMIDIA").equals("SIM") ? true : false);
					bancosRegulaveis.setSelected(rs.getString("BANCOSREGULAVEIS").equals("SIM") ? true : false);
					vidrosEletricos.setSelected(rs.getString("VIDROSELETRICOS").equals("SIM") ? true : false);
					direcaoHidraulica.setSelected(rs.getString("DIRECAOHIDRAULICA").equals("SIM") ? true : false);
					direcaoEletrica.setSelected(rs.getString("DIRECAOELETRICA").equals("SIM") ? true : false);
				}
			} catch(SQLException e) {
				LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
			}
		}
		LOG.info("Dados carregados com sucesso!");
	}
}