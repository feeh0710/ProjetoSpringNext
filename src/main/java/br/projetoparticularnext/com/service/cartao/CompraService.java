package br.projetoparticularnext.com.service.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projetoparticularnext.com.model.cartao.Compra;
import br.projetoparticularnext.com.repository.cartao.CompraRepository;

@Service
public class CompraService {
	@Autowired
	CompraRepository compraRepository;

	public Compra criaCompra(Compra compra) {
		return compraRepository.save(compra);
	}
}
