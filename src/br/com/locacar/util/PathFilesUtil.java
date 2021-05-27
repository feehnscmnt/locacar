package br.com.locacar.util;

import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import java.nio.file.*;
import javax.swing.*;
import java.net.*;
import java.awt.*;
import java.io.*;

/**
 * Classe responsável por obter o caminho dos arquivos de configurações, imagens e da ordem de serviço!
 * @author Felipe Nascimento
 */
public class PathFilesUtil {
	private static final Logger LOG = LogManager.getLogger(PathFilesUtil.class.getName());
	
	public static InputStream getConfigFile(String file) {
		InputStream is = null;
		try {
			String path = PathFilesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			path = Paths.get(PathFilesUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().toString();
			is = new FileInputStream(path.concat("/resource/configs/").concat(file));
		} catch (URISyntaxException e) {
			LOG.error("Erro de sistema: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("erroSistema " + e.getMessage().toUpperCase())).getText(), "ERRO", JOptionPane.ERROR_MESSAGE);
		} catch(FileNotFoundException e) {
			LOG.error("O arquivo {} não foi localizado! Exception: {}", file, e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("arqNaoEncontrado", file)).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		}
		return is;
	}
	
	public static String getImage() {
		String path = null;
		try {
			path = PathFilesUtil.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			path = Paths.get(PathFilesUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent().toString();
		} catch (URISyntaxException e) {
			LOG.error("Erro de sistema: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("erroSistema " + e.getMessage().toUpperCase())).getText(), "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		return path.concat("/resource");
	}
	
	public static ImageIcon getImg(String img) {
		Image imgn = Toolkit.getDefaultToolkit().createImage(getImage().concat("/images/").concat(img));
		return new ImageIcon(imgn);
	}
	
	public URL getIconTopo() {
		URL url = getClass().getClassLoader().getResource("br/com/locacar/files/images/imgLogoTopoForms.png");
		return url;
	}
	
	public static String getPathOs() {
		String path = null;
		path = System.getProperty("user.home").concat("/Locações/");
		return path;
	}
}