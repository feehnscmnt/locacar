package br.com.locacar.model.clientepf;

import javax.xml.bind.annotation.*;

/**
 * Classe model para obten��o das informa��es da categoria da CNH dos clientes pessoa f�sica!
 * @author Felipe Nascimento
 */
@XmlRootElement(name="cnh")
public class ConfigCategCnhModel {
	private String[] categoria;
	
	@XmlElement(name="categoria")
	public String[] getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String[] categoria) {
		this.categoria = categoria;
	}
}