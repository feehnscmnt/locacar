package br.com.locacar.action.veiculo;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.veiculo.*;
import br.com.locacar.view.veiculo.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.enums.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Classe action responsável pela validação dos campos e pela inclusão do novo veículo no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionSalvarAlterarVeiculo implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionSalvarAlterarVeiculo.class.getName());
	
	public boolean validarCampos(String placa, String marca, String modelo, String versao, String anoFab, String anoMod, String cor, String combustivel, String transmissao, String renavam, String portas, JRootPane rootPane) {
		if (placa.replaceAll("\\D", "").equals("") & marca.equals("SELECIONE..") & modelo.equals("SELECIONE..") & versao.equals("") & anoFab.equals("") &
			anoMod.equals("") & cor.equals("") & combustivel.equals("") & transmissao.equals("") & renavam.equals("") & portas.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("camposObrigatorios"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.txtPlaca.requestFocus();
			return false;
		} else if (placa.replaceAll("\\D", "").equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoPlaca"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.txtPlaca.requestFocus();
			return false;
		} else if (marca.equals("SELECIONE..")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("comboMarca"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.jcbMarca.requestFocus();
			return false;
		} else if (modelo.equals("SELECIONE..")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("comboModelo"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.jcbModelo.requestFocus();
			return false;
		} else if (versao.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoVersao"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.txtVersao.requestFocus();
			return false;
		} else if (anoFab.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoAnoFab"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.txtAnoFabricacao.requestFocus();
			return false;
		} else if (anoMod.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoAnoMod"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.txtAnoModelo.requestFocus();
			return false;
		} else if (cor.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCor"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.txtCor.requestFocus();
			return false;
		} else if (combustivel.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoCombustivel"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.txtCombustivel.requestFocus();
			return false;
		} else if (transmissao.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoTransmissao"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.txtTransmissao.requestFocus();
			return false;
		} else if (renavam.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoRenavam"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.txtRenavam.requestFocus();
			return false;
		} else if (portas.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoPortas"), nivelMensagens.ERRO), rootPane);
			FrmVeiculos.txtQtdePortas.requestFocus();
			return false;
		}
		return true;
	}
	
	public void salvar(String codigo, String placa, String marca, String modelo, String versao, String anoFab, String anoMod, String cor, String combustivel, String transmissao, String renavam, String portas, String alarme, String travas, String sensorRe, String bancosCouro, String freiosAbs, String airbags, String cameraRe, String multimidia, String bancosRegulaveis, String vidrosEletricos, String direcaoHidraulica, String direcaoEletrica, String situacao, JRootPane rootPane, Opcoes opcao) {
		Veiculos veiculos = new DAOVeiculos();
		VeiculosModel veiculo = new VeiculosModel();
		if (codigo != "") {
			veiculo.setCod(codigo);
		}
		veiculo.setPlaca(placa);
		veiculo.setMarca(marca);
		veiculo.setModelo(modelo);
		veiculo.setVersao(versao);
		veiculo.setAnoFab(anoFab);
		veiculo.setAnoMod(anoMod);
		veiculo.setCor(cor);
		veiculo.setCombustivel(combustivel);
		veiculo.setTransmissao(transmissao);
		veiculo.setRenavam(renavam);
		veiculo.setPortas(portas);
		veiculo.setAlarme(alarme);
		veiculo.setTravas(travas);
		veiculo.setSensorRe(sensorRe);
		veiculo.setBancosCouro(bancosCouro);
		veiculo.setFreiosAbs(freiosAbs);
		veiculo.setAirbags(airbags);
		veiculo.setCameraRe(cameraRe);
		veiculo.setMultimidia(multimidia);
		veiculo.setBancosRegulaveis(bancosRegulaveis);
		veiculo.setVidrosEletricos(vidrosEletricos);
		veiculo.setDirecaoHidraulica(direcaoHidraulica);
		veiculo.setDirecaoEletrica(direcaoEletrica);
		veiculo.setSituacao(situacao);
		if (opcao.equals(Opcoes.SALVAR)) {
			try {
				ResultSet rs = veiculos.verificarSeExiste(placa);
				if (rs.next()) {
					Modal.mensagem(new MensagensModel(Bundle.getString("veiculoExiste", placa), nivelMensagens.ERRO), rootPane);
					return;
				} else {
					veiculos.salvar(veiculo);
					LOG.info("Novo veículo salvo com sucesso!");
					Modal.mensagem(new MensagensModel(Bundle.getString("veicCadExito"), nivelMensagens.INFO), rootPane);
					FrmVeiculos.limparCampos();
				}
			} catch(SQLException e) {
				LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
			}
		} else if (opcao.equals(Opcoes.ALTERAR)) {
			veiculos.alterar(veiculo);
			LOG.info("O veículo {} foi alterado com sucesso!", veiculo.getModelo());
			Modal.mensagem(new MensagensModel(Bundle.getString("veicAltExito", modelo), nivelMensagens.INFO), rootPane);
		}
	}
}