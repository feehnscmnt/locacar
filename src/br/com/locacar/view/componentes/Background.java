package br.com.locacar.view.componentes;

import org.apache.logging.log4j.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Classe responsável pelo plano de fundo do View Principal!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class Background extends JPanel {
	private static final Logger LOG = LogManager.getLogger(Background.class.getName());
	private String pathImage;
	
	public Background(String pathImage) {
		this.pathImage = pathImage;
		setSize(900, 560);
		setLayout(null);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D gr = (Graphics2D) g.create();
		try {
			BufferedImage bi = ImageIO.read(new File(pathImage));
			gr.drawImage(bi, null, 0, 0);
		} catch(IOException e) {
			LOG.error("Erro: {}", e.getMessage());
		}
	}
}