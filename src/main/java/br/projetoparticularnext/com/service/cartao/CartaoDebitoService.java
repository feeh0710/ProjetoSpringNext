package br.projetoparticularnext.com.service.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projetoparticularnext.com.model.cartao.CartaoDebito;
import br.projetoparticularnext.com.repository.cartao.CartaoDebitoRepository;

@Service
public class CartaoDebitoService {
	
	@Autowired
	CartaoDebitoRepository cartaoDebitoRepository;
	
	public CartaoDebito criaDebito(CartaoDebito cartaoDebito) {
		return cartaoDebitoRepository.save(cartaoDebito);
	}
}
