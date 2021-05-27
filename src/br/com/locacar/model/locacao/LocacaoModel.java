package br.com.locacar.model.locacao;

/**
 * Classe model para obtenção das informações dos locações!
 * @author Felipe Nascimento
 */
public class LocacaoModel {
	private String cod, situacao, numeroLocacao, cpfCnpj, nome, endereco, cidade, bairro, email, placa, marca, modelo, combustivel, cor, ano, renavam, kilometragem, dataLocacao, horaLocacao, dataRetornoLocacao, horaRetornoLocacao, localEntrega, observacoes, valorDia, qtdeDias, valorTaxaServico, valorProtecao, valorTotal;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNumeroLocacao() {
		return numeroLocacao;
	}
	
	public String getSituacao() {
		return situacao;
	}
	
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public void setNumeroLocacao(String numeroLocacao) {
		this.numeroLocacao = numeroLocacao;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getKilometragem() {
		return kilometragem;
	}

	public void setKilometragem(String kilometragem) {
		this.kilometragem = kilometragem;
	}

	public String getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(String dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public String getHoraLocacao() {
		return horaLocacao;
	}

	public void setHoraLocacao(String horaLocacao) {
		this.horaLocacao = horaLocacao;
	}

	public String getDataRetornoLocacao() {
		return dataRetornoLocacao;
	}

	public void setDataRetornoLocacao(String dataRetornoLocacao) {
		this.dataRetornoLocacao = dataRetornoLocacao;
	}

	public String getHoraRetornoLocacao() {
		return horaRetornoLocacao;
	}

	public void setHoraRetornoLocacao(String horaRetornoLocacao) {
		this.horaRetornoLocacao = horaRetornoLocacao;
	}

	public String getLocalEntrega() {
		return localEntrega;
	}

	public void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getValorDia() {
		return valorDia;
	}

	public void setValorDia(String valorDia) {
		this.valorDia = valorDia;
	}

	public String getQtdeDias() {
		return qtdeDias;
	}

	public void setQtdeDias(String qtdeDias) {
		this.qtdeDias = qtdeDias;
	}

	public String getValorTaxaServico() {
		return valorTaxaServico;
	}

	public void setValorTaxaServico(String valorTaxaServico) {
		this.valorTaxaServico = valorTaxaServico;
	}

	public String getValorProtecao() {
		return valorProtecao;
	}

	public void setValorProtecao(String valorProtecao) {
		this.valorProtecao = valorProtecao;
	}

	public String getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
}