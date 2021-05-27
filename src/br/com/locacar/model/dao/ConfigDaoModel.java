package br.com.locacar.model.dao;

import javax.xml.bind.annotation.*;

/**
 * Classe model para obtenção das informações dos acessos ao banco de dados!
 * @author Felipe Nascimento
 */
@XmlRootElement(name="access")
public class ConfigDaoModel {
	private String driver, acesso, login, senha;
	
	@XmlElement(name="driver")
	public String getDriver() {
		return driver;
	}
	
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	@XmlElement(name="acesso")
	public String getAcesso() {
		return acesso;
	}

	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}
	
	@XmlElement(name="login")
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	@XmlElement(name="senha")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}