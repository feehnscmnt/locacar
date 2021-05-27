package br.com.locacar.action.locacao;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.veiculo.*;
import br.com.locacar.view.locacao.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Classe action responsável pela busca de veículos no banco de dados para realização da locação!
 * @author Felipe Nascimento
 */
public abstract class ActionBuscarVeiculo implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionBuscarVeiculo.class.getName());
	
	public boolean validaCampoPlaca(String placa, JRootPane rootPane) {
		if (placa.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoPlaca"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.txtPlaca.requestFocus();
			return false;
		}
		return true;
	}
	
	public boolean validaCampos(String placa, String marca, String modelo, String anoMod, String cor, String combustivel, String renavam, JRootPane rootPane) {
		if (placa.replaceAll("\\D", "").equals("") & marca.equals("SELECIONE..") & modelo.equals("SELECIONE..") & anoMod.equals("") & cor.equals("")
			& combustivel.equals("") & renavam.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("camposObrigatorios"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.txtPlaca.requestFocus();
			return false;
		} else if (placa.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoPlaca"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.txtPlaca.requestFocus();
			return false;
		} else if (marca.equals("SELECIONE..")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("comboMarca"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.jcbMarca.requestFocus();
			return false;
		} else if (modelo.equals("SELECIONE..")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("comboModelo"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.jcbModelo.requestFocus();
			return false;
		} else if (anoMod.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoAnoMod"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.txtAnoModelo.requestFocus();
			return false;
		} else if (cor.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCor"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.txtCor.requestFocus();
			return false;
		} else if (combustivel.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCombustivel"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.txtCombustivel.requestFocus();
			return false;
		} else if (renavam.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoRenavam"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.txtRenavam.requestFocus();
			return false;
		}
		return true;
	}
	
	public static void buscarVeiculo(JRadioButton rdbVeiculoCadastrado, JFormattedTextField txtPlaca, JComboBox<Object> jcbMarca, JComboBox<Object> jcbModelo, JTextField txtAnoModelo, JTextField txtCor, JTextField txtCombustivel, JTextField txtRenavam, JRootPane rootPane) {
		Veiculos veiculos = new DAOVeiculos();
		ResultSet rs = veiculos.buscarParaLocacao(txtPlaca.getText());
		try {
			if (rs.next()) {
				jcbMarca.setSelectedItem(rs.getString("MARCA"));
				jcbModelo.setSelectedItem(rs.getString("MODELO"));
				txtAnoModelo.setText(rs.getString("ANOMODELO"));
				txtCor.setText(rs.getString("COR"));
				txtCombustivel.setText(rs.getString("COMBUSTIVEL"));
				txtRenavam.setText(rs.getString("RENAVAM"));
				FrmLocacao.jdcDataLocacao.requestFocus();
			} else {
				LOG.info("O veículo da placa {} já está alugado ou não consta cadastrado no banco de dados!", txtPlaca.getText());
				Modal.mensagem(new MensagensModel(Bundle.getString("veicAlugado", txtPlaca.getText()), nivelMensagens.ERRO), rootPane);
				jcbMarca.setSelectedItem("SELECIONE..");
				jcbModelo.addItem("SELECIONE..");
				txtAnoModelo.setText("");
				txtCor.setText("");
				txtCombustivel.setText("");
				txtRenavam.setText("");
				txtPlaca.requestFocus();
				FrmLocacao.txtPlaca.requestFocus();
			}
		} catch(SQLException e) {
			LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
}