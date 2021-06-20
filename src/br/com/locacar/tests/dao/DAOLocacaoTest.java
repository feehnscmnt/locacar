package br.com.locacar.tests.dao;

import org.apache.logging.log4j.*;
import br.com.locacar.dao.*;
import junit.framework.*;
import javax.xml.bind.*;
import org.junit.Test;
import java.sql.*;

/**
 * Classe responsável pela realização dos testes unitários
 * das tarefas no banco de dados relacionadas às locações!
 * @author Felipe Nascimento
 */
public class DAOLocacaoTest extends TestCase {
	private static final Logger LOG = LogManager.getLogger(DAOLocacaoTest.class.getName());
	private StringBuilder sb;
	
	@Test
	public void testBuscarPorNumero() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT CODIGO, SITUACAO, NUMEROLOCACAO, CPFCNPJ, NOME, ENDERECO, CIDADE, BAIRRO, ");
			sb.append("EMAIL, PLACA, MARCA, MODELO, COMBUSTIVEL, COR, ANO, RENAVAM, KILOMETRAGEM, ");
			sb.append("DATALOCACAO, HORALOCACAO, DATARETORNOLOCACAO, HORARETORNOLOCACAO, ");
			sb.append("LOCALENTREGA, OBSERVACOES, VALORDIA, QTDEDIAS, VALORTAXASERVICO, VALORPROTECAO, VALORTOTAL, ");
			sb.append("STATUS FROM T_LOCACAO WHERE NUMEROLOCACAO LIKE '%LOC1324%' AND STATUS = '1'");
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
	public void testBuscarTodas() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT * FROM T_LOCACAO ");
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
	public void testVeiculoAlugado() {
		try {
			sb = new StringBuilder();
			sb.append("UPDATE T_VEICULOS SET ");
			sb.append("SITUACAO = 'ALUGADO', ");
			sb.append("DATALOCACAO = '01/01/2021' ");
			sb.append("WHERE PLACA = 'FDK-1434' AND STATUS = '1'");
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
	public void testVeiculoDisponibilizado() {
		try {
			sb = new StringBuilder();
			sb.append("UPDATE T_VEICULOS SET ");
			sb.append("SITUACAO = 'DISPONÍVEL', ");
			sb.append("DATALOCACAO = '01/01/2021' ");
			sb.append("WHERE PLACA = 'FDK-1434' AND STATUS = '1'");
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
	public void testFinalizaLocacao() {
		try {
			sb = new StringBuilder();
			sb.append("UPDATE T_LOCACAO SET ");
			sb.append("SITUACAO = 'DISPONÍVEL' ");
			sb.append("WHERE NUMEROLOCACAO = 'LOC1324' AND STATUS = '1'");
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
	public void testBuscarParaVisualizacao() {
		try {
			sb = new StringBuilder();
			sb.append("SELECT * FROM T_LOCACAO ");
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
	public void testSalvar() {
		try {
			sb = new StringBuilder();
			sb.append("INSERT INTO T_LOCACAO (NUMEROLOCACAO, CPFCNPJ, NOME, ENDERECO, CIDADE, ");
			sb.append("BAIRRO, EMAIL, PLACA, MARCA, MODELO, COMBUSTIVEL, COR, ANO, RENAVAM, KILOMETRAGEM, ");
			sb.append("DATALOCACAO, HORALOCACAO, DATARETORNOLOCACAO, HORARETORNOLOCACAO, ");
			sb.append("LOCALENTREGA, OBSERVACOES, VALORDIA, QTDEDIAS, VALORTAXASERVICO, ");
			sb.append("VALORPROTECAO, VALORTOTAL, SITUACAO, STATUS) VALUES (");
			sb.append("'LOC1324', ");
			sb.append("'12827353067', ");
			sb.append("'TESTE', ");
			sb.append("'RUA XV DE NOVEMBRO, 43', ");
			sb.append("'TESTING', ");
			sb.append("'TESTADOS', ");
			sb.append("'TESTE@TESTE.COM', ");
			sb.append("'FDK-1434', ");
			sb.append("'VOLKSWAGEN', ");
			sb.append("'UP', ");
			sb.append("'FLEX', ");
			sb.append("'BRANCO', ");
			sb.append("'2018', ");
			sb.append("'54811703605', ");
			sb.append("'27634', ");
			sb.append("'01/01/2021', ");
			sb.append("'10:00', ");
			sb.append("'15/01/2021', ");
			sb.append("'17:00', ");
			sb.append("'MATRIZ TESTE', ");
			sb.append("'VEICULO TESTE', ");
			sb.append("'100,00', ");
			sb.append("'5', ");
			sb.append("'50,00', ");
			sb.append("'35,00', ");
			sb.append("'585,00', ");
			sb.append("'ABERTA', ");
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
}