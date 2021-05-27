package br.com.locacar.model.modal;

import br.com.locacar.view.modal.BoxModalMensagens.nivelMensagens;

/**
 * Classe model para obten��o das informa��es das mensagens apresentadas aos usu�rios!
 * @author Felipe Nascimento
 */
public class MensagensModel {
	private String txt;
	private nivelMensagens nivelMsgns;
	
	public MensagensModel(String txt, nivelMensagens nivelMsgns) {
		this.txt = txt;
		this.nivelMsgns = nivelMsgns;
	}
	
	public MensagensModel(String txt) {
		this.txt = txt;
	}

	public String getText() {
		return txt;
	}
	
	public nivelMensagens getNivelMsgns() {
		return nivelMsgns;
	}
}