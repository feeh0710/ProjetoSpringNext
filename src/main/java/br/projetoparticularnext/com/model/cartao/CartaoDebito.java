package br.projetoparticularnext.com.model.cartao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.utils.Utils;

@Entity
@Table(name = "TB_DEBITO")
@PrimaryKeyJoinColumn(name = "id_cartao_debito")
public class CartaoDebito extends Cartao {

	@Column(name = "ds_limite")
	private double limitePorTransacao;
	
	@OneToOne(mappedBy = "debito")
	private Conta conta;

	public CartaoDebito(String bandeira, String senha, boolean isAtivo, double limitePorTransacao) {
		super(Utils.geraBlocosNumeros(4), bandeira, senha, isAtivo);
		this.limitePorTransacao = limitePorTransacao;
	}
	
	public CartaoDebito() {
		// TODO Auto-generated constructor stub
	}

	public double getLimitePorTransacao() {
		return limitePorTransacao;
	}

	public void setLimitePorTransacao(double limitePorTransacao) {
		this.limitePorTransacao = limitePorTransacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "CartaoDebito [limitePorTransacao=" + limitePorTransacao + ", conta=" + conta + "]";
	}
	
	
	
	

}
