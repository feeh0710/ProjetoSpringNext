package br.projetoparticularnext.com.model.cartao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.projetoparticularnext.com.utils.Const;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "TB_CARTAO")
public class Cartao {
	@Id
	@GeneratedValue(generator = "cartao", strategy = GenerationType.IDENTITY)
	@Column(name = "cd_cartao")
	private Long id;
	@Column(name = "nm_numero")
	private String numero;
	@Column(name = "ds_bandeira")
	private String bandeira;
	@Column(name = "ps_senha")
	private String senha;
	@Column(name = "ds_isativo")
	private boolean isAtivo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Compra> compras;

	public Cartao(String numero, String bandeira, String senha, boolean isAtivo) {
		this.numero = numero;
		this.bandeira = bandeira;
		this.senha = senha;
		this.isAtivo = isAtivo;
		this.compras = new ArrayList<Compra>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean alterarStatus(double limite, boolean isAtivo) {
		return true;
	}

	public String trocarSenha(String senha) {
		return null;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAtivo() {
		return isAtivo;
	}

	public void setAtivo(boolean isAtivo) {
		this.isAtivo = isAtivo;
	}

	// gera contas
	private int novoId() {
		return Const.CARTOES_CRIADOS++;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

}
