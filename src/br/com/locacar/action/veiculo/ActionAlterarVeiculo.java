package br.com.locacar.action.veiculo;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.view.veiculo.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela alteração dos veículos no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionAlterarVeiculo implements ActionListener {
	private JRootPane rootPaneFrmVei;
	
	public void alterar(JTable jTabDadosVeiculo, JRootPane rootPane) {
		int linha = jTabDadosVeiculo.getSelectedRow();
		if (linha != -1) {
			String codigo = String.valueOf(jTabDadosVeiculo.getValueAt(jTabDadosVeiculo.getSelectedRow(), 0));
			FrmVeiculos frmVeiculos = new FrmVeiculos();
			rootPaneFrmVei = frmVeiculos.getRootPane();
			frmVeiculos.setTitle("LOCACAR - Alterar Veículo");
			frmVeiculos.btnSalvar.setText("Alterar");
			frmVeiculos.btnSalvar.setIcon(PathFilesUtil.getImg("imgAlter.png"));
			frmVeiculos.btnSalvar.setRolloverIcon(PathFilesUtil.getImg("imgAlterOver.png"));
			frmVeiculos.btnSalvar.setToolTipText("Alterar");
			FrmVeiculos.lblAux.setText("ID DO VEÍCULO: ");
			FrmVeiculos.lblCodigo.setText(codigo);
			FrmVeiculos.txtPlaca.requestFocus();
			frmVeiculos.setVisible(true);
			ActionConsultarVeiculo.consultar(FrmConsultarVeiculos.txtPlaca, jTabDadosVeiculo, FrmConsultarVeiculos.btnAlterar, FrmConsultarVeiculos.btnExcluir, rootPaneFrmVei);
		} else {
			Modal.mensagem(new MensagensModel(Bundle.getString("consGridAlt"), nivelMensagens.ERRO), rootPane);
			FrmConsultarVeiculos.txtPlaca.requestFocus();
		}
	}
}