package br.com.locacar.util;

import br.com.locacar.model.email.*;
import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import javax.xml.bind.*;
import javax.swing.*;

/**
 * Classe responsável pela leitura do XML com as informações dos e-mails para configuração!
 * @author Felipe Nascimento
 */
public class ConfigEmailUtil {
	private static final Logger LOG = LogManager.getLogger(ConfigEmailUtil.class.getName());
	public static String mail_smtp_host, mail_smtp_socketFactory_port, mail_smtp_socketFactory_class, mail_smtp_auth, mail_smtp_port, mail_user, mail_password;
	private static ConfigEmailModel configuracao = new ConfigEmailModel();
	
	public static void carregarConfig() {
		try {
			configuracao = (ConfigEmailModel) XmlReadUtil.lerXmlConfigEnvioEmail(PathFilesUtil.getConfigFile("configEmailGmail.xml"));
			getConfigs(configuracao);
		} catch(JAXBException e) {
			LOG.error("O XML de configurações dos e-mails não foi localizado! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("xmlNaoEncontrado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	private static void getConfigs(ConfigEmailModel configuracao) {
		mail_smtp_host = configuracao.getMail_smtp_host();
		mail_smtp_socketFactory_port = configuracao.getMail_smtp_socketFactory_port();
		mail_smtp_socketFactory_class = configuracao.getMail_smtp_socketFactory_class();
		mail_smtp_auth = configuracao.getMail_smtp_auth();
		mail_smtp_port = configuracao.getMail_smtp_port();
		mail_user = configuracao.getMail_user();
		mail_password = configuracao.getMail_password();
	}
}