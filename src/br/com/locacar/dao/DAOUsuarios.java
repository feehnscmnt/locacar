package br.com.locacar.dao;

import br.com.locacar.model.usuario.*;
import br.com.locacar.model.dao.*;
import org.apache.logging.log4j.*;
import java.sql.*;

/**
 * Classe dao respons�vel pelas tarefas no banco de dados relacionadas aos usu�rios (cadastros, consultas, altera��es e exclus�es)!
 * @author Felipe Nascimento
 */
public class DAOUsuarios implements Usuarios {
	private static final Logger LOG = LogManager.getLogger(DAOUsuarios.class.getName());
	private ConfigDao acessaBanco;
	private StringBuilder sb;
	
	@Override
	public ResultSet buscarPorNome(String usuario) {
		acessaBanco = new DAOAccessManager();
		if (usuario.equals(""))
			LOG.info("Buscando usu�rios salvos no banco de dados...");
		else
			LOG.info("Buscando o usu�rio {} para consulta...", usuario);
		sb = new StringBuilder();
		sb.append("SELECT CODIGO, NOME, SENHA, ");
		sb.append("PERFIL, SITUACAO, STATUS ");
		sb.append("FROM T_USUARIOS WHERE NOME LIKE ");
		sb.append("'%" + usuario + "%' AND STATUS = '1'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarTodos() {
		LOG.info("Buscando todos os usu�rios salvos no banco de dados...");
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_USUARIOS ");
		sb.append("WHERE STATUS = '1'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarParaAlteracao(String codigo) {
		LOG.info("Buscando o usu�rio id {} para altera��o...", codigo);
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT * FROM T_USUARIOS ");
		sb.append("WHERE CODIGO = '" + codigo + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public ResultSet buscarParaAutenticar(UsuariosModel usuario) {
		LOG.info("Buscando o usu�rio {} para autentica��o...", usuario.getNome());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("SELECT CODIGO, NOME, ");
		sb.append("SENHA, PERFIL, SITUACAO, ");
		sb.append("STATUS FROM T_USUARIOS ");
		sb.append("WHERE NOME = '" + usuario.getNome() + "'");
		return acessaBanco.retornaQuery(String.valueOf(sb));
	}
	
	@Override
	public void salvar(UsuariosModel usuario) {
		LOG.info("Salvando o usu�rio {}...", usuario.getNome());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("INSERT INTO T_USUARIOS ");
		sb.append("(NOME, SENHA, PERFIL, ");
		sb.append("SITUACAO, STATUS) VALUES (");
		sb.append("'" + usuario.getNome() + "', ");
		sb.append("'" + usuario.getSenha() + "', ");
		sb.append("'" + usuario.getPerfil() + "', ");
		sb.append("'" + usuario.getSituacao() + "', ");
		sb.append("'1')");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
	
	@Override
	public void alterar(UsuariosModel usuario) {
		LOG.info("Alterando o usu�rio {}...", usuario.getNome());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("UPDATE T_USUARIOS SET ");
		sb.append("NOME = '" + usuario.getNome() + "', ");
		sb.append("SENHA = '" + usuario.getSenha() + "', ");
		sb.append("PERFIL = '" + usuario.getPerfil() + "', ");
		sb.append("SITUACAO = '" + usuario.getSituacao() + "', ");
		sb.append("STATUS = '1' ");
		sb.append("WHERE CODIGO = '" + usuario.getCod() + "'");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
	
	@Override
	public void excluir(UsuariosModel usuario) {
		LOG.info("Excluindo o usu�rio {}...", usuario.getNome());
		acessaBanco = new DAOAccessManager();
		sb = new StringBuilder();
		sb.append("UPDATE T_USUARIOS SET ");
		sb.append("STATUS = '0' ");
		sb.append("WHERE NOME = '" + usuario.getNome() + "'");
		acessaBanco.executaQuery(String.valueOf(sb));
	}
}