package br.com.locacar.action.locacao;

import br.com.locacar.model.locacao.*;
import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import com.toedter.calendar.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.awt.*;
import java.sql.*;

public abstract class ActionCarregarDadosLocacao extends WindowAdapter {
	private static final Logger LOG = LogManager.getLogger(ActionCarregarDadosLocacao.class.getName());
	private String info, cpf, cnpj;
	private ResultSet rs;
	
	public void carregarDadosLocacao(JLabel codigo, JRadioButton rdbCpf, JRadioButton rdbCnpj, JFormattedTextField txtCpfCnpj, JTextField txtNome, JTextField txtEndereco, JTextField txtCidade, JTextField txtBairro, JTextField txtEmail, JFormattedTextField txtPlaca, JComboBox<Object> jcbMarca, JComboBox<Object> jcbModelo, JTextField txtAnoModelo, JTextField txtCor, JTextField txtCombustivel, JTextField txtRenavam, JDateChooser jdcDataLocacao, JComboBox<Object> jcbHoraLocacao, JDateChooser jdcDataRetornoLocacao, JComboBox<Object> jcbHoraRetornoLocacao, JTextField txtKilometragem, JTextField txtObservacao, JTextField txtLocalEntrega, JFormattedTextField txtValorDia, JTextField txtQtdeDias, JFormattedTextField txtValorTaxaServico, JFormattedTextField txtValorProtecao, JFormattedTextField txtValorTotal, JTextField txtSituacao, JTextField txtNumLocacao) {
		LOG.info("Carregando os dados das locações para visualização...");
		if (codigo.getText() != "") {
			Locacao locacoes = new DAOLocacao();
			rs = locacoes.buscarParaVisualizacao(codigo.getText());
			try {
				if (rs.next()) {
					getCpfCnpj(rdbCpf, rdbCnpj, txtCpfCnpj);
					txtNome.setText(rs.getString("NOME"));
					txtEndereco.setText(rs.getString("ENDERECO"));
					txtCidade.setText(rs.getString("CIDADE"));
					txtBairro.setText(rs.getString("BAIRRO"));
					txtEmail.setText(rs.getString("EMAIL"));
					txtPlaca.setText(rs.getString("PLACA"));
					jcbMarca.setSelectedItem(rs.getString("MARCA"));
					jcbModelo.setSelectedItem(rs.getString("MODELO"));
					txtAnoModelo.setText(rs.getString("ANO"));
					txtCor.setText(rs.getString("COR"));
					txtCombustivel.setText(rs.getString("COMBUSTIVEL"));
					txtRenavam.setText(rs.getString("RENAVAM"));
					jdcDataLocacao.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("DATALOCACAO")));
					jcbHoraLocacao.setSelectedItem(rs.getString("HORALOCACAO"));
					jdcDataRetornoLocacao.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("DATARETORNOLOCACAO")));
					jcbHoraRetornoLocacao.setSelectedItem(rs.getString("HORARETORNOLOCACAO"));
					txtKilometragem.setText(rs.getString("KILOMETRAGEM"));
					txtObservacao.setText(rs.getString("OBSERVACOES"));
					txtLocalEntrega.setText(rs.getString("LOCALENTREGA"));
					txtValorDia.setText(rs.getString("VALORDIA"));
					txtQtdeDias.setText(rs.getString("QTDEDIAS"));
					txtValorTaxaServico.setText(rs.getString("VALORTAXASERVICO"));
					txtValorProtecao.setText(rs.getString("VALORPROTECAO"));
					txtValorTotal.setText(rs.getString("VALORTOTAL"));
					if (rs.getString("SITUACAO").equals("EM ABERTO")) {
						txtSituacao.setForeground(Color.RED);
						txtSituacao.setText(rs.getString("SITUACAO"));
					} else if (rs.getString("SITUACAO").equals("FINALIZADA")) {
						txtSituacao.setForeground(Color.BLUE);
						txtSituacao.setText(rs.getString("SITUACAO"));
					}
					txtNumLocacao.setText(rs.getString("NUMEROLOCACAO"));
				}
			} catch(SQLException e) {
				LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
			} catch(ParseException e) {
				LOG.error("Erro de sistema: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("erroSistema " + e.getMessage().toUpperCase())).getText(), "ERRO", JOptionPane.ERROR_MESSAGE);
			}
		}
		LOG.info("Dados carregados com sucesso!");
	}
	
	private void getCpfCnpj(JRadioButton rdbCpf, JRadioButton rdbCnpj, JFormattedTextField txtCpfCnpj) throws SQLException {
		info = rs.getString("CPFCNPJ");
		if (info.length() == 14) {
			cpf = info;
			rdbCpf.setSelected(true);
			txtCpfCnpj.setText(cpf);
		} else if (info.length() == 18) {
			cnpj = info;
			rdbCnpj.setSelected(true);
			txtCpfCnpj.setText(cnpj);
		}
	}
}