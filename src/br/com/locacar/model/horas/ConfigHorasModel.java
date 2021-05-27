package br.com.locacar.model.horas;

import javax.xml.bind.annotation.*;

/**
 * Classe model para obtenção das informações das horas!
 * @author Felipe Nascimento
 */
@XmlRootElement(name="horas")
public class ConfigHorasModel {
	private String[] hora;
	
	@XmlElement(name="hora")
	public String[] getHora() {
		return hora;
	}
	
	public void setHora(String[] hora) {
		this.hora = hora;
	}
}