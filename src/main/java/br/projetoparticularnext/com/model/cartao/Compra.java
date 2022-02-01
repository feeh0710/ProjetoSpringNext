package br.projetoparticularnext.com.model.cartao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.projetoparticularnext.com.utils.Utils;

@Entity
@Table(name = "TB_COMPRA")
public class Compra {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "compra")
	@Column(name = "cd_compra")
	private Long id;
	@Column(name = "dt_datacompra")
	private String dataCompra;
	@Column(name = "ds_valor")
	private double valor;
	@Column(name = "ds_descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_cartao")
	private Cartao cartao;
	
	
	
	public Compra(double valor, String descricao) {
		this.dataCompra = Utils.dataAtual();
		this.valor = valor;
		this.descricao = descricao;
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	

	
}
