package br.projetoparticularnext.com.service;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projetoparticularnext.com.dto.ClienteEndereco;
import br.projetoparticularnext.com.model.cliente.Cliente;
import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.model.conta.TipoConta;
import br.projetoparticularnext.com.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	public Cliente createCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	public Cliente buscaCpfSenha(String cpf,String senha) {
		return clienteRepository.findByCpfAndSenha(cpf,senha);
	}
	
	public Cliente getCliente(Long id) {
		return clienteRepository.getById(id);
	}
	
	
	public Cliente ciarContas(Cliente cliente) {
		Conta conta1 = new Conta(cliente,TipoConta.CONTACORRENTE);
		Conta conta2 = new Conta(cliente,TipoConta.CONTAPOUPANCA);
		cliente.setListConta(Arrays.asList(conta1,conta2));
		return cliente;
	}
	public Cliente criaContaPoupanca(Cliente cliente) {
		Conta conta2 = new Conta(cliente,TipoConta.CONTAPOUPANCA);
		cliente.setListConta(Arrays.asList(conta2));
		return cliente;
	}
	
	public Cliente criaContaCorrente(Cliente cliente) {
		Conta conta1 = new Conta(cliente,TipoConta.CONTACORRENTE);
		cliente.setListConta(Arrays.asList(conta1));
		return cliente;
	}

	public boolean validarCampos(ClienteEndereco cliend) {
		if(cliend.getBairro().isEmpty() && cliend.getCep().isEmpty() && cliend.getCidade().isEmpty()
				&& cliend.getCpf().isEmpty() && cliend.getEmail().isEmpty() && cliend.getEstado().isEmpty()
				&& cliend.getLogradouro().isEmpty() && cliend.getNome().isEmpty() && cliend.getNumero().toString().equals("")
				&& cliend.getRg().isEmpty() && cliend.getSenha().isEmpty() && cliend.getTelefone().isEmpty()
				&& cliend.getTipo().toString().equals("")) {
			return false;
		}
		return true;
	}


}
