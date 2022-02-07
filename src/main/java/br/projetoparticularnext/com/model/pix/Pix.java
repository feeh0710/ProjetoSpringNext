package br.projetoparticularnext.com.model.pix;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.utils.Const;

@Entity
@Table(name = "TB_PIX")
public class Pix  {
	@Id
	@GeneratedValue(generator = "pix",strategy = GenerationType.IDENTITY)
	public Long id;
	@Column(name = "tipochave")
	public TipoChavePix tipoChave;
	@Column(name = "conteudochave")
	public String conteudoChave;
	@Column(name = "isativado")
	public boolean isAtivado;
	
	@ManyToOne
	@JoinColumn(name = "id_conta")
	private Conta conta;
	
	public boolean ativarChave(TipoChavePix tipoChave, String conteudoChave, boolean isAtivado) {
		this.tipoChave = tipoChave;
		this.conteudoChave = conteudoChave;
		this.isAtivado = isAtivado;
		return true;
	}
	
	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Conta getConta() {
		return conta;
	}



	public void setConta(Conta conta) {
		this.conta = conta;
	}



	public TipoChavePix getTipoChave() {
		return tipoChave;
	}

	public void setTipoChave(TipoChavePix tipoChave) {
		this.tipoChave = tipoChave;
	}


	public String getConteudoChave() {
		return conteudoChave;
	}

	public void setConteudoChave(String conteudoChave) {
		this.conteudoChave = conteudoChave;
	}

	public boolean isAtivado() {
		return isAtivado;
	}

	public void setAtivado(boolean isAtivado) {
		this.isAtivado = isAtivado;
	}


}
