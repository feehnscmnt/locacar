package br.com.locacar.tests.dao;

import org.apache.logging.log4j.*;
import br.com.locacar.dao.*;
import junit.framework.*;
import javax.xml.bind.*;
import org.junit.Test;
import java.sql.*;

/**
 * Classe responsável pela realização dos testes unitários
 * das tarefas no banco de dados relacionadas aos veículos!
 * @author Felipe Nascimento
 */
public class DAOVeiculosTest extends TestCase {
	private static final Logger LOG = LogManager.getLogger(DAOVeiculosTest.class.getName());
	private StringBuilder sb;
	
	@Test
	public void testBuscarPorPlaca() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT CODIGO, SITUACAO, DATALOCACAO, PLACA, MARCA, ");
			sb.append("MODELO, VERSAO, ANOFABRICACAO, ANOMODELO, COR, ");
			sb.append("COMBUSTIVEL, TRANSMISSAO, RENAVAM, PORTAS, ");
			sb.append("ALARME, TRAVAS, SENSORRE, BANCOSCOURO, FREIOSABS, ");
			sb.append("AIRBAGS, CAMERARE, MULTIMIDIA, BANCOSREGULAVEIS, ");
			sb.append("VIDROSELETRICOS, DIRECAOHIDRAULICA, DIRECAOELETRICA ");
			sb.append("FROM T_VEICULOS WHERE PLACA LIKE ");
			sb.append("'%FDK-1434%' AND STATUS = '1'");
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
			sb.append("SELECT * FROM T_VEICULOS ");
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
			sb.append("SELECT * FROM T_VEICULOS ");
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
	public void testBuscarParaLocacao() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT * FROM T_VEICULOS WHERE SITUACAO = 'DISPONÍVEL' ");
			sb.append("AND PLACA = 'FDK-1434'");
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
			sb.append("SELECT * FROM T_VEICULOS ");
			sb.append("WHERE PLACA = 'FDK-1434'");
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
			sb.append("INSERT INTO T_VEICULOS ");
			sb.append("(PLACA, MARCA, MODELO, VERSAO, ");
			sb.append("ANOFABRICACAO, ANOMODELO, COR, COMBUSTIVEL, ");
			sb.append("TRANSMISSAO, RENAVAM, PORTAS, ALARME, TRAVAS, ");
			sb.append("SENSORRE, BANCOSCOURO, FREIOSABS, AIRBAGS, CAMERARE, ");
			sb.append("MULTIMIDIA, BANCOSREGULAVEIS, VIDROSELETRICOS, ");
			sb.append("DIRECAOHIDRAULICA, DIRECAOELETRICA, SITUACAO, STATUS) VALUES (");
			sb.append("'FDK-1434', ");
			sb.append("'RENAULT', ");
			sb.append("'SANDERO', ");
			sb.append("'STEPWAY', ");
			sb.append("'2018', ");
			sb.append("'2018', ");
			sb.append("'AZUL', ");
			sb.append("'FLEX', ");
			sb.append("'MANUAL', ");
			sb.append("'46806560124', ");
			sb.append("'4', ");
			sb.append("'SIM', ");
			sb.append("'SIM', ");
			sb.append("'NÃO', ");
			sb.append("'NÃO', ");
			sb.append("'SIM', ");
			sb.append("'SIM', ");
			sb.append("'NÃO', ");
			sb.append("'SIM', ");
			sb.append("'SIM', ");
			sb.append("'SIM', ");
			sb.append("'NÃO', ");
			sb.append("'SIM', ");
			sb.append("'DISPONÍVEL', ");
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
			sb.append("UPDATE T_VEICULOS SET ");
			sb.append("PLACA = 'FDK-1434', ");
			sb.append("MARCA = 'RENAULT', ");
			sb.append("MODELO = 'SANDERO', ");
			sb.append("VERSAO = 'STEPWAY', ");
			sb.append("ANOFABRICACAO = '2018', ");
			sb.append("ANOMODELO = '2018', ");
			sb.append("COR = 'AZUL', ");
			sb.append("COMBUSTIVEL = 'FLEX', ");
			sb.append("TRANSMISSAO = 'MANUAL', ");
			sb.append("RENAVAM = '46806560124', ");
			sb.append("PORTAS = '4', ");
			sb.append("ALARME = 'SIM', ");
			sb.append("TRAVAS = 'SIM', ");
			sb.append("SENSORRE = 'NÃO', ");
			sb.append("BANCOSCOURO = 'NÃO', ");
			sb.append("FREIOSABS = 'SIM', ");
			sb.append("AIRBAGS = 'SIM', ");
			sb.append("CAMERARE = 'NÃO', ");
			sb.append("MULTIMIDIA = 'SIM', ");
			sb.append("BANCOSREGULAVEIS = 'SIM', ");
			sb.append("VIDROSELETRICOS = 'SIM', ");
			sb.append("DIRECAOHIDRAULICA = 'NÃO', ");
			sb.append("DIRECAOELETRICA = 'SIM', ");
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
			sb.append("UPDATE T_VEICULOS SET ");
			sb.append("STATUS = '0' ");
			sb.append("WHERE PLACA = 'FDK-1434'");
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