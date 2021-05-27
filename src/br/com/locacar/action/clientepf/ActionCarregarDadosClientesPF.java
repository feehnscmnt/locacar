package br.com.locacar.action.clientepf;

import br.com.locacar.model.clientepf.*;
import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import com.toedter.calendar.*;
import br.com.locacar.util.*;
import br.com.locacar.dao.*;

import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.text.*;
import java.sql.*;
import java.io.*;

/**
 * Classe responsável por carregar do banco de dados as informações dos clientes pessoa física para alterações!
 * @author Felipe Nascimento
 */
public abstract class ActionCarregarDadosClientesPF extends WindowAdapter {
	private static final Logger LOG = LogManager.getLogger(ActionCarregarDadosClientesPF.class.getName());
	private WritableRaster wr;
	private JLabel lblImagem;
	private ImageIcon foto;
	public DataBuffer db;
	private ResultSet rs;
	private File imagem;
	
	public void carregarDadosClientes(JLabel codigo, JTextField txtNome, JFormattedTextField txtCpf, JFormattedTextField txtRg, JFormattedTextField txtContato, JDateChooser jdcDataNasc, JComboBox<Object> jcbCategCnh, JTextField txtNumeroCnh, JTextField txtEmail, JTextField txtEndereco, JFormattedTextField txtCep, JTextField txtCidade, JComboBox<Object> jcbUf, JTextField txtBairro, JTextField txtComplemento, JPanel pcbImagem) {
		LOG.info("Carregando os dados dos clientes pf para alterações...");
		if (codigo.getText() != "") {
			ClientesPF clientes = new DAOClientesPF();
			rs = clientes.buscarParaAlteracao(codigo.getText());
			try {
				if (rs.next()) {
					txtNome.setText(rs.getString("NOME"));
					txtCpf.setText(rs.getString("CPF"));
					txtRg.setText(rs.getString("RG"));
					txtContato.setText(rs.getString("CONTATO"));
					jdcDataNasc.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("DATANASCIMENTO")));
					jcbCategCnh.setSelectedItem(rs.getString("CATEGCNH"));
					txtNumeroCnh.setText(rs.getString("NUMEROCNH"));
					txtEmail.setText(rs.getString("EMAIL"));
					txtEndereco.setText(rs.getString("ENDERECO"));
					txtCep.setText(rs.getString("CEP"));
					txtCidade.setText(rs.getString("CIDADE"));
					jcbUf.setSelectedItem(rs.getString("UF"));
					txtBairro.setText(rs.getString("BAIRRO"));
					txtComplemento.setText(rs.getString("COMPLEMENTO"));
					pcbImagem.revalidate();
					pcbImagem.repaint();
					getImagem(pcbImagem);
				}
			} catch(SQLException e) {
				LOG.error("Houve problemas na conexão com o banco de dados! Exception: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("problemasConexaoDB")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
			} catch(ParseException e) {
				LOG.error("Erro de sistema: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("erroSistema ".concat(e.getMessage().toUpperCase()))).getText(), "ERRO", JOptionPane.ERROR_MESSAGE);
			} catch(IOException e) {
				LOG.error("A imagem do cliente não foi localizada! Exception: {}", e.getMessage());
				JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("imagemNaoEncontrada")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
			}
		}
		LOG.info("Dados carregados com sucesso!");
	}
	
	private void getImagem(JPanel pcbImagem) throws SQLException, IOException {
		if (rs.getString("IMAGEM").contains("IMG"))
			imagem = new File(PathFilesUtil.getImage().concat("/imagesWebcam/").concat(rs.getString("IMAGEM")));
		else
			imagem = new File(PathFilesUtil.getImage().concat("/imagesSelected/").concat(rs.getString("IMAGEM")));
		BufferedImage imagemOriginal = ImageIO.read(imagem);
		int tipo = imagemOriginal.getType() == 0 ? 2 : imagemOriginal.getType();
		BufferedImage imagemJpg = ActionProcurarImagem.imgJpg(imagemOriginal, tipo);
		foto = new ImageIcon(Toolkit.getDefaultToolkit().createImage(imagemJpg.getSource()));
		wr = imagemJpg.getRaster();
		db = wr.getDataBuffer();
		lblImagem = new JLabel("", foto, 0);
		pcbImagem.add(lblImagem);
	}
}