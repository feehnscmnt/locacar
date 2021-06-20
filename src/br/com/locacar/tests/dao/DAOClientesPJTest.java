package br.com.locacar.tests.dao;

import org.apache.logging.log4j.*;
import br.com.locacar.dao.*;
import junit.framework.*;
import javax.xml.bind.*;
import org.junit.Test;
import java.sql.*;

/**
 * Classe responsável pela realização dos testes unitários
 * das tarefas no banco de dados relacionadas aos clientes pessoa jurídica!
 * @author Felipe Nascimento
 */
public class DAOClientesPJTest extends TestCase {
	private static final Logger LOG = LogManager.getLogger(DAOClientesPJTest.class.getName());
	private StringBuilder sb;
	
	@Test
	public void testBuscarPorCpfCnpj() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT CODIGO, NOME, RAZAOSOCIAL, CPFCNPJ, CONTATO, ");
			sb.append("INSCRICAOESTADUAL, ENDERECO, CEP, CIDADE, UF, ");
			sb.append("BAIRRO, COMPLEMENTO, EMAIL, NOMEMOTORISTA, NUMEROCNH, CATEGCNH ");
			sb.append("FROM T_CLIENTESPJ WHERE CPFCNPJ LIKE '%12827353067%' AND STATUS = '1'");
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
			sb.append("SELECT * FROM T_CLIENTESPJ ");
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
	public void testBuscarParaLocacao() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT * FROM T_CLIENTESPJ ");
			sb.append("WHERE CPFCNPJ = '12827353067'");
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
			sb.append("SELECT * FROM T_CLIENTESPJ ");
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
	public void testVerificarSeExiste() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT * FROM T_CLIENTESPJ ");
			sb.append("WHERE CPFCNPJ = '12827353067'");
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
			sb.append("INSERT INTO T_CLIENTESPJ ");
			sb.append("(NOME, RAZAOSOCIAL, CPFCNPJ, CONTATO, ");
			sb.append("INSCRICAOESTADUAL, ENDERECO, CEP, CIDADE, ");
			sb.append("UF, BAIRRO, COMPLEMENTO, EMAIL, NOMEMOTORISTA, ");
			sb.append("NUMEROCNH, CATEGCNH, STATUS) VALUES (");
			sb.append("'TESTE', ");
			sb.append("'TESTES LTDA', ");
			sb.append("'34358370000117', ");
			sb.append("'(11)967543543', ");
			sb.append("'131307075548', ");
			sb.append("'RUA XV DE NOVEMBRO, 43', ");
			sb.append("'08976221', ");
			sb.append("'TESTING', ");
			sb.append("'TS', ");
			sb.append("'TESTADOS', ");
			sb.append("'NC', ");
			sb.append("'TESTE@TESTE.COM', ");
			sb.append("'TESTEMOTOR', ");
			sb.append("'70023466376', ");
			sb.append("'AB', ");
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
			sb.append("UPDATE T_CLIENTESPJ SET ");
			sb.append("NOME = 'TESTE', ");
			sb.append("RAZAOSOCIAL = 'TESTES LTDA', ");
			sb.append("CPFCNPJ = '34358370000117', ");
			sb.append("CONTATO = '(11)967543543', ");
			sb.append("INSCRICAOESTADUAL = '131307075548', ");
			sb.append("ENDERECO = 'RUA XV DE NOVEMBRO, 43', ");
			sb.append("CEP = '08976221', ");
			sb.append("CIDADE = 'TESTING', ");
			sb.append("UF = 'TS', ");
			sb.append("BAIRRO = 'TESTADOS', ");
			sb.append("COMPLEMENTO = 'NC', ");
			sb.append("EMAIL = 'TESTE@TESTE.COM', ");
			sb.append("NOMEMOTORISTA = 'TESTEMOTOR', ");
			sb.append("NUMEROCNH = '70023466376', ");
			sb.append("CATEGCNH = 'AB', ");
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
			sb.append("UPDATE T_CLIENTESPJ SET ");
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