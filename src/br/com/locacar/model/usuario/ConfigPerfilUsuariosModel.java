package br.com.locacar.model.usuario;

import javax.xml.bind.annotation.*;

/**
 * Classe model para obten��o das informa��es do perfil dos usu�rios (logins)!
 * @author Felipe Nascimento
 */
@XmlRootElement(name="profile")
public class ConfigPerfilUsuariosModel {
	private String[] tipo;
	
	@XmlElement(name="tipo")
	public String[] getTipo() {
		return tipo;
	}
	
	public void setTipo(String[] tipo) {
		this.tipo = tipo;
	}
}