package br.com.locacar.model.locacao;

import br.com.locacar.model.veiculo.*;
import java.sql.*;

public interface Locacao {
	public void veiculoDisponibilizado(VeiculosModel veiculo, LocacaoModel locacao);
	public void veiculoAlugado(VeiculosModel veiculo, LocacaoModel locacao);
	public ResultSet buscarParaVisualizacao(String codigo);
	public void finalizaLocacao(LocacaoModel locacao);
	public ResultSet buscarPorNumero(String numero);
	public void salvar(LocacaoModel locacao);
	public ResultSet buscarTodas();
}