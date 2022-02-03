package br.projetoparticularnext.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	ContaRepository contaRepository;
	
	public Conta createConta(Conta conta) {
		return contaRepository.save(conta);
	}

	public List<Conta> getAllContas(Long id_cliente) {
		return contaRepository.findAllContaByClienteId(id_cliente);
	}

	public List<Conta> createContas(List<Conta> contas) {
		return contaRepository.saveAll(contas);
	}

	public Conta getConta(long id) {
		return contaRepository.getById(id);
	}
	
	
}
