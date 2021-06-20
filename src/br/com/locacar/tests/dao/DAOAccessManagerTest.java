package br.com.locacar.tests.dao;

import org.apache.logging.log4j.*;
import br.com.locacar.dao.*;
import junit.framework.*;
import javax.xml.bind.*;
import org.junit.Test;
import java.sql.*;

/**
 * Classe responsável pela realização dos testes unitários de conexão com o banco de dados!
 * @author Felipe Nascimento
 */
public class DAOAccessManagerTest extends TestCase {
	private static final Logger LOG = LogManager.getLogger(DAOAccessManagerTest.class.getName());
	
	@Test
	public void testOpenConnection() {
		try {
			Connection con = DAOAccessManager.openConnection();
			assertEquals(con, true);
		} catch(SQLException e) {
			LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
		} catch(ClassNotFoundException e) {
			LOG.error("Não foi possível carregar o driver de conexão com o banco de dados! Exception: {}", e.getMessage());
		} catch(JAXBException e) {
			LOG.error("O XML de conexão com o banco de dados não foi localizado! Exception: {}", e.getMessage());
		}
	}
	
	@Test
	public void testRetornaQuery() {
		try {
			String query = "SELECT * FROM T_LOGIN";
			Connection con = DAOAccessManager.openConnection();
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			assertEquals(rs, true);
		} catch(SQLException e) {
			LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
		} catch(ClassNotFoundException e) {
			LOG.error("Não foi possível carregar o driver de conexão com o banco de dados! Exception: {}", e.getMessage());
		} catch(JAXBException e) {
			LOG.error("O XML de conexão com o banco de dados não foi localizado! Exception: {}", e.getMessage());
		}
	}
	
	@Test
	public void testExecutaQuery() {
		try {
			String query = "SELECT * FROM T_LOGIN";
			Connection con = DAOAccessManager.openConnection();
			PreparedStatement st = con.prepareStatement(query);
			st.executeQuery();
			assertEquals(st, true);
		} catch(SQLException e) {
			LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
		} catch(ClassNotFoundException e) {
			LOG.error("Não foi possível carregar o driver de conexão com o banco de dados! Exception: {}", e.getMessage());
		} catch(JAXBException e) {
			LOG.error("O XML de conexão com o banco de dados não foi localizado! Exception: {}", e.getMessage());
		}
	}
}