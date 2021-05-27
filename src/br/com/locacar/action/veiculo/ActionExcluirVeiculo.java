package br.com.locacar.action.veiculo;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.veiculo.*;
import br.com.locacar.view.veiculo.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela exclusão dos veículos no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionExcluirVeiculo implements ActionListener {
	private JRootPane rootPaneFrmVei;
	
	public void excluir(JTable jTabDadosVeiculo, JRootPane rootPane) {
		int linha = jTabDadosVeiculo.getSelectedRow();
		if (linha != -1) {
			String placa = String.valueOf(jTabDadosVeiculo.getValueAt(jTabDadosVeiculo.getSelectedRow(), 1));
			int exc = JOptionPane.showConfirmDialog(null, new MensagensModel(Bundle.getString("veicExcluir", placa)).getText(), "EXCLUIR", JOptionPane.YES_NO_OPTION);
			if (exc == JOptionPane.YES_OPTION) {
				Veiculos veiculos = new DAOVeiculos();
				FrmVeiculos frmVeiculos = new FrmVeiculos();
				rootPaneFrmVei = frmVeiculos.getRootPane();
				VeiculosModel veiculo = new VeiculosModel();
				veiculo.setPlaca(placa);
				veiculos.excluir(veiculo);
				Modal.mensagem(new MensagensModel(Bundle.getString("veicExcluido"), nivelMensagens.INFO), rootPane);
				ActionConsultarVeiculo.consultar(FrmConsultarVeiculos.txtPlaca, jTabDadosVeiculo, FrmConsultarVeiculos.btnAlterar, FrmConsultarVeiculos.btnExcluir, rootPaneFrmVei);
			} else {
				Modal.mensagem(new MensagensModel(Bundle.getString("exclusaoCancelada"), nivelMensagens.INFO), rootPane);
				FrmConsultarVeiculos.txtPlaca.requestFocus();
			}
		} else {
			Modal.mensagem(new MensagensModel(Bundle.getString("consGridExc"), nivelMensagens.ERRO), rootPane);
			FrmConsultarVeiculos.txtPlaca.requestFocus();
		}
	}
}