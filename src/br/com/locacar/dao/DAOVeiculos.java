
package br.com.locacar.dao;

import br.com.locacar.model.veiculo.*;
import br.com.locacar.model.dao.*;
import org.apache.logging.log4j.*;
import java.sql.*;

/**
 * Classe dao responsável pelas tarefas no banco de dados relacionadas aos veículos (cadastros, consultas, alterações e exclusões)!
 * @author Felipe Nascimento
 */
public class DAOVeiculos implements Veiculos {
	private static final Logger LOG = LogManager.getLogger(DAOVeiculos.class.getName());
	private ConfigDao acessaBanco;
	private StringBuilder sb;
	
	@Override
	public ResultSet buscarPorPlaca(String placa) {
		acessaBanco = new DAOAccessManager();
		if (placa.replaceAll("\\D", "").equals(""))
			LOG.info("Buscando veículos salvos no banco de dados...");
		else
			LOG.info("Buscando o veículo da placa {} para consulta...", placa);
		sb = new StringBuilder();
		sb.append("SELECT CODIGO, SITUACAO, DATALOCACAO, PLACA, MARCA, ");
		sb.append("MODELO, VERSAO, ANOFABRICACAO, ANOMODELO, COR, ");
		sb.append("COMBUSTIVEL, TRANSMISSAO, RENAVAM, PORTAS, ");
		sb.append("ALARME, TRAVAS, SENSORRE, BANCOSCOURO, FREIOSABS, ");
		sb.append("AIRBAGS, CAMERARE, MULTIMIDIA, BANCOSREGULAVEIS, ");
		sb.append("VIDROSELETRICOS, DIRECAOHIDRAULICA, DIRECAOELETRICA ");
		sb.append("FROM T_VEICULOS WHERE PLACA LIKE ");
		sb.append("'%" + placa + "%' AND STATUS = '1'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarTodos() {
		LOG.info("Buscando todos os veículos salvos no banco de dados...");
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_VEICULOS ");
		sb.append("WHERE STATUS = '1'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarParaAlteracao(String codigo) {
		LOG.info("Buscando o veículo id {} para alteração...", codigo);
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_VEICULOS ");
		sb.append("WHERE CODIGO = '" + codigo + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarParaLocacao(String placa) {
		LOG.info("Buscando o veículo da placa {} para locação...", placa);
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_VEICULOS WHERE SITUACAO = 'DISPONÍVEL' ");
		sb.append("AND PLACA = '" + placa + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet verificarSeExiste(String placa) {
		LOG.info("Buscando o veículo da placa {} para verificar se já existe...", placa);
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_VEICULOS ");
		sb.append("WHERE PLACA = '" + placa + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public void salvar(VeiculosModel veiculo) {
		LOG.info("Salvando o veículo {}...", veiculo.getModelo());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("INSERT INTO T_VEICULOS ");
		sb.append("(PLACA, MARCA, MODELO, VERSAO, ");
		sb.append("ANOFABRICACAO, ANOMODELO, COR, COMBUSTIVEL, ");
		sb.append("TRANSMISSAO, RENAVAM, PORTAS, ALARME, TRAVAS, ");
		sb.append("SENSORRE, BANCOSCOURO, FREIOSABS, AIRBAGS, CAMERARE, ");
		sb.append("MULTIMIDIA, BANCOSREGULAVEIS, VIDROSELETRICOS, ");
		sb.append("DIRECAOHIDRAULICA, DIRECAOELETRICA, SITUACAO, STATUS) VALUES (");
		sb.append("'" + veiculo.getPlaca() + "', ");
		sb.append("'" + veiculo.getMarca() + "', ");
		sb.append("'" + veiculo.getModelo() + "', ");
		sb.append("'" + veiculo.getVersao() + "', ");
		sb.append("'" + veiculo.getAnoFab() + "', ");
		sb.append("'" + veiculo.getAnoMod() + "', ");
		sb.append("'" + veiculo.getCor() + "', ");
		sb.append("'" + veiculo.getCombustivel() + "', ");
		sb.append("'" + veiculo.getTransmissao() + "', ");
		sb.append("'" + veiculo.getRenavam() + "', ");
		sb.append("'" + veiculo.getPortas() + "', ");
		sb.append("'" + veiculo.getAlarme() + "', ");
		sb.append("'" + veiculo.getTravas() + "', ");
		sb.append("'" + veiculo.getSensorRe() + "', ");
		sb.append("'" + veiculo.getBancosCouro() + "', ");
		sb.append("'" + veiculo.getFreiosAbs() + "', ");
		sb.append("'" + veiculo.getAirbags() + "', ");
		sb.append("'" + veiculo.getCameraRe() + "', ");
		sb.append("'" + veiculo.getMultimidia() + "', ");
		sb.append("'" + veiculo.getBancosRegulaveis() + "', ");
		sb.append("'" + veiculo.getVidrosEletricos() + "', ");
		sb.append("'" + veiculo.getDirecaoHidraulica() + "', ");
		sb.append("'" + veiculo.getDirecaoEletrica() + "', ");
		sb.append("'" + veiculo.getSituacao() + "', ");
		sb.append("'1')");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
	
	@Override
	public void alterar(VeiculosModel veiculo) {
		LOG.info("Alterando o veículo {}...", veiculo.getModelo());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("UPDATE T_VEICULOS SET ");
		sb.append("PLACA = '" + veiculo.getPlaca() + "', ");
		sb.append("MARCA = '" + veiculo.getMarca() + "', ");
		sb.append("MODELO = '" + veiculo.getModelo() + "', ");
		sb.append("VERSAO = '" + veiculo.getVersao() + "', ");
		sb.append("ANOFABRICACAO = '" + veiculo.getAnoFab() + "', ");
		sb.append("ANOMODELO = '" + veiculo.getAnoMod() + "', ");
		sb.append("COR = '" + veiculo.getCor() + "', ");
		sb.append("COMBUSTIVEL = '" + veiculo.getCombustivel() + "', ");
		sb.append("TRANSMISSAO = '" + veiculo.getTransmissao() + "', ");
		sb.append("RENAVAM = '" + veiculo.getRenavam() + "', ");
		sb.append("PORTAS = '" + veiculo.getPortas() + "', ");
		sb.append("ALARME = '" + veiculo.getAlarme() + "', ");
		sb.append("TRAVAS = '" + veiculo.getTravas() + "', ");
		sb.append("SENSORRE = '" + veiculo.getSensorRe() + "', ");
		sb.append("BANCOSCOURO = '" + veiculo.getBancosCouro() + "', ");
		sb.append("FREIOSABS = '" + veiculo.getFreiosAbs() + "', ");
		sb.append("AIRBAGS = '" + veiculo.getAirbags() + "', ");
		sb.append("CAMERARE = '" + veiculo.getCameraRe() + "', ");
		sb.append("MULTIMIDIA = '" + veiculo.getMultimidia() + "', ");
		sb.append("BANCOSREGULAVEIS = '" + veiculo.getBancosRegulaveis() + "', ");
		sb.append("VIDROSELETRICOS = '" + veiculo.getVidrosEletricos() + "', ");
		sb.append("DIRECAOHIDRAULICA = '" + veiculo.getDirecaoHidraulica() + "', ");
		sb.append("DIRECAOELETRICA = '" + veiculo.getDirecaoEletrica() + "', ");
		sb.append("STATUS = '1' ");
		sb.append("WHERE CODIGO = '" + veiculo.getCod() + "'");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
	
	@Override
	public void excluir(VeiculosModel veiculo) {
		LOG.info("Excluindo o veículo {}...", veiculo.getModelo());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("UPDATE T_VEICULOS SET ");
		sb.append("STATUS = '0' ");
		sb.append("WHERE PLACA = '" + veiculo.getPlaca() + "'");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
}