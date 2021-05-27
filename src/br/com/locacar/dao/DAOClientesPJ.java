package br.com.locacar.dao;

import br.com.locacar.model.clientepj.*;
import br.com.locacar.model.dao.*;
import org.apache.logging.log4j.*;
import java.sql.*;

/**
 * Classe dao responsável pelas tarefas no banco de dados relacionadas aos clientes pessoa jurídica (cadastros, consultas, alterações e exclusões)!
 * @author Felipe Nascimento
 */
public class DAOClientesPJ implements ClientesPJ {
	private static final Logger LOG = LogManager.getLogger(DAOClientesPJ.class.getName());
	private ConfigDao acessaBanco;
	private StringBuilder sb;
	
	@Override
	public ResultSet buscarPorCpfCnpj(String cpfCnpj) {
		acessaBanco = new DAOAccessManager();
		if (cpfCnpj.replaceAll("\\D", "").equals(""))
			LOG.info("Buscando clientes pj salvos no banco de dados...");
		else
			LOG.info("Buscando o CPF/CNPJ {} para consulta...", cpfCnpj);
		sb = new StringBuilder();
		sb.append("SELECT CODIGO, NOME, RAZAOSOCIAL, CPFCNPJ, CONTATO, ");
		sb.append("INSCRICAOESTADUAL, ENDERECO, CEP, CIDADE, UF, ");
		sb.append("BAIRRO, COMPLEMENTO, EMAIL, NOMEMOTORISTA, NUMEROCNH, CATEGCNH ");
		sb.append("FROM T_CLIENTESPJ WHERE CPFCNPJ LIKE '%" + cpfCnpj + "%' AND STATUS = '1'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarTodos() {
		LOG.info("Buscando todos os clientes pj salvos no banco de dados...");
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_CLIENTESPJ ");
		sb.append("WHERE STATUS = '1'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarParaLocacao(String cpfCnpj) {
		LOG.info("Buscando o CPF/CNPJ {} para locação...", cpfCnpj);
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_CLIENTESPJ ");
		sb.append("WHERE CPFCNPJ = '" + cpfCnpj + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarParaAlteracao(String codigo) {
		LOG.info("Buscando o cliente id {} para alteração...", codigo);
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_CLIENTESPJ ");
		sb.append("WHERE CODIGO = '" + codigo + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet verificarSeExiste(String cpfCnpj) {
		LOG.info("Buscando o CPF/CNPJ {} para verificar se já existe...", cpfCnpj);
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_CLIENTESPJ ");
		sb.append("WHERE CPFCNPJ = '" + cpfCnpj + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public void salvar(ClientesPJModel cliente) {
		LOG.info("Salvando o cliente {}...", cliente.getNome());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("INSERT INTO T_CLIENTESPJ ");
		sb.append("(NOME, RAZAOSOCIAL, CPFCNPJ, CONTATO, ");
		sb.append("INSCRICAOESTADUAL, ENDERECO, CEP, CIDADE, ");
		sb.append("UF, BAIRRO, COMPLEMENTO, EMAIL, NOMEMOTORISTA, ");
		sb.append("NUMEROCNH, CATEGCNH, STATUS) VALUES (");
		sb.append("'" + cliente.getNome() + "', ");
		sb.append("'" + cliente.getRazaoSocial() + "', ");
		sb.append("'" + cliente.getCpfCnpj() + "', ");
		sb.append("'" + cliente.getContato() + "', ");
		sb.append("'" + cliente.getInscricaoEstadual() + "', ");
		sb.append("'" + cliente.getEndereco() + "', ");
		sb.append("'" + cliente.getCep() + "', ");
		sb.append("'" + cliente.getCidade() + "', ");
		sb.append("'" + cliente.getUf() + "', ");
		sb.append("'" + cliente.getBairro() + "', ");
		sb.append("'" + cliente.getComplemento() + "', ");
		sb.append("'" + cliente.getEmail() + "', ");
		sb.append("'" + cliente.getNomeMotorista() + "', ");
		sb.append("'" + cliente.getNumeroCnh() + "', ");
		sb.append("'" + cliente.getCategCnh() + "', ");
		sb.append("'1')");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
	
	@Override
	public void alterar(ClientesPJModel cliente) {
		LOG.info("Alterando o cliente {}...", cliente.getNome());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("UPDATE T_CLIENTESPJ SET ");
		sb.append("NOME = '" + cliente.getNome() + "', ");
		sb.append("RAZAOSOCIAL = '" + cliente.getRazaoSocial() + "', ");
		sb.append("CPFCNPJ = '" + cliente.getCpfCnpj() + "', ");
		sb.append("CONTATO = '" + cliente.getContato() + "', ");
		sb.append("INSCRICAOESTADUAL = '" + cliente.getInscricaoEstadual() + "', ");
		sb.append("ENDERECO = '" + cliente.getEndereco() + "', ");
		sb.append("CEP = '" + cliente.getCep() + "', ");
		sb.append("CIDADE = '" + cliente.getCidade() + "', ");
		sb.append("UF = '" + cliente.getUf() + "', ");
		sb.append("BAIRRO = '" + cliente.getBairro() + "', ");
		sb.append("COMPLEMENTO = '" + cliente.getComplemento() + "', ");
		sb.append("EMAIL = '" + cliente.getEmail() + "', ");
		sb.append("NOMEMOTORISTA = '" + cliente.getNomeMotorista() + "', ");
		sb.append("NUMEROCNH = '" + cliente.getNumeroCnh() + "', ");
		sb.append("CATEGCNH = '" + cliente.getCategCnh() + "', ");
		sb.append("STATUS = '1' ");
		sb.append("WHERE CODIGO = '" + cliente.getCod() + "'");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
	
	@Override
	public void excluir(ClientesPJModel cliente) {
		LOG.info("Excluindo o cliente {}...", cliente.getNome());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("UPDATE T_CLIENTESPJ SET ");
		sb.append("STATUS = '0' ");
		sb.append("WHERE NOME = '" + cliente.getNome() + "'");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
}