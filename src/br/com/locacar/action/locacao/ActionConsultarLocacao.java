package br.com.locacar.action.locacao;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.locacao.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import javax.swing.table.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Classe action responsável pela consulta de locações no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionConsultarLocacao implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionConsultarLocacao.class.getName());
	
	public static void consultar(JTextField txtNumeroLocacao, JTable jTabDadosLocacao, JButton btnVisualizar, JButton btnDarBaixa, JRootPane rootPane) {
		txtNumeroLocacao.requestFocus();
		Locacao locacoes = new DAOLocacao();
		ResultSet rs = locacoes.buscarPorNumero(txtNumeroLocacao.getText());
		DefaultTableModel dtm = (DefaultTableModel) jTabDadosLocacao.getModel();
		dtm.setNumRows(0);
		try {
			while (rs.next()) {
				dtm.addRow(new Object[] { rs.getString("CODIGO"), rs.getString("SITUACAO"), rs.getString("NUMEROLOCACAO"), rs.getString("CPFCNPJ"), rs.getString("NOME"),
				rs.getString("ENDERECO"), rs.getString("CIDADE"), rs.getString("BAIRRO"), rs.getString("EMAIL"), rs.getString("PLACA"), rs.getString("MARCA"), rs.getString("MODELO"),
				rs.getString("COMBUSTIVEL"), rs.getString("COR"), rs.getString("ANO"), rs.getString("RENAVAM"), rs.getString("KILOMETRAGEM"), rs.getString("DATALOCACAO"),
				rs.getString("HORALOCACAO"), rs.getString("DATARETORNOLOCACAO"), rs.getString("HORARETORNOLOCACAO"), rs.getString("LOCALENTREGA"), rs.getString("OBSERVACOES"),
				rs.getString("VALORDIA"), rs.getString("QTDEDIAS"), rs.getString("VALORTAXASERVICO"), rs.getString("VALORPROTECAO"), rs.getString("VALORTOTAL") } );
			}
			if (dtm.getRowCount() == 0) {
				btnVisualizar.setEnabled(false);
				btnDarBaixa.setEnabled(false);
				if (txtNumeroLocacao.getText().equals("")) {
					LOG.info("Nenhuma locação foi encontrada!");
					Modal.mensagem(new MensagensModel(Bundle.getString("consLocsNaoEncontradas"), nivelMensagens.ERRO), rootPane);
				} else {
					LOG.info("A locação {} não foi encontrada!", txtNumeroLocacao.getText());
					Modal.mensagem(new MensagensModel(Bundle.getString("consNumLocNaoEncontrada", txtNumeroLocacao.getText()), nivelMensagens.ERRO), rootPane);
				}
				txtNumeroLocacao.setText("");
			} else {
				btnVisualizar.setEnabled(true);
				btnDarBaixa.setEnabled(true);
			}
		} catch(SQLException e) {
			LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
}