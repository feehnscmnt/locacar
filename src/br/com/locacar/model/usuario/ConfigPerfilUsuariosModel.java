package br.com.locacar.model.usuario;

import javax.xml.bind.annotation.*;

/**
 * Classe model para obtenção das informações do perfil dos usuários (logins)!
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