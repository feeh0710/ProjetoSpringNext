package br.projetoparticularnext.com.model.cliente;

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

import br.projetoparticularnext.com.model.Endereco;
import br.projetoparticularnext.com.model.conta.Conta;

@Entity
@Table(name = "TB_CLIENTE")
@SequenceGenerator(name = "cliente", allocationSize = 1, sequenceName = "sq_cliente")
public class Cliente {
	@Id
	@GeneratedValue(generator = "cliente", strategy = GenerationType.IDENTITY)
	@Column(name = "cd_cliente")
	private Long id;

	@Column(name = "ds_cpf")
	private String cpf;
	@Column(name = "nm_nome")
	private String nome;

	@Column(name = "ds_email")
	private String email;
	@Column(name = "ds_senha")
	private String senha;
	
	@Column(name = "ds_telefone")
	private String telefone;
	
	@Column(name = "ds_tipo")
	private TipoCliente tipo;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;
	
	@Column(name = "ds_rg")
	private String rg;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Conta> listConta;

	public Cliente(String senha, String email, String cpf, String rg, String nome,String telefone, Endereco endereco) {
		this.cpf = cpf;
		this.telefone = telefone;
		this.rg = rg;
		this.nome = nome;
		this.endereco = endereco;
		this.email = email;
		this.senha = senha;
		this.tipo = TipoCliente.COMUM;
	}
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}
	
	

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoCliente getTipo() {
		return tipo;
	}

	public void setTipo(TipoCliente tipoCliente) {
		this.tipo = tipoCliente;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public List<Conta> getListConta() {
		return listConta;
	}

	public void setListConta(List<Conta> listConta) {
		this.listConta = listConta;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", senha=" + senha
				+ ", tipo=" + tipo + "]";
	}
	
	

}
