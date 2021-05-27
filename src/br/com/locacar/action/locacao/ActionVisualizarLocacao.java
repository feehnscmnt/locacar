package br.com.locacar.action.locacao;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.view.locacao.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action para visualização das informações da locação!
 * @author Felipe Nascimento
 */
public abstract class ActionVisualizarLocacao implements ActionListener {
	private JRootPane rootPaneFrmLoc;
	
	public void visualizar(JTable jTabDadosLocacao, JRootPane rootPane) {
		int linha = jTabDadosLocacao.getSelectedRow();
		if (linha != -1) {
			String codigo = String.valueOf(jTabDadosLocacao.getValueAt(jTabDadosLocacao.getSelectedRow(), 0));
			FrmLocacao frmLocacao = new FrmLocacao();
			rootPaneFrmLoc = frmLocacao.getRootPane();
			frmLocacao.setTitle("LOCACAR - Visualizar Locação");
			frmLocacao.btnSalvar.setVisible(false);
			FrmLocacao.lblAux.setText("ID DA LOCAÇÃO: ");
			FrmLocacao.lblCodigo.setText(codigo);
			FrmLocacao.rdbCpf.setEnabled(false);
			FrmLocacao.rdbCnpj.setEnabled(false);
			FrmLocacao.txtCpfCnpj.setEditable(false);
			frmLocacao.btnBuscarCliente.setEnabled(false);
			FrmLocacao.rdbClientePf.setEnabled(false);
			FrmLocacao.rdbClientePj.setEnabled(false);
			FrmLocacao.rdbVeiculoCadastrado.setEnabled(false);
			FrmLocacao.rdbVeiculoNaoCadastrado.setEnabled(false);
			FrmLocacao.txtPlaca.setEditable(false);
			frmLocacao.btnBuscarVeiculo.setEnabled(false);
			FrmLocacao.jdcDataLocacao.setEnabled(false);
			FrmLocacao.jcbHoraLocacao.setEnabled(false);
			FrmLocacao.jdcDataRetornoLocacao.setEnabled(false);
			FrmLocacao.jcbHoraRetornoLocacao.setEnabled(false);
			FrmLocacao.txtKilometragem.setEditable(false);
			FrmLocacao.txtObservacao.setEditable(false);
			FrmLocacao.txtLocalEntrega.setEditable(false);
			FrmLocacao.txtValorDia.setEditable(false);
			FrmLocacao.txtValorProtecao.setEditable(false);
			FrmLocacao.txtValorTaxaServico.setEditable(false);
			frmLocacao.setVisible(true);
			ActionConsultarLocacao.consultar(FrmConsultarLocacao.txtNumeroLocacao, jTabDadosLocacao, FrmConsultarLocacao.btnVisualizar, FrmConsultarLocacao.btnDarBaixa, rootPaneFrmLoc);
		} else {
			Modal.mensagem(new MensagensModel(Bundle.getString("consGridVisual"), nivelMensagens.ERRO), rootPane);
			FrmConsultarLocacao.txtNumeroLocacao.requestFocus();
		}
	}
}