package br.com.locacar.action;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import com.google.gson.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

/**
 * Classe action responsável pela busca das empresas (CNPJ's) através da API Receitaws!
 * @author Felipe Nascimento
 */
public abstract class ActionBuscaEmpresa extends FocusAdapter {
	private static final Logger LOG = LogManager.getLogger(ActionBuscaEmpresa.class.getName());
	
	public void buscar(JFormattedTextField txtCnpj, JTextField txtNome, JTextField txtRazaoSocial, JTextField txtEmail, JTextField txtEndereco, JFormattedTextField txtCep, JTextField txtCidade, JTextField txtBairro, JComboBox<Object> jcbUf, JTextField txtTelCel, JRootPane rootPane) {
		String cnpj = txtCnpj.getText().replaceAll("\\D", "");
		if (cnpj.equals("") | cnpj.length() < 14) {
			LOG.info("O CNPJ não foi informado ou falta caracteres!");
			Modal.mensagem(new MensagensModel(Bundle.getString("cnpjNaoInformado"), nivelMensagens.ERRO), rootPane);
			txtCnpj.requestFocus();
		} else if (!ValidationsUtil.validaCnpj(cnpj)) {
			LOG.info("O CNPJ informado é inválido!");
			Modal.mensagem(new MensagensModel(Bundle.getString("cnpjInvalido"), nivelMensagens.ERRO), rootPane);
			txtCnpj.requestFocus();
		} else {
			try {
				URL url = new URL("https://www.receitaws.com.br/v1/cnpj/".concat(cnpj));
				HttpURLConnection request = (HttpURLConnection) url.openConnection();
		        request.connect();
		        JsonParser parser = new JsonParser();
		        JsonElement element = parser.parse(new InputStreamReader((InputStream) request.getContent()));
		        JsonObject object = element.getAsJsonObject();
	            txtNome.setText(object.get("fantasia").getAsString());
	            txtRazaoSocial.setText(object.get("nome").getAsString());
	            txtEmail.setText(object.get("email").getAsString());
	            txtEndereco.setText(object.get("logradouro").getAsString() + ", " + object.get("numero").getAsString());
	            txtCep.setText(object.get("cep").getAsString().replaceAll("\\D", ""));
	            txtCidade.setText(object.get("municipio").getAsString());
                txtBairro.setText(object.get("bairro").getAsString());
                jcbUf.setSelectedItem(object.get("uf").getAsString());
                txtTelCel.requestFocus();
			} catch(Exception e) {
				LOG.error("Erro: {}", e.getMessage());
				throw new RuntimeException(e);
			}
		}
	}
}