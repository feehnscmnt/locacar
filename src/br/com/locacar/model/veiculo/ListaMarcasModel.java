package br.com.locacar.model.veiculo;

import com.thoughtworks.xstream.annotations.*;

import java.util.*;

/**
 * Classe list para obtenção das marcas dos veículos!
 * @author Felipe Nascimento
 */
@XStreamAlias("infosVeiculos")
public class ListaMarcasModel {
	@XStreamImplicit
	private List<ConfigMarcasModelosModel> marcas;
	
	public ListaMarcasModel() {}
	
	public ListaMarcasModel(List<ConfigMarcasModelosModel> marcas) {
		this.marcas = marcas;
	}
	
	public List<ConfigMarcasModelosModel> getMarcas() {
		return marcas;
	}
	
	public void setMarcas(List<ConfigMarcasModelosModel> marcas) {
		this.marcas = marcas;
	}
}