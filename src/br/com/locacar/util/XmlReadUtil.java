package br.com.locacar.util;

import com.thoughtworks.xstream.io.xml.*;
import br.com.locacar.model.clientepf.*;
import br.com.locacar.model.usuario.*;
import br.com.locacar.model.veiculo.*;
import br.com.locacar.model.email.*;
import br.com.locacar.model.horas.*;
import org.apache.logging.log4j.*;
import com.thoughtworks.xstream.*;
import br.com.locacar.model.dao.*;
import br.com.locacar.model.uf.*;
import javax.xml.bind.*;
import java.io.*;

/**
 * Classe responsável pela leitura dos XML's de configuração!
 * @author Felipe Nascimento
 */
public class XmlReadUtil {
	private static final Logger LOG = LogManager.getLogger(XmlReadUtil.class.getName());
	
	public static ConfigDaoModel lerXmlConfigDao(InputStream xml) throws JAXBException {
		LOG.info("Realizando a leitura do XML de configurações do banco de dados...");
		JAXBContext context = JAXBContext.newInstance(ConfigDaoModel.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		LOG.info("Leitura efetuada com sucesso!");
		return (ConfigDaoModel) unmarshaller.unmarshal(xml);
	}
	
	public static ConfigPerfilUsuariosModel lerXmlConfigPerfilUsuarios(InputStream xml) throws JAXBException {
		LOG.info("Realizando a leitura do XML de configurações dos perfis dos usuários...");
		JAXBContext context = JAXBContext.newInstance(ConfigPerfilUsuariosModel.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		LOG.info("Leitura efetuada com sucesso!");
		return (ConfigPerfilUsuariosModel) unmarshaller.unmarshal(xml);
	}
	
	public static ConfigModelosModel lerXmlConfigModelos(InputStream xml) throws JAXBException {
		LOG.info("Realizando a leitura do XML de configurações dos modelos dos veículos...");
		JAXBContext context = JAXBContext.newInstance(ConfigModelosModel.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		LOG.info("Leitura efetuada com sucesso!");
		return (ConfigModelosModel) unmarshaller.unmarshal(xml);
	}
	
	public static ListaMarcasModel lerXmlConfigMarcasModelos(InputStream xml) throws JAXBException, FileNotFoundException {
		LOG.info("Realizando a leitura dos XML's de configurações das marcas e dos modelos dos veículos...");
		XStream xStream = new XStream(new DomDriver());
		XStream.setupDefaultSecurity(xStream);
		xStream.allowTypes(new Class[] { ListaMarcasModel.class, ConfigMarcasModelosModel.class });
		xStream.processAnnotations(ListaMarcasModel.class);
		ListaMarcasModel marcas = (ListaMarcasModel) xStream.fromXML(xml);
		LOG.info("Leitura efetuada com sucesso!");
		return marcas;
	}
	
	public static ConfigEmailModel lerXmlConfigEnvioEmail(InputStream xml) throws JAXBException {
		LOG.info("Realizando a leitura do XML de configurações dos e-mails...");
		JAXBContext context = JAXBContext.newInstance(ConfigEmailModel.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		LOG.info("Leitura efetuada com sucesso!");
		return (ConfigEmailModel) unmarshaller.unmarshal(xml);
	}
	
	public static ConfigCategCnhModel lerXmlConfigCategCnh(InputStream xml) throws JAXBException {
		LOG.info("Realizando a leitura do XML de configurações de categorias da CNH...");
		JAXBContext context = JAXBContext.newInstance(ConfigCategCnhModel.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		LOG.info("Leitura efetuada com sucesso!");
		return (ConfigCategCnhModel) unmarshaller.unmarshal(xml);
	}
	
	public static ConfigUFModel lerXmlConfigUF(InputStream xml) throws JAXBException {
		LOG.info("Realizando a leitura do XML de configurações dos estados (UF's)...");
		JAXBContext context = JAXBContext.newInstance(ConfigUFModel.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		LOG.info("Leitura efetuada com sucesso!");
		return (ConfigUFModel) unmarshaller.unmarshal(xml);
	}
	
	public static ConfigHorasModel lerXmlConfigHoras(InputStream xml) throws JAXBException {
		LOG.info("Realizando a leitura do XML de configurações das horas...");
		JAXBContext context = JAXBContext.newInstance(ConfigHorasModel.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		LOG.info("Leitura efetuada com sucesso!");
		return (ConfigHorasModel) unmarshaller.unmarshal(xml);
	}
}