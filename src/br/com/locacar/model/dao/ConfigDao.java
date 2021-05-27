package br.com.locacar.model.dao;

import java.sql.*;

public interface ConfigDao {
	public ResultSet retornaQuery(String query);
	public void executaQuery(String query);
}