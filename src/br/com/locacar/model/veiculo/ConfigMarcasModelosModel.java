package br.com.locacar.model.veiculo;

import com.thoughtworks.xstream.annotations.*;
import java.util.*;

/**
 * Classe model para obtenção das marcas e dos modelos dos veículos!
 * @author Felipe Nascimento
 */
@XStreamAlias("marca")
public class ConfigMarcasModelosModel {
	@XStreamAsAttribute
	private String nomeMarca;
	
	@XStreamImplicit(itemFieldName="modelo")
	private List<String> listaModelos;
	
	public String getNomeMarca() {
		return nomeMarca;
	}
	
	public void setNomeMarca(String nomeMarca) {
		this.nomeMarca = nomeMarca;
	}
	
	public List<String> getListaModelo() {
		return listaModelos;
	}
	
	public void setListaModelo(List<String> listaModelos) {
		this.listaModelos = listaModelos;
	}
}