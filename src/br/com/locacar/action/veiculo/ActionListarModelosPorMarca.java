package br.com.locacar.action.veiculo;

import br.com.locacar.domain.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pelo preenchimento do combo de modelos dos veículos de acordo com a marca selecionada no combo de marcas!
 * @author Felipe Nascimento
 */
public abstract class ActionListarModelosPorMarca extends FocusAdapter {
	public void listarModeloPorMarca(JComboBox<Object> comboMarca, JComboBox<Object> comboModelo) {
		if (comboMarca.getSelectedItem().equals("CHEVROLET")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("CITROEN")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("FIAT")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("FORD")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("HONDA")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("HYUNDAI")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("IVECO")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("JAC MOTORS")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("JEEP")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("KIA")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("LAND ROVER")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("MITSUBISHI")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("NISSAN")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("PEUGEOT")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("RENAULT")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("TOYOTA")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		} else if (comboMarca.getSelectedItem().equals("VOLKSWAGEN")) {
			comboModelo.removeAllItems();
			PopularCombos.popularComboModeloPorMarca(comboMarca, comboModelo);
		}
	}
}