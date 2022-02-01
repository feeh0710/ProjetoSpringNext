package br.projetoparticularnext.com.service.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projetoparticularnext.com.model.cartao.Seguro;
import br.projetoparticularnext.com.repository.cartao.SeguroRepository;

@Service
public class SeguroService {
	@Autowired
	SeguroRepository seguroRepository;

	public Seguro criaSeguro(Seguro seguro) {
		return seguroRepository.save(seguro);
	}
}
