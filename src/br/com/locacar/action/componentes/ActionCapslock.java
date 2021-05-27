package br.com.locacar.action.componentes;

import javax.swing.text.*;

/**
 * Classe respons�vel por for�ar que os caracteres dos campos de texto fiquem sempre em caixa alta!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class ActionCapslock extends PlainDocument {
	public void insertString(int offs, String text, AttributeSet atSet) throws BadLocationException {
		super.insertString(offs, text.toUpperCase(), atSet);
	}
}