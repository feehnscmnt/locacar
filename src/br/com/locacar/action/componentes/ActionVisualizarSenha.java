package br.com.locacar.action.componentes;

import java.awt.event.*;
import javax.swing.*;

/**
 * Classe responsável por apresentar (ou não) a senha informada no campo 'senha'!
 * @author Felipe Nascimento
 */
public abstract class ActionVisualizarSenha implements ActionListener {
	public void visualizaSenha(JCheckBox chBoxVisualizaSenha, JPasswordField txtSenha) {
		if(chBoxVisualizaSenha.isSelected()) {
            txtSenha.setEchoChar('\u0000');
        } else {
            txtSenha.setEchoChar('*');
        }
	}
}