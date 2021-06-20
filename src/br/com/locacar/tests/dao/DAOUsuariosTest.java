package br.com.locacar.tests.dao;

import org.apache.logging.log4j.*;
import br.com.locacar.dao.*;
import junit.framework.*;
import javax.xml.bind.*;
import org.junit.Test;
import java.sql.*;

/**
 * Classe responsável pela realização dos testes unitários
 * das tarefas no banco de dados relacionadas aos usuários!
 * @author Felipe Nascimento
 */
public class DAOUsuariosTest extends TestCase {
	private static final Logger LOG = LogManager.getLogger(DAOUsuariosTest.class.getName());
	private StringBuilder sb;
	
	@Test
	public void testBuscarPorNome() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT CODIGO, NOME, SENHA, ");
			sb.append("PERFIL, SITUACAO, STATUS ");
			sb.append("FROM T_USUARIOS WHERE NOME LIKE ");
			sb.append("'%TESTE%' AND STATUS = '1'");
			Connection con = DAOAccessManager.openConnection();
			PreparedStatement st = con.prepareStatement(String.valueOf(sb));
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
	public void testBuscarTodos() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT * FROM T_USUARIOS ");
			sb.append("WHERE STATUS = '1'");
			Connection con = DAOAccessManager.openConnection();
			PreparedStatement st = con.prepareStatement(String.valueOf(sb));
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
	public void testBuscarParaAlteracao() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT * FROM T_USUARIOS ");
			sb.append("WHERE CODIGO = '1'");
			Connection con = DAOAccessManager.openConnection();
			PreparedStatement st = con.prepareStatement(String.valueOf(sb));
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
	public void testBuscarParaAutenticar() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT CODIGO, NOME, ");
			sb.append("SENHA, PERFIL, SITUACAO, ");
			sb.append("STATUS FROM T_USUARIOS ");
			sb.append("WHERE NOME = 'TESTE'");
			Connection con = DAOAccessManager.openConnection();
			PreparedStatement st = con.prepareStatement(String.valueOf(sb));
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
	public void testSalvar() {
		try {
			sb = new StringBuilder();
			sb.append("INSERT INTO T_USUARIOS ");
			sb.append("(NOME, SENHA, PERFIL, ");
			sb.append("SITUACAO, STATUS) VALUES (");
			sb.append("'TESTE', ");
			sb.append("'tst132435', ");
			sb.append("'ADMINISTRADOR', ");
			sb.append("'ATIVO', ");
			sb.append("'1')");
			Connection con = DAOAccessManager.openConnection();
			PreparedStatement st = con.prepareStatement(String.valueOf(sb));
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
	public void testAlterar() {
		try {
			sb = new StringBuilder();
			sb.append("UPDATE T_USUARIOS SET ");
			sb.append("NOME = 'TESTE', ");
			sb.append("SENHA = 'tst132435', ");
			sb.append("PERFIL = 'ADMINISTRADOR', ");
			sb.append("SITUACAO = 'ATIVO', ");
			sb.append("STATUS = '1' ");
			sb.append("WHERE CODIGO = '1'");
			Connection con = DAOAccessManager.openConnection();
			PreparedStatement st = con.prepareStatement(String.valueOf(sb));
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
	public void testExcluir() {
		try {
			sb = new StringBuilder();
			sb.append("UPDATE T_USUARIOS SET ");
			sb.append("STATUS = '0' ");
			sb.append("WHERE NOME = 'TESTE'");
			Connection con = DAOAccessManager.openConnection();
			PreparedStatement st = con.prepareStatement(String.valueOf(sb));
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
}