package br.com.locacar.model.veiculo;

import java.sql.*;

public interface Veiculos {
	public ResultSet buscarParaAlteracao(String codigo);
	public ResultSet verificarSeExiste(String placa);
	public ResultSet buscarParaLocacao(String placa);
	public ResultSet buscarPorPlaca(String placa);
	public void alterar(VeiculosModel veiculo);
	public void excluir(VeiculosModel veiculo);
	public void salvar(VeiculosModel veiculo);
	public ResultSet buscarTodos();
}