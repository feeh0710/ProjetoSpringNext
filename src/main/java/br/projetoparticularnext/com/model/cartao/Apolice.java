package br.projetoparticularnext.com.model.cartao;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.projetoparticularnext.com.utils.Const;
import br.projetoparticularnext.com.utils.Utils;
@Entity
@Table(name = "TB_APOLICE")
public class Apolice {
	@Id
	@GeneratedValue(generator = "apolice",strategy = GenerationType.IDENTITY)
	@Column(name = "cd_apolice")
	private Long id;
	@Column(name = "dt_assinatura")
	private String dataAssinatura;
	@Column(name = "dt_carencia")
	private String dataCarencia;
	@Column(name = "dt_validade")
	private String dataValidade;
	@Column(name = "ds_anos")
	private int anos;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "seguro_id")
	private Seguro seguro;
	
	@OneToOne(mappedBy = "apolice")
	private CartaoCredito cartaoCredito;
	

	public Apolice(Seguro seguro, int anos) {
		this.anos = anos;
		this.setDataValidade(Utils.getDateAddYears(anos));
		this.dataAssinatura = Utils.dataAtual();
		this.dataCarencia = Utils.getDateAddDays(Const.DIAS_DE_CARENCIA_APOLICE);//
		this.seguro = seguro;
	}
	
	public Apolice() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataAssinatura() {
		return dataAssinatura;
	}

	public void setDataAssinatura(String dataAssinatura) {
		this.dataAssinatura = dataAssinatura;
	}

	public String getDataCarencia() {
		return dataCarencia;
	}

	public void setDataCarencia(int dataCarencia) {
		this.dataCarencia = Utils.getDateAddDays(dataCarencia);;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public String getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(String dataValidade) {
		this.dataValidade = dataValidade;
	}

	public int getAnos() {
		return anos;
	}

	public void setAnos(int anos) {
		this.anos = anos;
	}
	
	

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public void setDataCarencia(String dataCarencia) {
		this.dataCarencia = dataCarencia;
	}

	@Override
	public String toString() {
		return "Apolice [id=" + id + ", dataAssinatura=" + dataAssinatura + ", dataCarencia=" + dataCarencia
				+ ", dataValidade=" + dataValidade + ", anos=" + anos + ", seguro=" + seguro + ", cartaoCredito="
				+ cartaoCredito + "]";
	}
	
	

}
