package br.projetoparticularnext.com.service.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projetoparticularnext.com.model.cartao.Apolice;
import br.projetoparticularnext.com.repository.cartao.ApoliceRepository;

@Service
public class ApoliceService {
	@Autowired
	ApoliceRepository apoliceRepository;

	public Apolice criaApolice(Apolice apolice) {
		return apoliceRepository.save(apolice);
	}

	public void delete(Long id) {
		System.out.println("CHEGOU O ID '_' "+ id);
		apoliceRepository.deleteById(id);
	}
}
