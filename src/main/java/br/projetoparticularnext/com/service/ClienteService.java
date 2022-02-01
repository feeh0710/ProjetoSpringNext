package br.projetoparticularnext.com.service;

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
}
