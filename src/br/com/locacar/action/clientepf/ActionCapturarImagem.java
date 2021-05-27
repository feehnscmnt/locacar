package br.com.locacar.action.clientepf;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.view.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.util.*;
import de.humatic.dsj.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.*;
import javax.swing.*;
import java.beans.*;
import java.text.*;
import java.util.*;
import java.awt.*;
import java.io.*;

/**
 * Classe action responsável pela captura de imagens utilizando o dispositivo de imagens do computador utilizado!
 * @author Felipe Nascimento
 */
public abstract class ActionCapturarImagem implements ActionListener, PropertyChangeListener {
	private static final Logger LOG = LogManager.getLogger(ActionCapturarImagem.class.getName());
	private static DSFilterInfo[][] dsiVideo;
	private static DSCapture graph;
	private SimpleDateFormat sdf;
	public static File imagem;
	private Calendar data;
	
	public void capturar(JButton btnCapturarImagem, JPanel pcbImagem, JComboBox<Object >jcbDispositivo, JRootPane rootPane) {
		if (jcbDispositivo.getSelectedItem().equals("SELECIONAR DISPOSITIVO"))
			Modal.mensagem(new MensagensModel(Bundle.getString("dispositivoNaoSelecionado"), nivelMensagens.ERRO), rootPane);
		else
			if (btnCapturarImagem.getText().equals("Capturar Imagem")) {
				limpaVisorImagem(pcbImagem);
				ActionProcurarImagem.limpaVisorImagem(pcbImagem);
				dsiVideo = DSCapture.queryDevices(DSCapture.SKIP_AUDIO);
				graph = new DSCapture(DSFiltergraph.RENDER_NATIVE, dsiVideo[0][0], false, DSFilterInfo.doNotRender(), this);
				pcbImagem.add(graph.asComponent());
				graph.setPreview();
				btnCapturarImagem.setText("Capturar");
			} else if (btnCapturarImagem.getText().equals("Capturar")) {
				if (graph.getState() == DSCapture.PREVIEW) {
					try {
						graph.stop();
						sdf = new SimpleDateFormat("ddMMyyyyhhmm");
						data = Calendar.getInstance();
						Image img = graph.getImage();
						imagem = new File(PathFilesUtil.getImage().concat("/imagesWebcam/IMG_CLIENTEPF_").concat(sdf.format(data.getTime())).concat(".jpg"));
						ImageIO.write((BufferedImage) img, "jpg", imagem);
					} catch(IOException e) {
						LOG.error("Erro de sistema: {}", e.getMessage());
						JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("erroSistema " + e.getMessage().toUpperCase())).getText(), "ERRO", JOptionPane.ERROR_MESSAGE);
					}
				}
				btnCapturarImagem.setText("Capturar Imagem");
			}
	}
	
	public static void limpaVisorImagem(JPanel pcbImagem) {
		if (graph != null) {
			pcbImagem.remove(graph);
			pcbImagem.removeAll();
			pcbImagem.revalidate();
			pcbImagem.repaint();
			graph.dispose();
			dsiVideo = null;
		}
	}
}