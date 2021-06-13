package br.com.locacar.dao;

import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.model.dao.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import javax.xml.bind.*;
import javax.swing.*;
import java.sql.*;

/**
 * Classe respons�vel pela conex�o com o banco de dados!
 * @author Felipe Nascimento
 */
public class DAOAccessManager implements ConfigDao {
	private static final Logger LOG = LogManager.getLogger(DAOAccessManager.class.getName());
	private ConfigDaoModel configDaoModel;
	
	private Connection openConnection() throws JAXBException, ClassNotFoundException, SQLException {
		configDaoModel = new ConfigDaoModel();
		Connection con = null;
		LOG.info("Realizando conex�o com o banco de dados...");
		configDaoModel = (ConfigDaoModel) XmlReadUtil.lerXmlConfigDao(PathFilesUtil.getConfigFile("configDao.xml"));
		Class.forName(configDaoModel.getDriver());
		con = (Connection) DriverManager.getConnection(configDaoModel.getAcesso() + configDaoModel.getLogin() + configDaoModel.getSenha());
		
		if (con != null) {
			LOG.info("A conex�o foi realizada com sucesso!");
		}
		
		return con;
	}
	
	@Override
	public ResultSet retornaQuery(String query) {
		ResultSet rs = null;
		try {
			Connection con = openConnection();
			PreparedStatement st = con.prepareStatement(query);
			rs = st.executeQuery();
		} catch(SQLException e) {
			LOG.error("Houve problemas na conex�o com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		} catch(ClassNotFoundException e) {
			LOG.error("N�o foi poss�vel carregar o driver de conex�o com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("driverNaoEncontrado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		} catch(JAXBException e) {
			LOG.error("O XML de conex�o com o banco de dados n�o foi localizado! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("xmlConexaoNaoEncontrado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
		
		if (rs != null) {
			LOG.info("Busca realizada com sucesso!");
		}
		
		return rs;
	}
	
	@Override
	public void executaQuery(String query) {
		Connection con = null;
		try {
			con = openConnection();
			PreparedStatement st = con.prepareStatement(query);
			st.execute();
		} catch(SQLException e) {
			LOG.error("Houve problemas na conex�o com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		} catch(ClassNotFoundException e) {
			LOG.error("N�o foi poss�vel carregar o driver de conex�o com o banco de dados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("driverNaoEncontrado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		} catch(JAXBException e) {
			LOG.error("O XML de conex�o com o banco de dados n�o foi localizado! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("xmlConexaoNaoEncontrado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
		LOG.info("Informa��o salva com sucesso no banco de dados!");
	}
}