package br.projetoparticularnext.com.service.cartao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import br.projetoparticularnext.com.model.cartao.CartaoCredito;
import br.projetoparticularnext.com.model.cartao.CartaoDebito;
import br.projetoparticularnext.com.model.conta.Conta;
import br.projetoparticularnext.com.repository.cartao.CartaoDebitoRepository;

@Service
public class CartaoDebitoService {
	
	@Autowired
	CartaoDebitoRepository cartaoDebitoRepository;
	
	public CartaoDebito create(CartaoDebito cartaoDebito) {
		return cartaoDebitoRepository.save(cartaoDebito);
	}
	
	public void verificaCartao(ModelAndView modelAndView, Conta conta) {
		if (conta.getDebito() != null) {
			System.err.println("S CART");
			modelAndView.addObject("cadastrado", "cadastrado");
			if(conta.getDebito().isAtivo()) {
				modelAndView.addObject("ativado", "Bloquear");
				modelAndView.addObject("desbloqueado", "");
			}else {
				modelAndView.addObject("ativado", "Desbloquear");
				
			}
		}else {
			System.err.println("N CART");
			modelAndView.addObject("ncadastrado", "Criar Cartão");
		}
	}
	
	public void delete(Long id) {
		cartaoDebitoRepository.deleteById(id);
	}
}
