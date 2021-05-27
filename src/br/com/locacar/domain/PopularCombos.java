package br.com.locacar.domain;

import br.com.locacar.model.clientepf.*;
import br.com.locacar.model.usuario.*;
import br.com.locacar.model.veiculo.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.model.horas.*;
import org.apache.logging.log4j.*;
import br.com.locacar.model.uf.*;
import br.com.locacar.util.*;
import de.humatic.dsj.*;
import javax.xml.bind.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

/**
 * Classe respons�vel pela leitura dos XML's com as informa��es para preenchimento dos combos e pela obten��o dos dispositivos de imagem do computador utilizado!
 * @author Felipe Nascimento
 */
public class PopularCombos {
	private static final Logger LOG = LogManager.getLogger(PopularCombos.class.getName());
	static ConfigPerfilUsuariosModel configPerfilUsuariosModel = new ConfigPerfilUsuariosModel();
	static ConfigModelosModel configModelosModel = new ConfigModelosModel();
	static ConfigCategCnhModel configCategCnhModel = new ConfigCategCnhModel();
	static ConfigUFModel configUFModel = new ConfigUFModel();
	static ConfigHorasModel configHorasModel = new ConfigHorasModel();
	
	public static void popularComboPerfilUsers(JComboBox<Object> comboPerfis) {
		try {
			configPerfilUsuariosModel = (ConfigPerfilUsuariosModel) XmlReadUtil.lerXmlConfigPerfilUsuarios(PathFilesUtil.getConfigFile("configPerfilUsuarios.xml"));
			List<String> perfis = Arrays.asList(configPerfilUsuariosModel.getTipo());
			perfis.forEach(perfil -> comboPerfis.addItem(perfil));
		} catch(JAXBException e) {
			LOG.error("O XML de configura��es de perfis dos usu�rios n�o foi localizado! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("xmlNaoEncontrado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void popularCombosMarcaModelo(JComboBox<Object> comboMarca, JComboBox<Object> comboModelo) {
		try {
			ListaMarcasModel marcas = XmlReadUtil.lerXmlConfigMarcasModelos(PathFilesUtil.getConfigFile("configMarcasModelos.xml"));
			configModelosModel = (ConfigModelosModel) XmlReadUtil.lerXmlConfigModelos(PathFilesUtil.getConfigFile("configModelos.xml"));
			List<String> modelos = Arrays.asList(configModelosModel.getModelo());
			for (ConfigMarcasModelosModel marca : marcas.getMarcas()) {
				comboMarca.addItem(marca.getNomeMarca());
			}
			modelos.forEach(modelo -> comboModelo.addItem(modelo));
		} catch(JAXBException | FileNotFoundException e) {
			LOG.error("Os XML's de configura��es de marcas e modelos dos ve�culos n�o foram localizados! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("xmlNaoEncontrado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void popularComboModeloPorMarca(JComboBox<Object> comboMarca, JComboBox<Object> comboModelo) {
		try {
			ListaMarcasModel marcas = XmlReadUtil.lerXmlConfigMarcasModelos(PathFilesUtil.getConfigFile("configMarcasModelos.xml"));
			for (ConfigMarcasModelosModel marca : marcas.getMarcas()) {
				if (comboMarca.getSelectedItem().equals(marca.getNomeMarca())) {
					for (String modelo : marca.getListaModelo()) {
						comboModelo.addItem(modelo);
					}
				}
			}
		} catch(JAXBException | FileNotFoundException e) {
			LOG.error("O XML de configura��es de marcas e modelos dos ve�culos n�o foi localizado! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("xmlNaoEncontrado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void popularComboCategCnh(JComboBox<Object> comboCategCnh) {
		try {
			configCategCnhModel = (ConfigCategCnhModel) XmlReadUtil.lerXmlConfigCategCnh(PathFilesUtil.getConfigFile("configCategCnh.xml"));
			List<String> categorias = Arrays.asList(configCategCnhModel.getCategoria());
			categorias.forEach(categoria -> comboCategCnh.addItem(categoria));
		} catch(JAXBException e) {
			LOG.error("O XML de configura��es de categorias da CNH n�o foi localizado! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("xmlNaoEncontrado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void popularComboUF(JComboBox<Object> comboUF) {
		try {
			configUFModel = (ConfigUFModel) XmlReadUtil.lerXmlConfigUF(PathFilesUtil.getConfigFile("configUF.xml"));
			List<String> ufs = Arrays.asList(configUFModel.getUF());
			ufs.forEach(uf -> comboUF.addItem(uf));
		} catch(JAXBException e) {
			LOG.error("O XML de configura��es dos estados (UF's) n�o foi localizado! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("xmlNaoEncontrado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void popularComboHoras(JComboBox<Object> comboHoraLocacao, JComboBox<Object> comboHoraRetornoLocacao) {
		try {
			configHorasModel = (ConfigHorasModel) XmlReadUtil.lerXmlConfigHoras(PathFilesUtil.getConfigFile("configHoras.xml"));
			List<String> horas = Arrays.asList(configHorasModel.getHora());
			horas.forEach(hora -> comboHoraLocacao.addItem(hora));
			horas.forEach(hora -> comboHoraRetornoLocacao.addItem(hora));
		} catch(JAXBException e) {
			LOG.error("O XML de configura��es das horas n�o foi localizado! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("xmlNaoEncontrado")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void popularComboDispositivos(JComboBox<Object> comboDispositivo) {
		HashMap<String, DSFilterInfo> hashCams = new HashMap<String, DSFilterInfo>();
		List<DSFilterInfo> listaDispositivos = getDispositivos();
		String[] dispositivos = new String[listaDispositivos.size()];
		dispositivos[0] = "SELECIONAR DISPOSITIVO";
		for (int i = 0; i < listaDispositivos.size(); i++) {
			if (!listaDispositivos.get(i).getName().equalsIgnoreCase("none")) {
				dispositivos[(i + 1)] = listaDispositivos.get(i).getName().toUpperCase();
				hashCams.put(listaDispositivos.get(i).getName(), listaDispositivos.get(i));
			}
		}
		comboDispositivo.setModel(new DefaultComboBoxModel<Object>(dispositivos));
	}
	
	private static List<DSFilterInfo> getDispositivos() {
		DSFilterInfo[][] listaDispositivos = DSCapture.queryDevices();
		return Arrays.asList(listaDispositivos[0]);
	}
}