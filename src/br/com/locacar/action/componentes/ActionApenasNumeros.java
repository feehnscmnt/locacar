package br.com.locacar.action.componentes;

import java.awt.event.*;

/**
 * Classe responsável por forçar que em campos específicos seja digitado apenas valores numéricos!
 * @author Felipe Nascimento
 */
public abstract class ActionApenasNumeros extends KeyAdapter {
	public void valoresNumericos(KeyEvent e) {
		String caracteres = "0123456789";
		if (!caracteres.contains(e.getKeyChar() + "")) {
			e.consume();
		}
	}
}