package br.com.locacar.model.veiculo;

import javax.xml.bind.annotation.*;

/**
 * Classe model para obten��o dos modelos dos ve�culos e cria��o de um array!
 * @author Felipe Nascimento
 */
@XmlRootElement(name="modelos")
public class ConfigModelosModel {
	private String[] modelo;
	
	@XmlElement(name="modelo")
	public String[] getModelo() {
		return modelo;
	}
	
	public void setModelo(String[] modelo) {
		this.modelo = modelo;
	}
}