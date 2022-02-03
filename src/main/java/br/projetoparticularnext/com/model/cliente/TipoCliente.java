package br.projetoparticularnext.com.model.cliente;

import br.projetoparticularnext.com.model.conta.Conta;

public enum TipoCliente {
	COMUM(1000),SUPER(5000),PREMIUM(10000);
	
	private double limite;
	
	TipoCliente(int limite) {
		this.limite = limite;
	}
	
	public double getLimite() {
		return this.limite;
	}
	
	//RECEBE CONTA E VERIFICA SE MUDA PARA OUTRO TIPO
		public static Conta verificaTipoConta(Conta c) {
			if (c.getSaldo() <= 5000) {
				c.getCliente().setTipo(TipoCliente.COMUM);
				return c;
			} else if (c.getSaldo() >= 5000 && c.getSaldo() < 15000) {
				c.getCliente().setTipo(TipoCliente.SUPER);
				return c;
			} else {
				c.getCliente().setTipo(TipoCliente.PREMIUM);
				return c;
			}
		}
}
