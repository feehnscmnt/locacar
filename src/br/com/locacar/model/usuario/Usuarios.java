package br.com.locacar.model.usuario;

import java.sql.*;

public interface Usuarios {
	public ResultSet buscarParaAutenticar(UsuariosModel usuario);
	public ResultSet buscarParaAlteracao(String codigo);
	public ResultSet buscarPorNome(String usuario);
	public void alterar(UsuariosModel usuario);
	public void excluir(UsuariosModel usuario);
	public void salvar(UsuariosModel usuario);
	public ResultSet buscarTodos();
}