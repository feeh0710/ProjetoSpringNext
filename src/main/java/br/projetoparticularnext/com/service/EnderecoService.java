package br.projetoparticularnext.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projetoparticularnext.com.model.Endereco;
import br.projetoparticularnext.com.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Endereco createEndereco(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
}
