package br.com.locacar.action.componentes;

import java.awt.event.*;

/**
 * Classe respons�vel por for�ar que em campos espec�ficos seja digitado apenas valores num�ricos!
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