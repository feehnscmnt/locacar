package br.com.locacar.model.clientepf;

import java.sql.*;

public interface ClientesPF {
	public ResultSet buscarParaAlteracao(String codigo);
	public ResultSet verificarSeExiste(String cpf);
	public ResultSet buscarParaLocacao(String cpf);
	public void alterar(ClientesPFModel cliente);
	public void excluir(ClientesPFModel cliente);
	public void salvar(ClientesPFModel cliente);
	public ResultSet buscarPorCpf(String cpf);
	public ResultSet buscarTodos();
}