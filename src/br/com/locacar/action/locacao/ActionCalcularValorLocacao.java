package br.com.locacar.action.locacao;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;

/**
 * Classe action responsável pelo cálculo do valor da locação!
 * @author Felipe Nascimento
 */
public abstract class ActionCalcularValorLocacao implements ActionListener {
	
	public void calcular(JFormattedTextField txtValorDia, JTextField txtQtdeDias, JFormattedTextField txtValorTaxaServico, JFormattedTextField txtValorProtecao, JTextField txtValorTotal, JRootPane rootPane) {
		if (txtValorDia.getText().equals("") | txtQtdeDias.getText().equals("") | txtValorTaxaServico.getText().equals("") | txtValorProtecao.getText().equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("cadLocCamposCalcVazios"), nivelMensagens.ERRO), rootPane);
			txtValorDia.requestFocus();
		} else {
			DecimalFormat df = new DecimalFormat("#,##0.00");
			Float valorDia = Float.parseFloat(txtValorDia.getText().replaceAll("\\.", "").replace(",", "."));
			int qtdeDias = Integer.parseInt(txtQtdeDias.getText().replaceAll("\\.", "").replace(",", "."));
			Float valorTaxaServico = Float.parseFloat(txtValorTaxaServico.getText().replaceAll("\\.", "").replace(",", "."));
			Float valorProtecao = Float.parseFloat(txtValorProtecao.getText().replaceAll("\\.", "").replace(",", "."));
			Float valorTotal = ((valorDia * qtdeDias) + valorTaxaServico + valorProtecao);
			txtValorTotal.setText(String.valueOf("R$" + df.format(valorTotal)));
		}
	}
}