package br.projetoparticularnext.com.service;

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
}
