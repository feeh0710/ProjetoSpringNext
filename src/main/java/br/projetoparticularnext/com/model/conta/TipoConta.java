package br.projetoparticularnext.com.model.conta;

public enum TipoConta {
	CONTACORRENTE(1,"CORRENTE"), CONTAPOUPANCA(2,"POUPANCA");
	
	private int code;
	private String descricao;
	
	private TipoConta(int code, String descricao) {
		this.code = code;
		this.descricao = descricao;
	}

	public int getCode() {
		return code;
	}

	public String getDescricao() {
		return descricao;
	}


}
