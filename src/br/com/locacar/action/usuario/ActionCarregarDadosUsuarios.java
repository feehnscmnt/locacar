package br.com.locacar.action.usuario;

import br.com.locacar.model.usuario.*;
import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

/**
 * Classe responsável por carregar do banco de dados as informações dos usuários para alterações!
 * @author Felipe Nascimento
 */
public abstract class ActionCarregarDadosUsuarios extends WindowAdapter {
	private static final Logger LOG = LogManager.getLogger(ActionCarregarDadosUsuarios.class.getName());
	
	public void carregaDadosUsuarios(JLabel codigo, JTextField txtUsuario, JPasswordField txtSenha, JComboBox<Object> jcbPerfilUsuario, JComboBox<Object> jcbSituacao) {
		LOG.info("Carregando os dados dos usuários para alterações...");
		if (codigo.getText() != "") {
			Usuarios usuarios = new DAOUsuarios();
			ResultSet rs = usuarios.buscarParaAlteracao(codigo.getText());
			try {
				if (rs.next()) {
					txtUsuario.setText(rs.getString("NOME"));
					txtSenha.setText(rs.getString("SENHA"));
					jcbPerfilUsuario.setSelectedItem(rs.getString("PERFIL"));
					jcbSituacao.setSelectedItem(rs.getString("SITUACAO"));
				}
			} catch(SQLException e) {
				LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
			}
		}
		LOG.info("Dados carregados com sucesso!");
	}
}