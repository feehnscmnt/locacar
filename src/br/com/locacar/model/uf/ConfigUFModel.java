package br.com.locacar.model.uf;

import javax.xml.bind.annotation.*;

/**
 * Classe model para obten��o das informa��es dos estados (UF)!
 * @author Felipe Nascimento
 */
@XmlRootElement(name="ufs")
public class ConfigUFModel {
	private String[] uf;
	
	@XmlElement(name="uf")
	public String[] getUF() {
		return uf;
	}
	
	public void setUF(String[] uf) {
		this.uf = uf;
	}
}