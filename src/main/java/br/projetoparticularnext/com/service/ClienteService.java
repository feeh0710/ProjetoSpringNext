package br.projetoparticularnext.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projetoparticularnext.com.model.cliente.Cliente;
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
}
