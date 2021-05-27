package br.com.locacar.action.usuario;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.usuario.*;
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
 * Classe action responsável pela consulta de usuários no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionConsultarUsuario implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionConsultarUsuario.class.getName());
	
	public static void consultar(JTextField txtUsuario, JTable jTabDadosUsuario, JButton btnAlterar, JButton btnExcluir, JRootPane rootPane) {
		txtUsuario.requestFocus();
		Usuarios usuarios = new DAOUsuarios();
		ResultSet rs = usuarios.buscarPorNome(txtUsuario.getText());
		DefaultTableModel dtm = (DefaultTableModel) jTabDadosUsuario.getModel();
		dtm.setNumRows(0);
		try {
			while (rs.next()) {
				dtm.addRow(new Object[] { rs.getString("CODIGO"), rs.getString("NOME"), rs.getString("SENHA"), rs.getString("PERFIL"), rs.getString("SITUACAO") } );
			}
			if (dtm.getRowCount() == 0) {
				btnAlterar.setEnabled(false);
				btnExcluir.setEnabled(false);
				if (txtUsuario.getText().equals("")) {
					LOG.info("Nenhum usuário foi encontrado!");
					Modal.mensagem(new MensagensModel(Bundle.getString("usersNaoEncontrados"), nivelMensagens.ERRO), rootPane);
				} else {
					LOG.info("O usuário {} não foi encontrado!", txtUsuario.getText());
					Modal.mensagem(new MensagensModel(Bundle.getString("userNaoEncontrado", txtUsuario.getText()), nivelMensagens.ERRO), rootPane);
				}
				txtUsuario.setText("");
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