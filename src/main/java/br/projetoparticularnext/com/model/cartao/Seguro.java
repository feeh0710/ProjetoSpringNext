package br.projetoparticularnext.com.model.cartao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.projetoparticularnext.com.utils.Const;

@Entity
@Table(name = "TB_SEGURO")
public class Seguro {
	@Id
	@GeneratedValue(generator = "seguro" ,strategy = GenerationType.IDENTITY)
	@Column(name = "cd_seguro")
	private Long id;
	@Column(name = "nm_regra")
	private int regra;
	@Column(name = "ds_valor")
	private double valor;
	@Column(name = "ds_taxa")
	private double taxa;
	@Column(name = "tiposeguro")
	private TipoSeguro tipoSeguro;
	@Column(name = "nm_nome")
	private String nome;
	
	@OneToOne(mappedBy = "seguro")
	private Apolice apolice;

	public Seguro( TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
		this.nome = tipoSeguro.name();
		this.valor = buscaValorApolice(tipoSeguro);
		this.taxa = buscaTaxaApolice(tipoSeguro);
		this.regra = buscaRegra(tipoSeguro);
	}
	
	public Seguro() {
		// TODO Auto-generated constructor stub
	}

	private double buscaTaxaApolice(TipoSeguro tipoSeguro) {
		return tipoSeguro.getTaxa();
	}

	private double buscaValorApolice(TipoSeguro tipoSeguro) {
		return tipoSeguro.getValorSeguro();
	}

	//BUSCA REGRA APÓS INICIALIZAR CONSTRUTOR
	private int buscaRegra(TipoSeguro tipoSeguro) {
		return tipoSeguro.getRegra();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getRegras() {
		return regra;
	}

	public void setRegra(int regras) {
		this.regra = regras;
	}

	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}

	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getTaxa() {
		
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Apolice getApolice() {
		return apolice;
	}

	public void setApolice(Apolice apolice) {
		this.apolice = apolice;
	}

	public int getRegra() {
		return regra;
	}
	
	
	

}
