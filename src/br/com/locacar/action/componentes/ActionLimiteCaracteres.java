package br.com.locacar.action.componentes;

import javax.swing.text.*;

/**
 * Classe responsável por limitar em campos específicos a qtde de caracteres digitados!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class ActionLimiteCaracteres extends PlainDocument {
	int qtdeCaracteresInformados, qtdeCaracteresNecessarios = 11;
	public void insertString(int offs, String text, AttributeSet atSet) throws BadLocationException {
		if (text == null) return;
		String txt = getText(0, getLength());
		qtdeCaracteresInformados = txt.length() + text.length();
		if (qtdeCaracteresInformados <= qtdeCaracteresNecessarios)
			super.insertString(offs, text, atSet);
		else
			super.insertString(offs, "", atSet);
	}
}