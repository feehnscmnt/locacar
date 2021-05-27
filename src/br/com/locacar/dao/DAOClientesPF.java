package br.com.locacar.dao;

import br.com.locacar.model.clientepf.*;
import br.com.locacar.model.dao.*;
import org.apache.logging.log4j.*;
import java.sql.*;

/**
 * Classe dao responsável pelas tarefas no banco de dados relacionadas aos clientes pessoa física (cadastros, consultas, alterações e exclusões)!
 * @author Felipe Nascimento
 */
public class DAOClientesPF implements ClientesPF {
	private static final Logger LOG = LogManager.getLogger(DAOClientesPF.class.getName());
	private ConfigDao acessaBanco;
	private StringBuilder sb;
	
	@Override
	public ResultSet buscarPorCpf(String cpf) {
		acessaBanco = new DAOAccessManager();
		if (cpf.replaceAll("\\D", "").equals(""))
			LOG.info("Buscando clientes pf salvos no banco de dados...");
		else
			LOG.info("Buscando o CPF {} para consulta...", cpf);
		sb = new StringBuilder();
		sb.append("SELECT CODIGO, NOME, CPF, RG, CONTATO, ");
		sb.append("DATANASCIMENTO, CATEGCNH, NUMEROCNH, ");
		sb.append("EMAIL, ENDERECO, CEP, CIDADE, UF, BAIRRO, ");
		sb.append("COMPLEMENTO, IMAGEM FROM T_CLIENTESPF ");
		sb.append("WHERE CPF LIKE '%" + cpf + "%' AND STATUS = '1'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarTodos() {
		LOG.info("Buscando todos os clientes pf salvos no banco de dados...");
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_CLIENTESPF ");
		sb.append("WHERE STATUS = '1'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarParaLocacao(String cpf) {
		LOG.info("Buscando o CPF {} para locação...", cpf);
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_CLIENTESPF ");
		sb.append("WHERE CPF = '" + cpf + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarParaAlteracao(String codigo) {
		LOG.info("Buscando o cliente id {} para alteração...", codigo);
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_CLIENTESPF ");
		sb.append("WHERE CODIGO = '" + codigo + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet verificarSeExiste(String cpf) {
		LOG.info("Buscando o CPF {} para verificar se já existe...", cpf);
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_CLIENTESPF ");
		sb.append("WHERE CPF = '" + cpf + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public void salvar(ClientesPFModel cliente) {
		LOG.info("Salvando o cliente {}...", cliente.getNome());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("INSERT INTO T_CLIENTESPF ");
		sb.append("(NOME, CPF, RG, CONTATO, ");
		sb.append("DATANASCIMENTO, CATEGCNH, NUMEROCNH, ");
		sb.append("EMAIL, ENDERECO, CEP, CIDADE, UF, ");
		sb.append("BAIRRO, COMPLEMENTO, IMAGEM, STATUS) VALUES (");
		sb.append("'" + cliente.getNome() + "', ");
		sb.append("'" + cliente.getCpf() + "', ");
		sb.append("'" + cliente.getRg() + "', ");
		sb.append("'" + cliente.getContato() + "', ");
		sb.append("'" + cliente.getDataNasc() + "', ");
		sb.append("'" + cliente.getCategCnh() + "', ");
		sb.append("'" + cliente.getNumeroCnh() + "', ");
		sb.append("'" + cliente.getEmail() + "', ");
		sb.append("'" + cliente.getEndereco() + "', ");
		sb.append("'" + cliente.getCep() + "', ");
		sb.append("'" + cliente.getCidade() + "', ");
		sb.append("'" + cliente.getUf() + "', ");
		sb.append("'" + cliente.getBairro() + "', ");
		sb.append("'" + cliente.getComplemento() + "', ");
		sb.append("'" + cliente.getImagem() + "', ");
		sb.append("'1')");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
	
	@Override
	public void alterar(ClientesPFModel cliente) {
		LOG.info("Alterando o cliente {}...", cliente.getNome());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("UPDATE T_CLIENTESPF SET ");
		sb.append("NOME = '" + cliente.getNome() + "', ");
		sb.append("CPF = '" + cliente.getCpf() + "', ");
		sb.append("RG = '" + cliente.getRg() + "', ");
		sb.append("CONTATO = '" + cliente.getContato() + "', ");
		sb.append("DATANASCIMENTO = '" + cliente.getDataNasc() + "', ");
		sb.append("CATEGCNH = '" + cliente.getCategCnh() + "', ");
		sb.append("NUMEROCNH = '" + cliente.getNumeroCnh() + "', ");
		sb.append("EMAIL = '" + cliente.getEmail() + "', ");
		sb.append("ENDERECO = '" + cliente.getEndereco() + "', ");
		sb.append("CEP = '" + cliente.getCep() + "', ");
		sb.append("CIDADE = '" + cliente.getCidade() + "', ");
		sb.append("UF = '" + cliente.getUf() + "', ");
		sb.append("BAIRRO = '" + cliente.getBairro() + "', ");
		sb.append("COMPLEMENTO = '" + cliente.getComplemento() + "', ");
		sb.append("IMAGEM = '" + cliente.getImagem() + "', ");
		sb.append("STATUS = '1' ");
		sb.append("WHERE CODIGO = '" + cliente.getCod() + "'");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
	
	@Override
	public void excluir(ClientesPFModel cliente) {
		LOG.info("Excluindo o cliente {}...", cliente.getNome());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("UPDATE T_CLIENTESPF SET ");
		sb.append("STATUS = '0' ");
		sb.append("WHERE NOME = '" + cliente.getNome() + "'");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
}