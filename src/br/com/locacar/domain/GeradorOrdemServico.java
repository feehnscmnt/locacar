package br.com.locacar.domain;

import br.com.locacar.model.modal.*;
import org.apache.logging.log4j.*;
import com.itextpdf.text.pdf.*;
import br.com.locacar.util.*;
import com.itextpdf.text.*;
import javax.swing.*;
import java.text.*;
import java.util.*;
import java.io.*;

/**
 * Classe respons�vel pela gera��o do arquivo da Ordem de Servi�o!
 * @author Felipe Nascimento
 */
public class GeradorOrdemServico {
	private static final Logger LOG = LogManager.getLogger(GeradorOrdemServico.class.getName());
	
	public static void gerarOs(String numeroLocacao, String cpfCnpj, String nome, String endereco, String bairro, String cidade, String email, String placa, String marca, String modelo, String cor, String ano, String combustivel, String renavam, String kilometragem, String dataLocacao, String horaLocacao, String dataRetLocacao, String horaRetLocacao, String localEntrega, String observacoes, String qtdeDias, String valorDia, String valorTaxaServico, String valorProtecao, String valorTotal, String usuario) {
		LOG.info("Gerando o arquivo da ordem de servi�o da loca��o {}...", numeroLocacao);
		Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			File path = new File(PathFilesUtil.getPathOs().concat(sdf.format(data)));
			if (!path.exists())
				path.mkdirs();
			
			PdfWriter.getInstance(doc, new FileOutputStream(path + "/".concat(numeroLocacao).concat(".pdf")));
			doc.open();
			
			Chunk chunk = new Chunk("LOCACAR - Ordem de Servi�o", FontFactory.getFont(FontFactory.COURIER, 28, Font.BOLD, BaseColor.BLUE));
			chunk.setBackground(BaseColor.LIGHT_GRAY);
			Paragraph titulo = new Paragraph(chunk);
			titulo.setAlignment(Element.ALIGN_CENTER);
			doc.add(titulo);
			
			Paragraph dadosCliente = new Paragraph("Dados do cliente", FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD, BaseColor.RED));
			dadosCliente.setLeading(35f);
			doc.add(dadosCliente);
			
			Paragraph dadoCpfCnpj = new Paragraph("CPF/CNPJ: ".concat(cpfCnpj), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoCpfCnpj);
			
			Paragraph dadoNome = new Paragraph("NOME: ".concat(nome), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoNome);
			
			Paragraph dadoEndereco = new Paragraph("ENDERE�O: ".concat(endereco).concat(" - ").concat(bairro).concat(", ").concat(cidade), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoEndereco);
			
			Paragraph dadoEmail = new Paragraph("E-MAIL: ".concat(email), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoEmail);
			
			Paragraph dadosVeiculo = new Paragraph("Dados do ve�culo", FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD, BaseColor.RED));
			dadosVeiculo.setLeading(35f);
			doc.add(dadosVeiculo);
			
			Paragraph dadoPlaca = new Paragraph("PLACA: ".concat(placa), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoPlaca);
			
			Paragraph dadoMarca = new Paragraph("MARCA: ".concat(marca), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoMarca);
			
			Paragraph dadoModelo = new Paragraph("MODELO: ".concat(modelo), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoModelo);
			
			Paragraph dadoCor = new Paragraph("COR: ".concat(cor), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoCor);
			
			Paragraph dadoAno = new Paragraph("ANO: ".concat(ano), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoAno);
			
			Paragraph dadoComb = new Paragraph("COMBUST�VEL: ".concat(combustivel), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoComb);
			
			Paragraph dadoRenavam = new Paragraph("RENAVAM: ".concat(renavam), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoRenavam);
			
			Paragraph dadoKilometragem = new Paragraph("KILOMETRAGEM: ".concat(kilometragem), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoKilometragem);
			
			Paragraph dadosLocacao = new Paragraph("Dados da loca��o", FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD, BaseColor.RED));
			dadosLocacao.setLeading(35f);
			doc.add(dadosLocacao);
			
			Paragraph dadoDataHoraLocacao = new Paragraph("DATA DA LOCA��O: ".concat(dataLocacao).concat(" HORA: ").concat(horaLocacao), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoDataHoraLocacao);
			
			Paragraph dadoDataHoraRetLocacao = new Paragraph("DATA DE RETORNO: ".concat(dataRetLocacao).concat(" HORA: ").concat(horaRetLocacao), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoDataHoraRetLocacao);
			
			Paragraph dadoQtdeDias = new Paragraph("QTDE DIAS: ".concat(qtdeDias), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoQtdeDias);
			
			Paragraph dadoLocalEntrega = new Paragraph("LOCAL DE ENTREGA: ".concat(localEntrega), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoLocalEntrega);
			
			Paragraph dadoObservacoes = new Paragraph("OBSERVA��ES: ".concat(observacoes), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			doc.add(dadoObservacoes);
			
			Paragraph dadoValorDia = new Paragraph("VALOR DIA: ".concat(valorDia), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC, BaseColor.BLUE));
			doc.add(dadoValorDia);
			
			Paragraph dadoValorTaxaServico = new Paragraph("VALOR TAXA DE SERVI�O: ".concat(valorTaxaServico), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC, BaseColor.BLUE));
			doc.add(dadoValorTaxaServico);
			
			Paragraph dadoValorProtecao = new Paragraph("VALOR PROTE��O: ".concat(valorProtecao), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC, BaseColor.BLUE));
			doc.add(dadoValorProtecao);
			
			Paragraph dadoValorTotal = new Paragraph("VALOR TOTAL: ".concat(valorTotal), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC, BaseColor.BLUE));
			doc.add(dadoValorTotal);
			
			Paragraph dadoUsuario = new Paragraph("LOCA��O GERADA NO DIA ".concat(sdf.format(data)).concat(" PELO ATENDENTE ").concat(usuario), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
			dadoUsuario.setLeading(200f);
			dadoUsuario.setAlignment(Element.ALIGN_RIGHT);
			doc.add(dadoUsuario);
		} catch(DocumentException | FileNotFoundException e) {
			LOG.error("Houve falha na cria��o do arquivo da Ordem de Servi�o! Exception: {}", e.getMessage());
			JOptionPane.showMessageDialog(null, new MensagensModel(Bundle.getString("falhaCriarArquivo")).getText(), "AVISO", JOptionPane.WARNING_MESSAGE);
		} finally {
			doc.close();
			LOG.info("O arquivo foi gerado com sucesso!");
		}
	}
}