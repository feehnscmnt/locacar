package br.com.locacar.action;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

/**
 * Classe action responsável pela busca dos CEP's através da API Viacep!
 * @author Felipe Nascimento
 */
public abstract class ActionBuscaCep extends FocusAdapter {
	private static final Logger LOG = LogManager.getLogger(ActionBuscaCep.class.getName());
	
	public void buscar(JFormattedTextField txtCep, JTextField txtEndereco, JTextField txtBairro, JTextField txtCidade, JComboBox<Object> jcbUf, JTextField txtComplemento, JRootPane rootPane) {
		String json, cep = txtCep.getText().replaceAll("\\D", "");
		if (cep.equals("") | cep.length() < 8) {
			LOG.info("O CEP não foi informado ou falta caracteres!");
			Modal.mensagem(new MensagensModel(Bundle.getString("cepNaoInformado"), nivelMensagens.ERRO), rootPane);
			txtCep.setText("");
			txtCep.requestFocus();
		} else {
			try {
				URL url = new URL("http://viacep.com.br/ws/".concat(cep).concat("/json"));
				URLConnection urlCon = url.openConnection();
	            InputStream is = urlCon.getInputStream();
	            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	            StringBuilder sb = new StringBuilder();
	            br.lines().forEach(l -> sb.append(l.trim()));
	            json = sb.toString();
	            if (json.equals("{\"erro\": true}")) {
	            	LOG.info("O CEP informado não foi loclizado!");
	    			Modal.mensagem(new MensagensModel(Bundle.getString("cepNaoEncontrado"), nivelMensagens.ERRO), rootPane);
	    			txtCep.requestFocus();
	            } else {
	            	json = sb.toString();
	                json = json.replaceAll("[{},:]", "");
	                json = json.replaceAll("\"", "\n");
	                String array[] = new String[30];
	                array = json.split("\n");
	                txtEndereco.setText(array[7]);
	                txtBairro.setText(array[15]);
	                txtCidade.setText(array[19]);
	                jcbUf.setSelectedItem(array[23]);
	                txtComplemento.requestFocus();
	            }
			} catch(Exception e) {
				LOG.error("Erro: {}", e.getMessage());
				throw new RuntimeException(e);
			}
		}
	}
}