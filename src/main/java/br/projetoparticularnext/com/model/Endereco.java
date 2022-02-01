package br.projetoparticularnext.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.projetoparticularnext.com.model.cliente.Cliente;

@Entity
@Table(name = "TB_ENDERECO")
@SequenceGenerator(name = "endereco", allocationSize = 1, sequenceName = "sq_endereco")
public class Endereco {
	@Id
	@GeneratedValue(generator = "endereco",strategy = GenerationType.AUTO)
	@Column(name = "cd_endereco")
	private Long id;
	
	@Column(name = "ds_cidade")
	private String cidade;
	@Column(name = "cd_estado")
	private String estado;
	@Column(name = "ds_bairro")
	private String bairro;
	@Column(name = "ds_numero")
	private String numero;
	@Column(name = "ds_logradouro")
	private String logradoro;
	@Column(name = "ds_cep")
	private String cep;
	
	@OneToOne(mappedBy = "endereco")
	private Cliente cliente;
	// comprovanteEndereco : imagem

	public Endereco(String cidade, String estado, String bairro, String numero, String logradoro, String cep) {
		this.cidade = cidade;
		this.estado = estado;
		this.bairro = bairro;
		this.numero = numero;
		this.logradoro = logradoro;
		this.cep = cep;
	}
	
	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getRua() {
		return logradoro;
	}

	public void setLogradoro(String logradoro) {
		this.logradoro = logradoro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}
