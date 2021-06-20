package br.com.locacar.tests.dao;

import org.apache.logging.log4j.*;
import br.com.locacar.dao.*;
import junit.framework.*;
import javax.xml.bind.*;
import org.junit.Test;
import java.sql.*;

/**
 * Classe responsável pela realização dos testes unitários
 * das tarefas no banco de dados relacionadas aos clientes pessoa física!
 * @author Felipe Nascimento
 */
public class DAOClientesPFTest extends TestCase {
	private static final Logger LOG = LogManager.getLogger(DAOClientesPFTest.class.getName());
	private StringBuilder sb;
	
	@Test
	public void testBuscarPorCpf() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT CODIGO, NOME, CPF, RG, CONTATO, ");
			sb.append("DATANASCIMENTO, CATEGCNH, NUMEROCNH, ");
			sb.append("EMAIL, ENDERECO, CEP, CIDADE, UF, BAIRRO, ");
			sb.append("COMPLEMENTO, IMAGEM FROM T_CLIENTESPF ");
			sb.append("WHERE CPF LIKE '%12827353067%' AND STATUS = '1'");
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
			sb.append("SELECT * FROM T_CLIENTESPF ");
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
			sb.append("SELECT * FROM T_CLIENTESPF ");
			sb.append("WHERE CPF = '12827353067'");
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
			sb.append("SELECT * FROM T_CLIENTESPF ");
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
			sb.append("SELECT * FROM T_CLIENTESPF ");
			sb.append("WHERE CPF = '12827353067'");
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
			sb.append("INSERT INTO T_CLIENTESPF ");
			sb.append("(NOME, CPF, RG, CONTATO, ");
			sb.append("DATANASCIMENTO, CATEGCNH, NUMEROCNH, ");
			sb.append("EMAIL, ENDERECO, CEP, CIDADE, UF, ");
			sb.append("BAIRRO, COMPLEMENTO, IMAGEM, STATUS) VALUES (");
			sb.append("'TESTE', ");
			sb.append("'12827353067', ");
			sb.append("'410480435', ");
			sb.append("'(11)967543543', ");
			sb.append("'23/06/1988', ");
			sb.append("'AB', ");
			sb.append("'70023466376', ");
			sb.append("'TESTE@TESTE.COM', ");
			sb.append("'RUA XV DE NOVEMBRO, 43', ");
			sb.append("'08976221', ");
			sb.append("'TESTING', ");
			sb.append("'TS', ");
			sb.append("'TESTADOS', ");
			sb.append("'NC', ");
			sb.append("'C:/Users/imagem.jpg', ");
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
			sb.append("UPDATE T_CLIENTESPF SET ");
			sb.append("NOME = 'TESTE', ");
			sb.append("CPF = '12827353067', ");
			sb.append("RG = '410480435', ");
			sb.append("CONTATO = '(11)967543543', ");
			sb.append("DATANASCIMENTO = '23/06/1988', ");
			sb.append("CATEGCNH = 'AB', ");
			sb.append("NUMEROCNH = '70023466376', ");
			sb.append("EMAIL = 'TESTE@TESTE.COM', ");
			sb.append("ENDERECO = 'RUA XV DE NOVEMBRO, 43', ");
			sb.append("CEP = '08976221', ");
			sb.append("CIDADE = 'TESTING', ");
			sb.append("UF = 'TS', ");
			sb.append("BAIRRO = 'TESTADOS', ");
			sb.append("COMPLEMENTO = 'NC', ");
			sb.append("IMAGEM = 'C:/Users/imagem.jpg', ");
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
			sb.append("UPDATE T_CLIENTESPF SET ");
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