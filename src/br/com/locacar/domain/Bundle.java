package br.com.locacar.domain;

import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

/**
 * Classe responsável pela leitura do properties contendo as chaves (keys) com as mensagens aos usuários!
 * @author Felipe Nascimento
 */
public class Bundle {
	private static final Logger LOG = LogManager.getLogger(Bundle.class.getName());
	private static ResourceBundle BUNDLE;
	
	static {
		BUNDLE = ResourceBundle.getBundle("br/com/locacar/files/bundle/msgns", new Locale("pt", "BR"));
	}
	
	public static String getString(String key) {
		try {
			return BUNDLE.getString(key);
		} catch(MissingResourceException e) {
			LOG.error("Erro: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("chaveNaoLocalizada", key)).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
			return "Chave: ".concat(key);
		} catch(NullPointerException e) {
			LOG.error("Erro: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("erroSistema " + e.getMessage().toUpperCase())).getText(), "ERRO", JOptionPane.ERROR_MESSAGE);
			return "";
		}
	}
	
	public static String getString(String key, Object ... params) {
		String str = BUNDLE.getString(key);
		MessageFormat format = new MessageFormat(str);
		return format.format(params);
	}
}