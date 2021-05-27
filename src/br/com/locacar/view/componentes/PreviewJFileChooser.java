package br.com.locacar.view.componentes;

import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.io.*;

/**
 * Classe responsável por implementar uma pré-visualização das imagens no componente JFileChooser!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class PreviewJFileChooser extends JPanel implements PropertyChangeListener {
	private static final int ACCSIZE = 155;
	private int largura, altura;
	private ImageIcon icone;
	private Image imagem;
	private Color colorBG;
	
	public PreviewJFileChooser() {
		setPreferredSize(new Dimension(ACCSIZE, -1));
		colorBG = getBackground();
	}
	
	public void propertyChange(PropertyChangeEvent e) {
		String propertyName = e.getPropertyName();
		if (propertyName.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
			File selecao = (File) e.getNewValue();
			String nome;
			if (selecao == null)
				return;
			else
				nome = selecao.getAbsolutePath();
			if ((nome != null) && nome.toLowerCase().endsWith(".jpeg") || nome.toLowerCase().endsWith(".jpg") || nome.toLowerCase().endsWith(".ico") || nome.toLowerCase().endsWith(".gif") || nome.toLowerCase().endsWith(".png")) {
				icone = new ImageIcon(nome);
				imagem = icone.getImage();
				escalaImagem();
				repaint();
			}
		}
	}
	
	private void escalaImagem() {
		largura = imagem.getWidth(this);
		altura = imagem.getHeight(this);
		double ratio = 1.0;
		if (largura >= altura) {
			ratio = (double) (ACCSIZE - 5) / largura;
			largura = ACCSIZE - 5;
			altura = (int) (altura * ratio);
		} else {
			if (getHeight() > 150) {
				ratio = (double) (ACCSIZE - 5) / altura;
				altura = ACCSIZE - 5;
				largura = (int) (largura * ratio);
			} else {
				ratio = (double) getHeight() / altura;
				altura = getHeight();
				largura = (int) (largura * ratio);
			}
		}
		imagem = imagem.getScaledInstance(largura, altura, Image.SCALE_DEFAULT);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(colorBG);
		g.fillRect(0, 0, ACCSIZE, getHeight());
		g.drawImage(imagem, getWidth() / 2 - largura / 2 + 5, getHeight() / 2 - altura / 2, this);
	}
}