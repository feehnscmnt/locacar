package br.com.locacar.action.locacao;

import br.com.locacar.view.modal.BoxModalMensagens.*;
import br.com.locacar.model.locacao.*;
import br.com.locacar.view.locacao.*;
import br.com.locacar.model.modal.*;
import br.com.locacar.model.veiculo.VeiculosModel;
import br.com.locacar.view.modal.*;
import br.com.locacar.view.opcao.*;
import org.apache.logging.log4j.*;
import br.com.locacar.domain.*;
import br.com.locacar.dao.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe action responsável pela validação dos campos e pela inclusão da nova locação no banco de dados!
 * @author Felipe Nascimento
 */
public abstract class ActionSalvarLocacao implements ActionListener {
	private static final Logger LOG = LogManager.getLogger(ActionSalvarLocacao.class.getName());
	
	public boolean validaCamposCliente(String nome, String endereco, String cidade, String bairro, String email, JRootPane rootPane) {
		if (nome.equals("") & endereco.equals("") & cidade.equals("") & bairro.equals("") & email.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("consultaCliente"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.txtCpfCnpj.requestFocus();
			return false;
		}
		return true;
	}
	
	public boolean validaCamposVeiculo(String placa, String marca, String modelo, String combustivel, String cor, String ano, String renavam, JRootPane rootPane) {
		if (placa.replaceAll("\\D", "").equals("") & marca.equals("SELECIONE..") & modelo.equals("SELECIONE..") & combustivel.equals("") & cor.equals("") & ano.equals("") & renavam.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("consultaVeiculo"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.txtPlaca.requestFocus();
			return false;
		}
		return true;
	}
	
	public boolean validaCamposDadosLocacao(String dataLocacao, String horaLocacao, String dataRetornoLocacao, String horaRetornoLocacao, String kilometragem, String observacoes, String localEntrega, String valorDia, String qtdeDias, String valorTaxaServico, String valorProtecao, String valorTotal, JRootPane rootPane) {
		if (dataLocacao.equals("") & horaLocacao.equals("SELECIONE..") & dataRetornoLocacao.equals("") & horaRetornoLocacao.equals("SELECIONE..") & kilometragem.equals("") &
			observacoes.equals("") & localEntrega.equals("") & valorDia.equals("") & qtdeDias.equals("") & valorTaxaServico.equals("") & valorProtecao.equals("") & valorTotal.equals("")) {
			Modal.mensagem(new MensagensModel(Bundle.getString("camposLocacao"), nivelMensagens.ERRO), rootPane);
			FrmLocacao.jdcDataLocacao.requestFocus();
			return false;
		}
		return true;
	}
	
	public void salvar(String codigo, String numeroLocacao, String cpfCnpj, String nome, String endereco, String cidade, String bairro, String email, String placa, String marca, String modelo, String combustivel, String cor, String ano, String renavam, String kilometragem, String dataLocacao, String horaLocacao, String dataRetornoLocacao, String horaRetornoLocacao, String localEntrega, String observacoes, String valorDia, String qtdeDias, String valorTaxaServico, String valorProtecao, String valorTotal, String situacao, JRootPane rootPane, Opcoes opcao) {
		VeiculosModel veiculo = new VeiculosModel();
		LocacaoModel locacao = new LocacaoModel();
		Locacao locacoes = new DAOLocacao();
		if (codigo != "") {
			locacao.setCod(codigo);
		}
		locacao.setNumeroLocacao(numeroLocacao);
		locacao.setCpfCnpj(cpfCnpj);
		locacao.setNome(nome);
		locacao.setEndereco(endereco);
		locacao.setCidade(cidade);
		locacao.setBairro(bairro);
		locacao.setEmail(email);
		locacao.setPlaca(placa);
		locacao.setMarca(marca);
		locacao.setModelo(modelo);
		locacao.setCombustivel(combustivel);
		locacao.setCor(cor);
		locacao.setAno(ano);
		locacao.setRenavam(renavam);
		locacao.setKilometragem(kilometragem);
		locacao.setDataLocacao(dataLocacao);
		locacao.setHoraLocacao(horaLocacao);
		locacao.setDataRetornoLocacao(dataRetornoLocacao);
		locacao.setHoraRetornoLocacao(horaRetornoLocacao);
		locacao.setLocalEntrega(localEntrega);
		locacao.setObservacoes(observacoes);
		locacao.setValorDia(valorDia);
		locacao.setQtdeDias(qtdeDias);
		locacao.setValorTaxaServico(valorTaxaServico);
		locacao.setValorProtecao(valorProtecao);
		locacao.setValorTotal(valorTotal);
		locacao.setSituacao(situacao);
		veiculo.setSituacao("ALUGADO");
		if (opcao.equals(Opcoes.SALVAR)) {
			locacoes.veiculoAlugado(veiculo, locacao);
			locacoes.salvar(locacao);
			LOG.info("Nova locação salva com sucesso!");
			Modal.mensagem(new MensagensModel(Bundle.getString("cadLocExito", numeroLocacao), nivelMensagens.INFO), rootPane);
		}
	}
}