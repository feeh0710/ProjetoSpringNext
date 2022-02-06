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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

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
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@Column(name = "ds_tipo")
	private String tipoConta;
	//private TipoConta tipoConta;
	
	private int tipo;
	
	
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
	
	public Conta() {
		// TODO Auto-generated constructor stub
	}

	public Conta(Cliente cliente, TipoConta tipoConta) {
		this.listPix = new ArrayList<Pix>();
		this.numero = novaConta();
		this.saldo = 0.0;
		this.cliente = cliente;
		this.tipo = tipoConta.getCode();
		this.tipoConta = tipoConta.getDescricao();
		this.data = Utils.dataAtual();
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Conta [id=" + id + ", numero=" + numero + ", saldo=" + saldo + ", cliente=" + cliente + ", tipoConta="
				+ tipoConta + ", listPix=" + listPix + ", credito=" + credito + ", debito=" + debito + ", data=" + data
				+ "]";
	}
	
	
	
	
	
	

}
