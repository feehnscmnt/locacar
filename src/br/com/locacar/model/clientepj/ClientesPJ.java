package br.com.locacar.model.clientepj;

import java.sql.*;

public interface ClientesPJ {
	public ResultSet buscarParaAlteracao(String codigo);
	public ResultSet verificarSeExiste(String cpfCnpj);
	public ResultSet buscarParaLocacao(String cpfCnpj);
	public ResultSet buscarPorCpfCnpj(String cpfCnpj);
	public void alterar(ClientesPJModel cliente);
	public void excluir(ClientesPJModel cliente);
	public void salvar(ClientesPJModel cliente);
	public ResultSet buscarTodos();
}