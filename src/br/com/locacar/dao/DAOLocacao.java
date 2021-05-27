package br.com.locacar.dao;

import br.com.locacar.model.locacao.*;
import br.com.locacar.model.veiculo.*;
import br.com.locacar.model.dao.*;
import org.apache.logging.log4j.*;
import java.sql.*;

/**
 * Classe dao responsável pelas tarefas no banco de dados relacionadas às locações (cadastros, consultas, alterações e exclusões)!
 * @author Felipe Nascimento
 */
public class DAOLocacao implements Locacao {
	private static final Logger LOG = LogManager.getLogger(DAOLocacao.class.getName());
	private ConfigDao acessaBanco;
	private StringBuilder sb;
	
	@Override
	public ResultSet buscarPorNumero(String numero) {
		acessaBanco = new DAOAccessManager();
		if (numero.equals(""))
			LOG.info("Buscando locações salvas no banco de dados...");
		else
			LOG.info("Buscando a locação {} para consulta...", numero);
		sb = new StringBuilder();
		sb.append("SELECT CODIGO, SITUACAO, NUMEROLOCACAO, CPFCNPJ, NOME, ENDERECO, CIDADE, BAIRRO, ");
		sb.append("EMAIL, PLACA, MARCA, MODELO, COMBUSTIVEL, COR, ANO, RENAVAM, KILOMETRAGEM, ");
		sb.append("DATALOCACAO, HORALOCACAO, DATARETORNOLOCACAO, HORARETORNOLOCACAO, ");
		sb.append("LOCALENTREGA, OBSERVACOES, VALORDIA, QTDEDIAS, VALORTAXASERVICO, VALORPROTECAO, VALORTOTAL, ");
		sb.append("STATUS FROM T_LOCACAO WHERE NUMEROLOCACAO LIKE '%" + numero + "%' AND STATUS = '1'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarTodas() {
		LOG.info("Buscando todas as locações salvas no banco de dados...");
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_LOCACAO ");
		sb.append("WHERE STATUS = '1'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public void veiculoAlugado(VeiculosModel veiculo, LocacaoModel locacao) {
		LOG.info("Alterando a situação do veículo {}...", veiculo.getModelo());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("UPDATE T_VEICULOS SET ");
		sb.append("SITUACAO = '" + veiculo.getSituacao() + "', ");
		sb.append("DATALOCACAO = '" + locacao.getDataLocacao() + "' ");
		sb.append("WHERE PLACA = '" + locacao.getPlaca() + "' AND STATUS = '1'");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
	
	@Override
	public void veiculoDisponibilizado(VeiculosModel veiculo, LocacaoModel locacao) {
		LOG.info("Alterando a situação do veículo da placa {}...", locacao.getPlaca());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("UPDATE T_VEICULOS SET ");
		sb.append("SITUACAO = '" + veiculo.getSituacao() + "', ");
		sb.append("DATALOCACAO = '" + locacao.getDataLocacao() + "' ");
		sb.append("WHERE PLACA = '" + locacao.getPlaca() + "' AND STATUS = '1'");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
	
	@Override
	public void finalizaLocacao(LocacaoModel locacao) {
		LOG.info("Alterando a situação da locação {}...", locacao.getNumeroLocacao());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("UPDATE T_LOCACAO SET ");
		sb.append("SITUACAO = '" + locacao.getSituacao() + "' ");
		sb.append("WHERE NUMEROLOCACAO = '" + locacao.getNumeroLocacao() + "' AND STATUS = '1'");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarParaVisualizacao(String codigo) {
		LOG.info("Buscando a locação id {} para visualização...", codigo);
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_LOCACAO ");
		sb.append("WHERE CODIGO = '" + codigo + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public void salvar(LocacaoModel locacao) {
		LOG.info("Salvando a locação {}...", locacao.getNumeroLocacao());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("INSERT INTO T_LOCACAO (NUMEROLOCACAO, CPFCNPJ, NOME, ENDERECO, CIDADE, ");
		sb.append("BAIRRO, EMAIL, PLACA, MARCA, MODELO, COMBUSTIVEL, COR, ANO, RENAVAM, KILOMETRAGEM, ");
		sb.append("DATALOCACAO, HORALOCACAO, DATARETORNOLOCACAO, HORARETORNOLOCACAO, ");
		sb.append("LOCALENTREGA, OBSERVACOES, VALORDIA, QTDEDIAS, VALORTAXASERVICO, ");
		sb.append("VALORPROTECAO, VALORTOTAL, SITUACAO, STATUS) VALUES (");
		sb.append("'" + locacao.getNumeroLocacao() + "', ");
		sb.append("'" + locacao.getCpfCnpj() + "', ");
		sb.append("'" + locacao.getNome() + "', ");
		sb.append("'" + locacao.getEndereco() + "', ");
		sb.append("'" + locacao.getCidade() + "', ");
		sb.append("'" + locacao.getBairro() + "', ");
		sb.append("'" + locacao.getEmail() + "', ");
		sb.append("'" + locacao.getPlaca() + "', ");
		sb.append("'" + locacao.getMarca() + "', ");
		sb.append("'" + locacao.getModelo() + "', ");
		sb.append("'" + locacao.getCombustivel() + "', ");
		sb.append("'" + locacao.getCor() + "', ");
		sb.append("'" + locacao.getAno() + "', ");
		sb.append("'" + locacao.getRenavam() + "', ");
		sb.append("'" + locacao.getKilometragem() + "', ");
		sb.append("'" + locacao.getDataLocacao() + "', ");
		sb.append("'" + locacao.getHoraLocacao() + "', ");
		sb.append("'" + locacao.getDataRetornoLocacao() + "', ");
		sb.append("'" + locacao.getHoraRetornoLocacao() + "', ");
		sb.append("'" + locacao.getLocalEntrega() + "', ");
		sb.append("'" + locacao.getObservacoes() + "', ");
		sb.append("'" + locacao.getValorDia() + "', ");
		sb.append("'" + locacao.getQtdeDias() + "', ");
		sb.append("'" + locacao.getValorTaxaServico() + "', ");
		sb.append("'" + locacao.getValorProtecao() + "', ");
		sb.append("'" + locacao.getValorTotal() + "', ");
		sb.append("'" + locacao.getSituacao() + "', ");
		sb.append("'1')");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
}