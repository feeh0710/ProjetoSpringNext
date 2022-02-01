package br.projetoparticularnext.com.model.conta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.projetoparticularnext.com.model.cartao.CartaoCredito;
import br.projetoparticularnext.com.model.cartao.CartaoDebito;
import br.projetoparticularnext.com.model.cliente.Cliente;
import br.projetoparticularnext.com.model.pix.Pix;
import br.projetoparticularnext.com.utils.Const;
import br.projetoparticularnext.com.utils.Utils;

@Entity
@Table(name = "TB_CONTA")
@SequenceGenerator(allocationSize = 1,sequenceName = "sq_conta", name = "conta" )
public class Conta {
	@Id
	@GeneratedValue(generator = "conta",strategy = GenerationType.IDENTITY)
	@Column(name = "cd_conta")
	private Long id;
	
	@Column(name = "ds_numero")
	private String numero;
	@Column(name = "ds_saldo")
	private double saldo;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Column(name = "ds_tipo")
	private TipoConta tipoConta;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Pix> listPix;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "credito_id")
	private CartaoCredito credito;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "debito_id")
	private CartaoDebito debito;

	@Column(name = "dt_data")
	private String data;

	public List<Pix> getListPix() {
		return listPix;
	}

	public void setListPix(List<Pix> listPix) {
		this.listPix = listPix;
	}

	public Conta(Cliente cliente, TipoConta tipoConta) {
		this.listPix = new ArrayList<Pix>();
		this.numero = novaConta();
		this.saldo = 0.0;
		this.cliente = cliente;
		this.tipoConta = tipoConta;
		this.data = Utils.dataAtual();
	}

	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	private String novaConta() {
		return String.valueOf(Const.NUMERO_CONTA++);
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getSaldo() {
		return saldo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public CartaoCredito getCredito() {
		return credito;
	}

	public void setCredito(CartaoCredito credito) {
		this.credito = credito;
	}

	public CartaoDebito getDebito() {
		return debito;
	}

	public void setDebito(CartaoDebito debito) {
		this.debito = debito;
	}
	
	

}
