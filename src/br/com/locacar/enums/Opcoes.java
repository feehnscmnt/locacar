package br.com.locacar.enums;

public enum Opcoes {
	SALVAR(1), ALTERAR(2), VIEW_CADASTRO(3), VIEW_CONSULTA(4);
	
	int opcoes;
	
	Opcoes(int opcao) {
		opcoes = opcao;
	}
	
	public int getOpcao() {
		return opcoes;
	}
}