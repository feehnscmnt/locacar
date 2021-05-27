package br.com.locacar.action.veiculo;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.veiculo.*;
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
 * Classe action responsável pela consulta de veículos no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionConsultarVeiculo implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionConsultarVeiculo.class.getName());
	
	public static void consultar(JFormattedTextField txtPlaca, JTable jTabDadosVeiculo, JButton btnAlterar, JButton btnExcluir, JRootPane rootPane) {
		txtPlaca.requestFocus();
		Veiculos veiculos = new DAOVeiculos();
		ResultSet rs = veiculos.buscarPorPlaca(txtPlaca.getText());
		DefaultTableModel dtm = (DefaultTableModel) jTabDadosVeiculo.getModel();
		dtm.setNumRows(0);
		try {
			while (rs.next()) {
				dtm.addRow(new Object[] { rs.getString("CODIGO"), rs.getString("SITUACAO"), rs.getString("DATALOCACAO"), rs.getString("PLACA"), rs.getString("MARCA"),
				rs.getString("MODELO"), rs.getString("VERSAO"), rs.getString("ANOFABRICACAO"), rs.getString("ANOMODELO"), rs.getString("COR"), rs.getString("COMBUSTIVEL"),
				rs.getString("TRANSMISSAO"), rs.getString("RENAVAM"), rs.getString("PORTAS"), rs.getString("ALARME"), rs.getString("TRAVAS"), rs.getString("SENSORRE"),
				rs.getString("BANCOSCOURO"), rs.getString("FREIOSABS"), rs.getString("AIRBAGS"), rs.getString("CAMERARE"), rs.getString("MULTIMIDIA"), rs.getString("BANCOSREGULAVEIS"),
				rs.getString("VIDROSELETRICOS"), rs.getString("DIRECAOHIDRAULICA"), rs.getString("DIRECAOELETRICA") } );
			}
			if (dtm.getRowCount() == 0) {
				btnAlterar.setEnabled(false);
				btnExcluir.setEnabled(false);
				if (txtPlaca.getText().equals("")) {
					LOG.info("Nenhum veículo foi encontrado!");
					Modal.mensagem(new MensagensModel(Bundle.getString("veicsNaoEncontrados"), nivelMensagens.ERRO), rootPane);
				} else {
					LOG.info("O veículo da placa {} não foi encontrado!", txtPlaca.getText());
					Modal.mensagem(new MensagensModel(Bundle.getString("veicNaoEncontrado", txtPlaca.getText()), nivelMensagens.ERRO), rootPane);
				}
				txtPlaca.setText("");
			} else {
				btnAlterar.setEnabled(true);
				btnExcluir.setEnabled(true);
			}
		} catch(SQLException e) {
			LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
}