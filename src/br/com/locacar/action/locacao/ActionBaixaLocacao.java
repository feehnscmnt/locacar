package br.com.locacar.action.locacao;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.veiculo.*;
import br.com.locacar.model.locacao.*;
import br.com.locacar.view.locacao.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela realização da baixa das locações e disponibilização do veículo!
 * @author Felipe Nascimento
 */
public abstract class ActionBaixaLocacao implements ActionListener {
	public JRootPane rootPaneFrmLoc;
	
	public void baixa(JTable jTabDadosLocacao, JRootPane rootPane) {
		int linha = jTabDadosLocacao.getSelectedRow();
		if (linha != -1) {
			String placa = String.valueOf(jTabDadosLocacao.getValueAt(jTabDadosLocacao.getSelectedRow(), 9));
			String numeroLocacao = String.valueOf(jTabDadosLocacao.getValueAt(jTabDadosLocacao.getSelectedRow(), 2));
			int bai = JOptionPane.showConfirmDialog(null, new MensagensModel(Bundle.getString("baixaLocacao", placa)).getText(), "DAR BAIXA", JOptionPane.YES_NO_OPTION);
			if (bai == JOptionPane.YES_OPTION) {
				FrmLocacao frmLocacao = new FrmLocacao();
				Locacao locacoes = new DAOLocacao();
				rootPaneFrmLoc = frmLocacao.getRootPane();
				LocacaoModel locacao = new LocacaoModel();
				VeiculosModel veiculo = new VeiculosModel();
				locacao.setPlaca(placa);
				locacao.setDataLocacao("");
				locacao.setNumeroLocacao(numeroLocacao);
				locacao.setSituacao("FINALIZADA");
				veiculo.setSituacao("DISPONÍVEL");
				locacoes.veiculoDisponibilizado(veiculo, locacao);
				locacoes.finalizaLocacao(locacao);
				Modal.mensagem(new MensagensModel(Bundle.getString("baixaExito"), nivelMensagens.INFO), rootPane);
				ActionConsultarLocacao.consultar(FrmConsultarLocacao.txtNumeroLocacao, jTabDadosLocacao, FrmConsultarLocacao.btnVisualizar, FrmConsultarLocacao.btnDarBaixa, rootPane);
			} else {
				Modal.mensagem(new MensagensModel(Bundle.getString("baixaCancelada"), nivelMensagens.INFO), rootPane);
				FrmConsultarLocacao.txtNumeroLocacao.requestFocus();
			}
		} else {
			Modal.mensagem(new MensagensModel(Bundle.getString("consGridBai"), nivelMensagens.ERRO), rootPane);
			FrmConsultarLocacao.txtNumeroLocacao.requestFocus();
		}
	}
}