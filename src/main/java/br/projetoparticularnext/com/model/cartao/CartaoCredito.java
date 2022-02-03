package br.projetoparticularnext.com.model.cartao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.utils.Utils;

@Entity
@Table(name = "TB_CREDITO")
@SequenceGenerator(name = "credito", allocationSize = 1, sequenceName = "sq_credito")
@PrimaryKeyJoinColumn(name = "id_cartao_credito")
public class CartaoCredito extends Cartao {
	
	@Column(name = "ds_limite")
	private double limite;
	@Column(name = "dt_vencimento")
	private String dataVencimento;
	@Column(name = "ds_valor_fatura")
	private double valorFatura;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "apolice_id")
	private Apolice apolice;
	
	@OneToOne(mappedBy = "credito")
	private Conta conta;
	
	
	public CartaoCredito(String bandeira, String senha, boolean isAtivo, double limite, String dataVencimento) {
		super(Utils.geraBlocosNumeros(4), bandeira, senha, isAtivo);// cartao pede
		this.limite = limite;
		this.dataVencimento = dataVencimento;
//		this.dataVencimento = Utils.returnDataDiaDefinido(dataVencimento);
		this.valorFatura = 0.0;
	}
	
	public CartaoCredito() {
		// TODO Auto-generated constructor stub
	}
	

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}


	public String getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public double getValorFatura() {
		return valorFatura;
	}

	public void setValorFatura(double valorFatura) {
		this.valorFatura = valorFatura;
	}


	public Apolice getApolice() {
		return apolice;
	}


	public void setApolice(Apolice apolice) {
		this.apolice = apolice;
	}

	
	

}
