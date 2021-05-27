package br.com.locacar.action.clientepf;

import br.com.locacar.view.componentes.*;
import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import javax.swing.filechooser.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Classe action responsável pela busca de imagens nos diretórios do computador utilizado!
 * @author Felipe Nascimento
 */
public abstract class ActionProcurarImagem implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionProcurarImagem.class.getName());
	public static File origemImagem, destinoImagem, imagemParaMover, imagem;
	private PreviewJFileChooser preview;
	private FileNameExtensionFilter fnef;
	private WritableRaster wr;
	private JLabel lblImagem;
	private JFileChooser jfc;
	private ImageIcon foto;
	public DataBuffer db;
	
	public void procurar(JPanel pcbImagem) {
		preview = new PreviewJFileChooser();
		jfc = new JFileChooser();
		jfc.addPropertyChangeListener(preview);
		jfc.setDialogTitle("Selecionar Imagem");
		jfc.setAccessory(preview);
		fnef = new FileNameExtensionFilter("jpeg", new String[] { "jpg", "ico", "png", "gif", "bmp", "tif", "pdf", "pgm" });
		jfc.addChoosableFileFilter(fnef);
		jfc.setFileView(new FileView() { public Icon getIcon(File file) { return FileSystemView.getFileSystemView().getSystemIcon(file); } });
		int open = jfc.showOpenDialog(null);
		if (open == JFileChooser.APPROVE_OPTION) {
			limpaVisorImagem(pcbImagem);
			pcbImagem.revalidate();
			pcbImagem.repaint();
			imagemParaMover = new File(jfc.getCurrentDirectory().getPath().concat("/").concat(jfc.getSelectedFile().getName()));
			destinoImagem = new File(PathFilesUtil.getImage().concat("/imagesSelected/"));
			boolean moveu = imagemParaMover.renameTo(new File(destinoImagem, imagemParaMover.getName()));
			if (moveu) {
				try {
					imagem = new File(PathFilesUtil.getImage().concat("/imagesSelected/").concat(imagemParaMover.getName()));
					BufferedImage imagemOriginal = ImageIO.read(imagem);
					int tipo = imagemOriginal.getType() == 0 ? 2 : imagemOriginal.getType();
					BufferedImage imagemJpg = imgJpg(imagemOriginal, tipo);
					foto = new ImageIcon(Toolkit.getDefaultToolkit().createImage(imagemJpg.getSource()));
					wr = imagemJpg.getRaster();
					db = wr.getDataBuffer();
					lblImagem = new JLabel("", foto, 0);
					pcbImagem.add(lblImagem);
					jfc.setCurrentDirectory(imagem);
				} catch(IOException e) {
					LOG.error("A imagem do cliente não foi localizada! Exception: {}", e.getMessage());
					JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("imagemNaoEncontrada")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	public static BufferedImage imgJpg(Image imagemOriginal, int tipo) {
		BufferedImage br = new BufferedImage(160, 153, tipo);
		Graphics2D g = br.createGraphics();
		g.drawImage(imagemOriginal, 0, 0, 160, 153, null);
		g.dispose();
		return br;
	}
	
	public static void limpaVisorImagem(JPanel pcbImagem) {
		pcbImagem.removeAll();
		pcbImagem.revalidate();
		pcbImagem.repaint();
	}
}