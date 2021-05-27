package br.com.locacar.action.locacao;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.view.locacao.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import br.com.locacar.domain.*;
import com.toedter.calendar.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

/**
 * Classe action responsável pelo cálculo de quantidade de dias que terá uma locação!
 * @author Felipe Nascimento
 */
public abstract class ActionQtdeDiasLocacao extends FocusAdapter {
	private GregorianCalendar dtInicial = new GregorianCalendar();
	private GregorianCalendar dtFinal = new GregorianCalendar();
	private String dataInicial, dataFinal;
	int qtdeDias;
	
	public void qtdeDiasLocacao(JDateChooser jdcDataLocacao, JDateChooser jdcDataRetornoLocacao, JTextField txtQtdeDias, JRootPane rootPane) {
		if (jdcDataLocacao.getDate() == null) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoDataLocacao"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.jdcDataLocacao.requestFocus();
		} else if (jdcDataRetornoLocacao.getDate() == null) {
			Modal.mensagem(new MensagensModel(Bundle.getString("campoDataRetornoLocacao"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.jdcDataRetornoLocacao.requestFocus();
		} else {
			try {
				dataInicial = new SimpleDateFormat().format(jdcDataLocacao.getDate());
				dataFinal = new SimpleDateFormat().format(jdcDataRetornoLocacao.getDate());
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				dtInicial.setTime(sdf.parse(dataInicial));
				dtFinal.setTime(sdf.parse(dataFinal));
				qtdeDias = (int) (((dtFinal.getTimeInMillis() - dtInicial.getTimeInMillis()) / 86400000) + 1);
				txtQtdeDias.setText(String.valueOf(qtdeDias));
			} catch(ParseException e) {
				e.printStackTrace();
			}
		}
	}
}