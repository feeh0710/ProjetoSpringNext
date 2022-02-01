package br.projetoparticularnext.com.service.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.projetoparticularnext.com.model.cartao.CartaoCredito;
import br.projetoparticularnext.com.repository.cartao.CartaoCreditoRepository;

@Service
public class CartaoCreditoService {
	
	@Autowired
	CartaoCreditoRepository cartaoCreditoRepository;
	
	public CartaoCredito criaCredito(CartaoCredito cartaoCredito) {
		return cartaoCreditoRepository.save(cartaoCredito);
	}
}
